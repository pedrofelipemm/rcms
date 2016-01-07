package br.ufscar.rcms.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.ConfiguracaoService;
import br.ufscar.rcms.servico.LattesService;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.exception.RCMSException;

@Component
public class JobLattesImporter extends AbstractJob {

    private static final Logger LOGGER = LoggerFactory.getLogger(JobLattesImporter.class);

    @Autowired
    private LattesService lattesService;

    @Autowired
    private PesquisadorService pesquisadorService;

    @Autowired
    private ConfiguracaoService configuracaoService;

    @Autowired
    private br.ufscar.rcms.util.ForkJoinPool forkJoinPool;

    @Override
    public void process() {

        if (!Boolean.valueOf(configuracaoService.buscarPorKey(Tipo.IMPORTACAO_LATTES_AUTOMATICA).getValue())) {
            return;
        }

        ForkJoinPool pool = forkJoinPool.getPool();

        long start = System.currentTimeMillis();

        List<Pesquisador> pesquisadores = pesquisadorService.buscarTodos();

        ParallelProcess parallelProcess = new ParallelProcess(lattesService, pesquisadores);

        pool.execute(parallelProcess);

        logThreadsStatuses(pool, start, pesquisadores, parallelProcess);
    }

    @Override
    protected String getJobName() {
        return "LattesImporter";
    }

    class ParallelProcess extends RecursiveAction {

        private static final int MAXIMUM_PROCESS_PER_THREAD = 1;

        private static final long serialVersionUID = 1L;

        private LattesService lattesService;

        private List<Pesquisador> pesquisadores;

        public ParallelProcess(final LattesService lattesService, final List<Pesquisador> pesquisadores) {
            this.lattesService = lattesService;
            this.pesquisadores = pesquisadores;
        }

        @Override
        protected void compute() {
            if (pesquisadores.size() <= MAXIMUM_PROCESS_PER_THREAD) {
                salvarDadosDoLattes();
            } else {
                joinTasks(distributeTasks());
            }
        }

        private void joinTasks(final List<RecursiveAction> actions) {
            for (RecursiveAction action : actions) {
                action.join();
            }
        }

        private List<RecursiveAction> distributeTasks() {
            List<RecursiveAction> actions = new ArrayList<>();
            for (Pesquisador pesquisador : pesquisadores) {
                ParallelProcess action = new ParallelProcess(lattesService, Arrays.asList(pesquisador));
                action.fork();
                actions.add(action);
            }
            return actions;
        }

        private void salvarDadosDoLattes() {
            Pesquisador pesquisador = pesquisadores.get(0);
            try {
                lattesService.salvarDadosLattes(pesquisador);
            } catch (RCMSException e) {
                LOGGER.error(String.format("Erro ao importar dados do pesquisador: %s", pesquisador.getNome()), e);
            }
        }
    }

    private void logThreadsStatuses(final ForkJoinPool pool, final long start, final List<Pesquisador> pesquisadores,
            final ParallelProcess parallelProcess) {
        do {
            System.out.printf("******************************************\n");
            System.out.printf("Main: Parallelism: %d\n", pool.getParallelism());
            System.out.printf("Main: Active Threads: %d\n", pool.getActiveThreadCount());
            System.out.printf("Main: Task Count: %d\n", pool.getQueuedTaskCount());
            System.out.printf("Main: Steal Count: %d\n", pool.getStealCount());
            System.out.printf("******************************************\n");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!parallelProcess.isDone());

        LOGGER.info("Tempo total para importar " + pesquisadores.size() + " currículos: " + (System.currentTimeMillis() - start) + "ms");
    }
}
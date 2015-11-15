package br.ufscar.rcms.job;

import java.util.List;
import java.util.Objects;

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

    @Override
    public void process() {

        if (Boolean.valueOf(configuracaoService.buscarPorTipo(Tipo.IMPORTACAO_LATTES_AUTOMATICA).get(0).getValue())) {
            List<Pesquisador> pesquisadores = pesquisadorService.findToAutoImport();
            pesquisadores.stream().filter(Objects::nonNull).forEach(this::safeProcess);
        }
    }

    private void safeProcess(final Pesquisador pesquisador) {
        try {
            lattesService.salvarDadosLattes(pesquisador);
        } catch (final RCMSException exception) {
            LOGGER.error(String.format("Erro ao importar dados do pesquisador: %s", pesquisador.getNome()), exception);
        }
    }

    @Override
    protected String getJobName() {
        return "LattesImporter";
    }
}
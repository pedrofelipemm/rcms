package br.ufscar.rcms.job;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
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

    @Override
    public void process() {

        // TODO PEDRO TEST
        // safeProcess(null);

        List<Pesquisador> pesquisadores = pesquisadorService.findToAutoImport();
        if (!isEmpty(pesquisadores)) {
            pesquisadores.forEach(this::safeProcess);
        }
    }

    private void safeProcess(final Pesquisador pesquisador) {
        try {
            lattesService.salvarDadosLattes(pesquisador);
        } catch (final RCMSException exception) {
            LOGGER.error(String.format("Erro ao importar dados do pesquisador: ", pesquisador.getNome()), exception);
        }
    }

    @Override
    protected String getJobName() {
        return "LattesImporter";
    }
}
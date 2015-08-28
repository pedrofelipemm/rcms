package br.ufscar.rcms.integration.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.integration.model.entity.Producao;
import br.ufscar.rcms.integration.repository.ProducaoIntegrationRepository;
import br.ufscar.rcms.integration.service.ProducaoIntegrationService;

@Service
@Transactional("integrationTransactionManager")
public class ProducaoIntegrationServiceImpl implements ProducaoIntegrationService {

    @Autowired
    private ProducaoIntegrationRepository producaoIntegrationRepository;

    @Override
    public List<Producao> findAll() {
        List<Producao> producoes = producaoIntegrationRepository.findAll();

        lazyLoadCollections(producoes);

        return producoes;
    }

    private void lazyLoadCollections(final List<Producao> producoes) {
        for (Producao producao : producoes) {
            producao.getAutores().size();
        }
    }

}
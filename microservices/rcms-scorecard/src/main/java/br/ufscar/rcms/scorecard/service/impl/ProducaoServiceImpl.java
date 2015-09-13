package br.ufscar.rcms.scorecard.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.integration.service.ProducaoIntegrationService;
import br.ufscar.rcms.scorecard.model.entity.Producao;
import br.ufscar.rcms.scorecard.repository.ProducaoRepository;
import br.ufscar.rcms.scorecard.rest.commons.dto.AmountProducaoByResearcherDTO;
import br.ufscar.rcms.scorecard.rest.commons.dto.AmountProducaoByYearDTO;
import br.ufscar.rcms.scorecard.service.ProducaoService;

@Service
@Transactional
public class ProducaoServiceImpl implements ProducaoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducaoServiceImpl.class);

    @Autowired
    private ProducaoRepository producaoRepository;

    @Autowired
    private ProducaoIntegrationService producaoIntegrationService;

    @Override
    public List<AmountProducaoByYearDTO> findAmountProducaoByYear() {

        syncronizeData();

        List<Producao> producoes = producaoRepository.findAll();

        return producoes.stream().collect(Collectors.groupingBy(p -> p.getYear(), Collectors.counting()))
                .entrySet().stream().map(e -> new AmountProducaoByYearDTO(e.getValue().intValue(), e.getKey()))
                .filter(Objects::nonNull).collect(Collectors.toList());
    }

    @Override
    public List<AmountProducaoByResearcherDTO> findAmountProducaoByResearcher() {

        syncronizeData();

        List<Producao> producoes = producaoRepository.findAll();

        return producoes.stream().map(e -> e.getAuthors()).filter(Objects::nonNull).flatMap(e -> e.stream())
                .collect(Collectors.groupingBy(e -> e, Collectors.counting())).entrySet().stream()
                .map(e -> new AmountProducaoByResearcherDTO(e.getValue(), e.getKey())).collect(Collectors.toList());
    }

    @Override
    public void syncronizeData() {
        LOGGER.info("BEGIN - syncronizeData()");
        try {
            List<Producao> producoes = producaoRepository.findAll();
            List<Producao> producoesFromIntegration = producaoIntegrationService.findAll().stream()
                    .map(p -> new Producao(p)).collect(Collectors.toList());

            List<Producao> producoesToPersist = producoesFromIntegration.stream().filter(p -> !producoes.contains(p))
                    .collect(Collectors.toList());

            List<Producao> producoesToRemove = producoes.stream().filter(p -> !producoesFromIntegration.contains(p))
                    .collect(Collectors.toList());

            producaoRepository.save(producoesToPersist);
            producaoRepository.delete(producoesToRemove);
        } catch (Exception exception) {
            LOGGER.error("Fail in attemp to syncronize databases!", exception);
        }
        LOGGER.info("END - syncronizeData()");
    }
}
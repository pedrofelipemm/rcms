package br.ufscar.rcms.scorecard.service;

import java.util.List;

import br.ufscar.rcms.scorecard.rest.commons.dto.AmountProducaoByResearcherDTO;
import br.ufscar.rcms.scorecard.rest.commons.dto.AmountProducaoByYearDTO;

public interface ProducaoService {

    List<AmountProducaoByYearDTO> findAmountProducaoByYear();

    List<AmountProducaoByResearcherDTO> findAmountProducaoByResearcher();

    void syncronizeData();
}
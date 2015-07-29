package br.ufscar.rcms.scorecard.service;

import java.util.List;

import br.ufscar.rcms.scorecard.rest.dto.AmountProducaoByYearDTO;

public interface ProducaoService {

    List<AmountProducaoByYearDTO> findAll();

}
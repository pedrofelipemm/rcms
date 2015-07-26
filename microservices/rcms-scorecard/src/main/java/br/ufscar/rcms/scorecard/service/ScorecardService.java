package br.ufscar.rcms.scorecard.service;

import java.util.List;

import br.ufscar.rcms.scorecard.model.entity.ProducaoPesquisador;

public interface ScorecardService {

    List<ProducaoPesquisador> findAll();

}
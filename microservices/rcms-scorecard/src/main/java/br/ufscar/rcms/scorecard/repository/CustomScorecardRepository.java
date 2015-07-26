package br.ufscar.rcms.scorecard.repository;

import java.util.List;

import br.ufscar.rcms.scorecard.model.entity.ProducaoPesquisador;

public interface CustomScorecardRepository {

    List<ProducaoPesquisador> findAll();
}
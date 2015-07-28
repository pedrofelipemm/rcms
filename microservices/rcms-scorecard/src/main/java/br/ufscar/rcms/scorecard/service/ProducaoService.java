package br.ufscar.rcms.scorecard.service;

import java.util.List;

import br.ufscar.rcms.scorecard.model.entity.Producao;

public interface ProducaoService {

    List<Producao> findAll();

}
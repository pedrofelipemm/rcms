package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.Pesquisador;

public interface PesquisadorDAO extends BaseDAO<Pesquisador, Long> {

    List<Pesquisador> buscarTodosOrderByNome();

    List<Pesquisador> buscarTodosComIdioma(Long idIdioma);

}
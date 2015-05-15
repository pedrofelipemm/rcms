package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.ProjetoPesquisa;

public interface ProjetoPesquisaDAO extends BaseDAO<ProjetoPesquisa, Long> {

    List<ProjetoPesquisa> buscarTodosOrderByNome();

}
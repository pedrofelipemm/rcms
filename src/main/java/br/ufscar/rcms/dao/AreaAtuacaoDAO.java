package br.ufscar.rcms.dao;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;

public interface AreaAtuacaoDAO extends BaseDAO<AreaAtuacao, Long> {

    AreaAtuacao buscarPorDescricao(String Descricao);
}

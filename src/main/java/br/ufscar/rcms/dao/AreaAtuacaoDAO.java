package br.ufscar.rcms.dao;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;

public interface AreaAtuacaoDAO extends BaseDAO<AreaAtuacao, Long> {

    public abstract AreaAtuacao buscarPorDescricao(String Descricao);
}

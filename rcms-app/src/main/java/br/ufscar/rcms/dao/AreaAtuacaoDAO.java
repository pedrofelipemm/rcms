package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;

public interface AreaAtuacaoDAO extends BaseDAO<AreaAtuacao, Long> {

	List<AreaAtuacao> buscarPorDescricao(String Descricao);
}

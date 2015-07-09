package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;

public interface GrandeAreaAtuacaoDAO extends BaseDAO<GrandeAreaAtuacao, Long> {

	List<GrandeAreaAtuacao> buscarPorDescricao(String Descricao); 

}

package br.ufscar.rcms.dao;

import br.ufscar.rcms.modelo.entidades.EspecializacaoAreaAtuacao;

public interface EspecializacaoAreaAtuacaoDAO extends BaseDAO<EspecializacaoAreaAtuacao, Long> {
	void removerEx(EspecializacaoAreaAtuacao espec);
}

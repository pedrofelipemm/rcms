package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.OrganizacaoEvento;

public interface OrganizacaoEventoDAO extends BaseDAO<OrganizacaoEvento, Long>{

	void remover(List<OrganizacaoEvento> organizacaoEventos);

}

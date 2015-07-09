package br.ufscar.rcms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.OrganizacaoEventoDAO;
import br.ufscar.rcms.modelo.entidades.OrganizacaoEvento;

@Repository
public class OrganizacaoEventoDAOImpl extends BaseDAOImpl<OrganizacaoEvento, Long> implements OrganizacaoEventoDAO {

	private static final long serialVersionUID = 90850461828277889L;
	
	public OrganizacaoEventoDAOImpl(){
		setClazz(OrganizacaoEvento.class);
	}

	@Override
	public void remover(List<OrganizacaoEvento> organizacaoEventos) {
		for (OrganizacaoEvento organizacao : organizacaoEventos) {
            remover(organizacao);
        }
	}

}

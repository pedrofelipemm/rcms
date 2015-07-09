package br.ufscar.rcms.servico.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufscar.rcms.dao.OrganizacaoEventoDAO;
import br.ufscar.rcms.modelo.entidades.OrganizacaoEvento;
import br.ufscar.rcms.servico.OrganizacaoEventoService;

@Service("organizacaoEventoService")
@Transactional
public class OrganizacaoEventoServiceImpl implements OrganizacaoEventoService {

	private static final long serialVersionUID = 3950630105662187699L;

	@Autowired
	private OrganizacaoEventoDAO organizacaoEventoDAO;
	
	@Override
	public void salvar(OrganizacaoEvento organizacaoEvento) {
		organizacaoEventoDAO.salvar(organizacaoEvento);
	}

	@Override
	public void salvarOuAtualizar(OrganizacaoEvento organizacaoEvento) {
		organizacaoEventoDAO.salvarOuAtualizar(organizacaoEvento);
	}
	
	@Override
	public void remover(List<OrganizacaoEvento> organizacaoEventos){
		organizacaoEventoDAO.remover(organizacaoEventos);
	}

	@Override
	public void remover(OrganizacaoEvento organizacaoEvento) {
		organizacaoEventoDAO.remover(organizacaoEvento);
	}

	@Override
	public List<OrganizacaoEvento> buscarTodas() {
		return organizacaoEventoDAO.buscarTodos();
	}

}

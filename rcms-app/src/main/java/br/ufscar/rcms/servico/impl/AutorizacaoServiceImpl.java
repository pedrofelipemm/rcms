package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.AutorizacaoDAO;
import br.ufscar.rcms.modelo.entidades.Autorizacao;
import br.ufscar.rcms.servico.AutorizacaoService;
import br.ufscar.rcms.servico.exception.RCMSException;

@Service("autorizacaoService")
@Transactional(rollbackFor = RCMSException.class)
public class AutorizacaoServiceImpl implements AutorizacaoService {

    
	private static final long serialVersionUID = -3782026195564982152L;

	@Autowired
    private AutorizacaoDAO autorizacaoDAO;

    
	@Override
	public List<Autorizacao> buscarTodos() {
		return autorizacaoDAO.buscarTodos();
	}


	@Override
	public Autorizacao salvar(Autorizacao autorizacao) {
		return autorizacaoDAO.salvarOuAtualizar(autorizacao);
	}


	@Override
	public Autorizacao buscarAutorizacao(Long id) {
		return autorizacaoDAO.buscar(id);
	}

   
}
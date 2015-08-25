package br.ufscar.rcms.servico.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufscar.rcms.dao.EspecializacaoAreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.EspecializacaoAreaAtuacao;
import br.ufscar.rcms.servico.EspecializacaoAreaAtuacaoService;

@Service("especializacaoAreaAtuacaoService")
@Transactional
public class EspecializacaoAreaAtuacaoServiceImpl implements EspecializacaoAreaAtuacaoService{
	
	private static final long serialVersionUID = -9189071056593579455L;
	
	@Autowired
	private EspecializacaoAreaAtuacaoDAO especializacaoDAO;

	@Override
	public List<EspecializacaoAreaAtuacao> buscarTodas() {
		return especializacaoDAO.buscarTodos();
	}

	@Override
	public void remover(EspecializacaoAreaAtuacao espec) {
		especializacaoDAO.removerEx(espec);		
	}

	@Override
	public void salvar(EspecializacaoAreaAtuacao espec) {
		especializacaoDAO.salvarOuAtualizar(espec);		
	}

}

package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.AreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.servico.AreaAtuacaoService;

@Service("areaAtuacaoService")
@Transactional
public class AreaAtuacaoServiceImpl implements AreaAtuacaoService {
	
	@Autowired
	private AreaAtuacaoDAO areaDAO;

	@Override
	public void Salvar(AreaAtuacao area) {
		areaDAO.salvar(area);		
	}

	@Override
	public void Alterar(AreaAtuacao area) {
		areaDAO.atualizar(area);
	}

	@Override
	public List<AreaAtuacao> BuscarTodas() {
		return areaDAO.buscarTodos();		
	}
	
	public AreaAtuacao BuscarPorDescricao(String Descricao){
		return areaDAO.BuscarPorDescricao(Descricao);
	}

}

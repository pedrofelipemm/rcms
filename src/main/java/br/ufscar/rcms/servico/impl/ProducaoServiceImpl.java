package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.ProducaoDAO;
import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.servico.ProducaoService;

@Service("publicacaoService")
@Transactional
public class ProducaoServiceImpl implements ProducaoService{

	private static final long serialVersionUID = -3551699743568411854L;
	
	@Autowired
	private ProducaoDAO publicacaoDAO;
		
	@Override
	public void saveOrUpdate(Producao producao) {
		publicacaoDAO.saveOrUpdate(producao);
	}

	@Override
	public void remove(Producao producao) {
		publicacaoDAO.remover(producao);
	}

	@Override
	public List<Producao> buscarTodas() {
		return publicacaoDAO.buscarTodos();
	}

	@Override
	public Producao buscarPorId(Long id) {
		Producao p = publicacaoDAO.buscar(id);
		loadLazyDependencies(p);
		return p;
	}
	
	private void loadLazyDependencies(Producao p){
		p.getCitacaoBibliograficas().size();
	}

}
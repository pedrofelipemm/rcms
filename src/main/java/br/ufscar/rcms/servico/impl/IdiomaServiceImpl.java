package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.dao.IdiomaDAO;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.servico.IdiomaService;

@Service("idiomaService")
@Transactional
public class IdiomaServiceImpl implements IdiomaService {
	
	@Autowired
	private IdiomaDAO idiomaDAO;
	
	@Override
	public void Salvar(Idioma idioma) {
		idiomaDAO.salvar(idioma);	
	}

	@Override
	public void Alterar(Idioma idioma) {
		idiomaDAO.atualizar(idioma);
	}

	@Override
	public List<Idioma> BuscarTodas() {
		return idiomaDAO.buscarTodos();
	}

	@Override
	public Idioma BuscarPorDescricao(String Descricao) {
		return idiomaDAO.BuscarPorDescricao(Descricao);
	}

}

package br.ufscar.rcms.servico.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.dao.FormacaoAcademicaDAO;
import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;
import br.ufscar.rcms.servico.FormacaoAcademicaService;

/**
 * 
 * @author joaogenari
 *
 */
public class FormacaoAcademicaServiceImpl implements FormacaoAcademicaService{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private FormacaoAcademicaDAO formacaoAcademicaDao;

	@Override
	public void salvar(FormacaoAcademica formacaoAcademica) {
		formacaoAcademicaDao.salvar(formacaoAcademica);
	}
	
	public List<FormacaoAcademica> buscarTodos(){
		return formacaoAcademicaDao.buscarTodos();
	}

}

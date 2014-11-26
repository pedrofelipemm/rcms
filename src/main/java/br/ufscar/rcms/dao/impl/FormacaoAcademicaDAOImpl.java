package br.ufscar.rcms.dao.impl;

import br.ufscar.rcms.dao.FormacaoAcademicaDAO;
import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;

public class FormacaoAcademicaDAOImpl extends BaseDAOImpl<FormacaoAcademica, Integer> implements FormacaoAcademicaDAO{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void salvar(FormacaoAcademica formacaoAcademica) {
		super.salvar(formacaoAcademica);
	}
	
	@Override
	public FormacaoAcademica buscarFormacaoAcademica(Integer idPesquisadorFormacao) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}

package br.ufscar.rcms.dao;

import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;

public interface FormacaoAcademicaDAO extends BaseDAO<FormacaoAcademica, Integer> {
	
	FormacaoAcademica buscarFormacaoAcademica(Integer idPesquisadorFormacao);
	
}

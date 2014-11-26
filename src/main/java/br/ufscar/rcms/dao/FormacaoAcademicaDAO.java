package br.ufscar.rcms.dao;

import java.io.Serializable;

import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;

public interface FormacaoAcademicaDAO extends Serializable{
	
	FormacaoAcademica buscarFormacaoAcademica(Integer idPesquisadorFormacao);
	
}

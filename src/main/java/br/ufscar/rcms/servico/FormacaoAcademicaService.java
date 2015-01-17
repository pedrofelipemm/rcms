package br.ufscar.rcms.servico;

import java.io.Serializable;

import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;

public interface FormacaoAcademicaService extends Serializable{

	void salvar(FormacaoAcademica formacaoAcademica);
}

package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;

public interface FormacaoAcademicaService extends Serializable{

	void salvar(FormacaoAcademica formacaoAcademica);

    List<FormacaoAcademica> buscarFormacaoAcademica(Long idPesquisador);

    List<FormacaoAcademica> buscarTodos();
}
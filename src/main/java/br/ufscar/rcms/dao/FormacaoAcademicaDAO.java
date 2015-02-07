package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;

public interface FormacaoAcademicaDAO extends BaseDAO<FormacaoAcademica, Integer> {

    List<FormacaoAcademica> buscarFormacaoAcademica(Long idPesquisador);
}
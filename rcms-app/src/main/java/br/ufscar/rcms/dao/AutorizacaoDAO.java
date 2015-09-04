package br.ufscar.rcms.dao;

import br.ufscar.rcms.modelo.entidades.Autorizacao;

public interface AutorizacaoDAO extends BaseDAO<Autorizacao, Long> {

    Autorizacao findByRole(String role);

}
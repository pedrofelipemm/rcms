package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;

public interface CitacaoBibliograficaDAO extends BaseDAO<CitacaoBibliografica, Long> {

    public void remover(List<CitacaoBibliografica> citacoesBibliograficas);
}

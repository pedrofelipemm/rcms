package br.ufscar.rcms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.CitacaoBibliograficaDAO;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;

@Repository
public class CitacaoBibliograficaDAOImpl extends BaseDAOImpl<CitacaoBibliografica, Long> implements
        CitacaoBibliograficaDAO {

    private static final long serialVersionUID = 8690233515966857478L;

    @Override
    public void remover(List<CitacaoBibliografica> citacoesBibliograficas) {
        for (CitacaoBibliografica citacaoBibliografica : citacoesBibliograficas) {
            remover(citacaoBibliografica);
        }
    }
}

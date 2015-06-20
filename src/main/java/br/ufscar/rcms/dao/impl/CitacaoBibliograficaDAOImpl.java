package br.ufscar.rcms.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.CitacaoBibliograficaDAO;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;

@Repository
public class CitacaoBibliograficaDAOImpl extends BaseDAOImpl<CitacaoBibliografica, Long> implements
        CitacaoBibliograficaDAO {

    private static final long serialVersionUID = 8690233515966857478L;

    public CitacaoBibliografica buscarPorNomeCitacao(String nomeCitacao) {
        Query q = getEntityManager().createQuery("SELECT c from CitacaoBibliografica AS c WHERE c.nomeCitacao = ':n'");
        q.setParameter("n", nomeCitacao);

        try {
            Object c = q.getResultList();
            if (c != null)
                return (CitacaoBibliografica) c;
            else
                return null;
        } catch (NoResultException noResultException) {
            return null;
        }
    }

    @Override
    public void remover(List<CitacaoBibliografica> citacoesBibliograficas) {
        for (CitacaoBibliografica citacaoBibliografica : citacoesBibliograficas) {
            remover(citacaoBibliografica);
        }
    }
}

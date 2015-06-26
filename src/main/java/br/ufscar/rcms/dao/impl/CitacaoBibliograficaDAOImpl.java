package br.ufscar.rcms.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.CitacaoBibliograficaDAO;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;

@Repository
public class CitacaoBibliograficaDAOImpl extends BaseDAOImpl<CitacaoBibliografica, Long> implements
        CitacaoBibliograficaDAO {

    private static final long serialVersionUID = 8690233515966857478L;
    private static final Logger LOGGER = LoggerFactory.getLogger(CitacaoBibliograficaDAOImpl.class);

    @Override
    public CitacaoBibliografica buscarPorNomeCitacao(final String nomeCitacao) {
        Query q = getEntityManager().createQuery("SELECT c from CitacaoBibliografica AS c WHERE c.nomeCitacao = :n");
        q.setParameter("n", nomeCitacao);

        try {
            return (CitacaoBibliografica) q.getSingleResult();
        } catch (NoResultException noResultException) {
            LOGGER.debug("Nenhuma citacao encontrada com o nome \"%s\"", nomeCitacao, noResultException);
            return null;
        }
    }

    @Override
    public void remover(final List<CitacaoBibliografica> citacoesBibliograficas) {
        for (CitacaoBibliografica citacaoBibliografica : citacoesBibliograficas) {
            remover(citacaoBibliografica);
        }
    }
}

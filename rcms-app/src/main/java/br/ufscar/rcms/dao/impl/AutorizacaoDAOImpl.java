package br.ufscar.rcms.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.AutorizacaoDAO;
import br.ufscar.rcms.modelo.entidades.Autorizacao;

@Repository
public class AutorizacaoDAOImpl extends BaseDAOImpl<Autorizacao, Long> implements AutorizacaoDAO {

    private static final long serialVersionUID = 32735639850047914L;

    private static final Logger LOGGER = LoggerFactory.getLogger(AutorizacaoDAOImpl.class);

    public AutorizacaoDAOImpl() {
        setClazz(Autorizacao.class);
    }

    @Override
    public Autorizacao findByRole(final String role) {

        String jpql = "SELECT e From " + Autorizacao.class.getName() + " AS e WHERE e.nomeAutorizacao = :nome";

        Query query = getEntityManager().createQuery(jpql);

        query.setParameter("nome", role);

        try {
            return (Autorizacao) query.getSingleResult();
        } catch (NoResultException noResultException) {
            LOGGER.debug("Nenhuma autorização encontrada, nome autorização: {0}", role, noResultException);
            return null;
        }
    }
}
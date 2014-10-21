package br.ufscar.rcms.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.IdiomaDAO;
import br.ufscar.rcms.modelo.entidades.Idioma;

@Repository
public class IdiomaDAOImpl extends BaseDAOImpl<Idioma, Long> implements IdiomaDAO {

    private static final long serialVersionUID = 32735639850047914L;

    public IdiomaDAOImpl() {
        setClazz(Idioma.class);
    }

    @Override
    public Idioma buscarPorDescricao(String Descricao) {
        Query q = getEntityManager().createQuery("SELECT a from Idioma AS a WHERE a.descricao = :d");
        q.setParameter("d", Descricao);

        try {
            return (Idioma) q.getSingleResult();
        } catch (NoResultException noResultException) {
            return null;
        }
    }
}

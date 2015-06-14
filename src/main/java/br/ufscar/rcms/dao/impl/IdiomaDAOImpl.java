package br.ufscar.rcms.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.IdiomaDAO;
import br.ufscar.rcms.modelo.entidades.Idioma;

@Repository
public class IdiomaDAOImpl extends BaseDAOImpl<Idioma, Long> implements IdiomaDAO {

	private static final long serialVersionUID = 32735639850047914L;
    private static final Logger LOGGER = LoggerFactory.getLogger(IdiomaDAOImpl.class);

    // TODO PEDRO
	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(final Session session) {
		this.session = session;
	}

	public IdiomaDAOImpl() {
		setClazz(Idioma.class);
	}

    @Override
    public Idioma buscarPorDescricao(final String descricao) {
        Query q = getEntityManager().createQuery("SELECT a from Idioma AS a WHERE a.descricao = :d");
        q.setParameter("d", descricao);

        try {
            return (Idioma) q.getSingleResult();
        } catch (NoResultException noResultException) {
            LOGGER.debug("Nenhum idioma encontrado, descrição: %s", descricao, noResultException);
            return null;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Idioma> listar() {
		return session.createCriteria(Idioma.class).list();
	}

}

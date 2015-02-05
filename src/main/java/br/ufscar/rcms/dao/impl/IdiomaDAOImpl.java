package br.ufscar.rcms.dao.impl;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.IdiomaDAO;
import br.ufscar.rcms.modelo.entidades.Idioma;

@Repository
public class IdiomaDAOImpl extends BaseDAOImpl<Idioma, Long> implements
		IdiomaDAO {

	private static final long serialVersionUID = 32735639850047914L;
	private Session session;

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public IdiomaDAOImpl() {
		setClazz(Idioma.class);
	}

	@Override
	public Idioma buscarPorDescricao(String Descricao) {
		Query q = getEntityManager().createQuery(
				"SELECT a from Idioma AS a WHERE a.descricao = :d");
		q.setParameter("d", Descricao);

		try {
			return (Idioma) q.getSingleResult();
		} catch (NoResultException noResultException) {
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Idioma> listar() {
		return this.session.createCriteria(Idioma.class).list();
	}

}

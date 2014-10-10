package br.ufscar.rcms.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.IdiomaDAO;
import br.ufscar.rcms.modelo.entidades.Idioma;

@Repository
public class IdiomaDAOImpl extends BaseDAOImpl<Idioma, Long> implements IdiomaDAO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 32735639850047914L;

	public IdiomaDAOImpl(){
		setClazz(Idioma.class);
	}

	@Override
	public Idioma BuscarPorDescricao(String Descricao) {
		Query q = entityManager
				.createQuery("SELECT a from Idioma AS a WHERE a.descricao = :d");
		q.setParameter("d", Descricao);

		List<Idioma> resultList = q.getResultList();

		if (resultList.isEmpty())
			return null;
		else
			return resultList.get(0);
	}
}

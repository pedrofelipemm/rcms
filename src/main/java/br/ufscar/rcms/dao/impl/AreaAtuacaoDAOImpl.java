package br.ufscar.rcms.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.AreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.AreaAtuacao;

@Repository
public class AreaAtuacaoDAOImpl extends BaseDAOImpl<AreaAtuacao, Long>
		implements AreaAtuacaoDAO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4170408904792442435L;

	public AreaAtuacaoDAOImpl() {

		setClazz(AreaAtuacao.class);
	}

	@Override
	public AreaAtuacao BuscarPorDescricao(String Descricao) {
		Query q = entityManager
				.createQuery("SELECT a from AreaAtuacao AS a WHERE a.descricao = :d");
		q.setParameter("d", Descricao);

		List<AreaAtuacao> resultList = q.getResultList();

		if (resultList.isEmpty())
			return null;
		else
			return resultList.get(0);
	}

}

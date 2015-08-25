package br.ufscar.rcms.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.EspecializacaoAreaAtuacaoDAO;
import br.ufscar.rcms.modelo.entidades.EspecializacaoAreaAtuacao;

@Repository
public class EspecializacaoAreaAtuacaoDAOImpl extends BaseDAOImpl<EspecializacaoAreaAtuacao, Long> implements EspecializacaoAreaAtuacaoDAO {

	private static final long serialVersionUID = 2390204273078379409L;
	
	public EspecializacaoAreaAtuacaoDAOImpl(){
		setClazz(EspecializacaoAreaAtuacao.class);
	}
	
	public void removerEx(EspecializacaoAreaAtuacao espec){
		Query q = getEntityManager().createQuery("delete from EspecializacaoAreaAtuacao e where e.idEspecializacao = :d");
		q.setParameter("d", espec.getIdEspecializacao());
		
		q.executeUpdate();
	}

}

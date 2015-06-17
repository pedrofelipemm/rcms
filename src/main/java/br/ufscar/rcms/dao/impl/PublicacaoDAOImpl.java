package br.ufscar.rcms.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.PublicacaoDAO;
import br.ufscar.rcms.modelo.entidades.Producao;

@Repository
public class PublicacaoDAOImpl extends BaseDAOImpl<Producao, Long> implements PublicacaoDAO {

	private static final long serialVersionUID = 5464576921352357099L;
	
	public PublicacaoDAOImpl(){
		setClazz(Producao.class);
	}

}

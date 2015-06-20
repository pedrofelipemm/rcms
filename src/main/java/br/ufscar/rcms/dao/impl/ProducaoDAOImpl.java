package br.ufscar.rcms.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.ProducaoDAO;
import br.ufscar.rcms.modelo.entidades.Producao;

@Repository
public class ProducaoDAOImpl extends BaseDAOImpl<Producao, Long> implements ProducaoDAO {

	private static final long serialVersionUID = 5464576921352357099L;
	
	public ProducaoDAOImpl(){
		setClazz(Producao.class);
	}

}

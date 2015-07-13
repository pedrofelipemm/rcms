package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.Producao;

public interface ProducaoDAO extends BaseDAO<Producao, Long>{

    List<ArtigoEmPeriodico> buscarArtigosEmPeriodicos(Long idUsuario);
	
}

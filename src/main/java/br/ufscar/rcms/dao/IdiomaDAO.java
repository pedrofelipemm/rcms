package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.Idioma;

public interface IdiomaDAO extends BaseDAO<Idioma, Long> {

	Idioma buscarPorDescricao(String Descricao);
	
	List <Idioma> listar ();

}

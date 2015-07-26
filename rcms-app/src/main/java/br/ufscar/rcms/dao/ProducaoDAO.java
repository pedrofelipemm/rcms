package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.Producao;

public interface ProducaoDAO extends BaseDAO<Producao, Long>{

    Boolean exists(String titulo, Integer ano);

    <T> List<T> buscarProducoes(Class<T> clazz, final Long idUsuario);
}

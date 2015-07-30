package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.Producao;

public interface ProducaoService extends Serializable {

    void saveOrUpdate(Producao producao);

    void remove(Producao producao);

    List<Producao> buscarTodas();

    Producao buscarPorId(Long id);

    Boolean exists(String titulo, Integer ano);

    <T> List<T> buscarProducoes(Class<T> clazz);

    <T> List<T> buscarProducoes(Class<T> clazz, final Long idUsuario);
}
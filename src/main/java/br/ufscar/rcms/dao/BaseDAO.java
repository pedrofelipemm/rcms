package br.ufscar.rcms.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T, K extends Serializable> extends Serializable {

    T buscar(Long id);

    List<T> buscarTodos();

    Long count();

    void salvar(T entidade);

    T atualizar(T entidade);

    T salvarOuAtualizar(T entidade);

    void remover(T entidade);

    void remover(Long id);
}

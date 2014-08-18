package br.ufscar.rcms.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDAO<T, K extends Serializable> extends Serializable {

	public abstract T buscar(Long id);

	public abstract List<T> buscarTodos();

	public abstract Long count();

	public abstract void salvar(T entidade);

	public abstract void atualizar(T entidade);

	public abstract void remover(T entidade);

	public abstract void remover(Long id);
}

package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.exception.PesquisadorNaoEncontradoException;

public interface PesquisadorService extends Serializable {

    void salvar(Pesquisador pesquisador);

    Pesquisador salvarOuAtualizar(Pesquisador pesquisador);

    Pesquisador buscar(Long id);

    List<Pesquisador> buscarTodos();

    List<Pesquisador> buscarTodosOrderByNome();

    void remover(Pesquisador pesquisador) throws PesquisadorNaoEncontradoException;

    void remover(Long id) throws PesquisadorNaoEncontradoException;

    Pesquisador buscarTodosDados(Long idUsuario);
}
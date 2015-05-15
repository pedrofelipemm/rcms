package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.ProjetoPesquisa;
import br.ufscar.rcms.servico.exception.ProjetoPesquisaNaoEncontradoException;

public interface ProjetoPesquisaService extends Serializable {

    void salvar(ProjetoPesquisa projetoPesquisa);

    ProjetoPesquisa salvarOuAtualizar(ProjetoPesquisa projetoPesquisa);

    ProjetoPesquisa buscar(Long id);

    List<ProjetoPesquisa> buscarTodos();

    List<ProjetoPesquisa> buscarTodosOrderByNome();

    void remover(ProjetoPesquisa projetoPesquisa) throws ProjetoPesquisaNaoEncontradoException;

    void remover(Long id) throws ProjetoPesquisaNaoEncontradoException;

    ProjetoPesquisa buscarTodosDados(Long idUsuario);
}
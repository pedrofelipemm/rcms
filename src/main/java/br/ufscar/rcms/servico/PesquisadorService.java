package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.Pesquisador;

public interface PesquisadorService extends Serializable {

    void salvar(Pesquisador pesquisador);

    Pesquisador salvarOuAtualizar(Pesquisador pesquisador);

    Pesquisador buscar(Long id);

    List<Pesquisador> buscarTodos();

    void remover(Pesquisador pesquisador);

    void remover(Long id);

    Pesquisador buscarTodosDados(Long idUsuario);
}
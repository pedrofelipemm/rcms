package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.TransientFile;
import br.ufscar.rcms.servico.exception.PesquisadorNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;

public interface PesquisadorService extends Serializable {

    Pesquisador salvarOuAtualizar(Pesquisador pesquisador) throws RCMSException;

    Pesquisador buscar(Long id);

    List<Pesquisador> buscarTodos();

    List<Pesquisador> buscarTodosOrderByNome();

    List<Pesquisador> buscarTodosComIdioma(Long idIdioma);

    void remover(Pesquisador pesquisador) throws PesquisadorNaoEncontradoException;

    void remover(Long id) throws PesquisadorNaoEncontradoException;

    Pesquisador buscarTodosDados(Long idUsuario);

    TransientFile buscarFoto(Pesquisador pesquisador);

    TransientFile buscarFoto(Long idUsuario);

    void salvarFotoPesquisador(Pesquisador pesquisador) throws RCMSException;

    Pesquisador buscarPorLogin(String login);

    void saveOrUpdate(Pesquisador pesquisador);
}
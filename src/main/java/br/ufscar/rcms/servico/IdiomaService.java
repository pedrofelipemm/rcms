package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.servico.exception.IdiomaNaoEncontradoException;

public interface IdiomaService extends Serializable {

    void salvar(Idioma idioma);

    void alterar(Idioma idioma);

    void remover(Idioma idioma) throws IdiomaNaoEncontradoException;

    List<Idioma> buscarTodos();

    Idioma buscarPorDescricao(String Descricao);

}

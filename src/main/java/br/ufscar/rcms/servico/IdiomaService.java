package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.servico.exception.IdiomaNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;

public interface IdiomaService extends Serializable {

    void saveOrUpdate(Idioma idioma) throws RCMSException;

    void remover(Idioma idioma) throws IdiomaNaoEncontradoException;

    List<Idioma> buscarTodos();

    Idioma buscarPorDescricao(String Descricao);
}
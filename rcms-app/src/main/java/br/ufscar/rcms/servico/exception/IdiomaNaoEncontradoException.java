package br.ufscar.rcms.servico.exception;

public class IdiomaNaoEncontradoException extends RCMSException {

    private static final long serialVersionUID = -1876779188012245246L;

    public IdiomaNaoEncontradoException(final String descricao) {
        super("Idioma '" + descricao + "' não encontrado!");
    }
}
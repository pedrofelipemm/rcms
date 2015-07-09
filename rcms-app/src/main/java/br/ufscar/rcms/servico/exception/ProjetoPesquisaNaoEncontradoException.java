package br.ufscar.rcms.servico.exception;

public class ProjetoPesquisaNaoEncontradoException extends RCMSException {

    private static final long serialVersionUID = 6359380258354609264L;

    public ProjetoPesquisaNaoEncontradoException(final Number id) {
        super("Projeto de pesquisa não encontrado! ID: " + id);
    }
}
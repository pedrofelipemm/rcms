package br.ufscar.rcms.servico.exception;

public class PesquisadorNaoEncontradoException extends RCMSException {

    private static final long serialVersionUID = 6359380258354609264L;

    public PesquisadorNaoEncontradoException(final Number id) {
        super("Pesquisador não encontrado! ID: " + id);
    }
}
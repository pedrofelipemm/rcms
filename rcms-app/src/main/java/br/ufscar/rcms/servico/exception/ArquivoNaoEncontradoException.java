package br.ufscar.rcms.servico.exception;

public class ArquivoNaoEncontradoException extends RCMSException {

    private static final long serialVersionUID = -8510691271382153284L;

    public ArquivoNaoEncontradoException(final String arquivo) {
        super("Arquivo não encontrado: " + arquivo);
    }
}
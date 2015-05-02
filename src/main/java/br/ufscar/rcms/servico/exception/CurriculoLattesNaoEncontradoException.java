package br.ufscar.rcms.servico.exception;

public class CurriculoLattesNaoEncontradoException extends RCMSException {

    private static final long serialVersionUID = 6359380258354609264L;

    public CurriculoLattesNaoEncontradoException(final String codigoLattes) {
        super("Currículo Lattes não encontrado para o código lattes: " + codigoLattes);
    }
}
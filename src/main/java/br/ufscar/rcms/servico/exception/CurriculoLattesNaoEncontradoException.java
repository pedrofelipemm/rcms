package br.ufscar.rcms.servico.exception;

public class CurriculoLattesNaoEncontradoException extends Exception {

    private static final long serialVersionUID = 6359380258354609264L;

    public CurriculoLattesNaoEncontradoException(final String codigoLattes) {
        super("Curr�culo Lattes n�o encontrado para o c�digo lattes: " + codigoLattes);
    }
}

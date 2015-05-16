package br.ufscar.rcms.servico.exception;

import java.util.List;
import java.util.stream.Collectors;

import br.ufscar.rcms.modelo.entidades.Pesquisador;

public class IdiomaEmUsoException extends RCMSException{

    private static final long serialVersionUID = 4667618302407268820L;

    public IdiomaEmUsoException(final List<Pesquisador> pesquisadores) {
        super("Idioma usado pelos pesquisadores:\n"
                + pesquisadores.stream().map(Pesquisador::getNome).collect(Collectors.joining("\n")));
    }
}
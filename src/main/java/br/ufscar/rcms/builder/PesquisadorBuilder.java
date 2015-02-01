package br.ufscar.rcms.builder;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.lattes.PesquisadorLattes;

public class PesquisadorBuilder implements Builder<Pesquisador> {

    private Pesquisador pesquisador;

    public PesquisadorBuilder(PesquisadorLattes pesquisador) {

    }

    public PesquisadorBuilder pesquisador() {

        return this;
    }

    @Override
    public Pesquisador build() {
        return pesquisador;
    }
}
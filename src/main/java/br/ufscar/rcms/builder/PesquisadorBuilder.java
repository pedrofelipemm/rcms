package br.ufscar.rcms.builder;

import br.ufscar.rcms.modelo.entidades.Pesquisador;

public class PesquisadorBuilder implements Builder<Pesquisador> {

    private Pesquisador pesquisador;

    public PesquisadorBuilder() {
        // TODO Auto-generated constructor stub
    }

    @Override
    public Pesquisador build() {
        return pesquisador;
   }
}
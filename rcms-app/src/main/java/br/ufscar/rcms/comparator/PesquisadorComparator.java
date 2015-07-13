package br.ufscar.rcms.comparator;

import java.util.Comparator;

import br.ufscar.rcms.modelo.entidades.Pesquisador;

public class PesquisadorComparator implements Comparator<Pesquisador> {

    @Override
    public int compare(final Pesquisador pesquisador1, final Pesquisador pesquisador2) {
        return pesquisador1.getNome().compareTo(pesquisador2.getNome());
    }
}
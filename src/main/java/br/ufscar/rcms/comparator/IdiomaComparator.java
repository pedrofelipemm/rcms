package br.ufscar.rcms.comparator;

import java.util.Comparator;

import br.ufscar.rcms.modelo.entidades.Idioma;

public class IdiomaComparator implements Comparator<Idioma> {

    @Override
    public int compare(final Idioma idioma1, final Idioma idioma2) {
        return idioma1.getDescricao().compareTo(idioma2.getDescricao());
    }

}
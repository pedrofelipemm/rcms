package br.ufscar.rcms.commons.util;

import java.util.Arrays;
import java.util.Date;

import br.ufscar.rcms.scorecard.model.entity.Producao;

public abstract class Fixture {

    public static Producao newProducao() {
        return new Producao("title", Arrays.asList("author1"), 2000, new Date());
    }
}
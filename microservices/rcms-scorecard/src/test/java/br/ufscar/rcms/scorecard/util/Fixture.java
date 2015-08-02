package br.ufscar.rcms.scorecard.util;

import br.ufscar.rcms.scorecard.model.entity.CitacaoBibliografica;
import br.ufscar.rcms.scorecard.model.entity.Pesquisador;
import br.ufscar.rcms.scorecard.model.entity.Producao;

public class Fixture {

    public static Pesquisador newPesquisador() {
        final Pesquisador pesquisador = new Pesquisador();
        pesquisador.setCodigoLattes("codigo-lattes-default");
        pesquisador.setEmail("email-default@email.com");
        pesquisador.setLogin("login-default");
        pesquisador.setNome("nome-default");
        pesquisador.setResumoProfissional("resumo-profissional-default");
        pesquisador.setSenha("senha-default");
        return pesquisador;
    }

    public static Producao newProducao() {
        final Producao producao = new Producao();
        producao.setAno(2000);
        producao.setLink("www.link-default.com.br");
        producao.setPdf(new byte[2]);
        producao.setTitulo("Producao Default");
        return producao;
    }

    public static CitacaoBibliografica newCitacao() {
        final CitacaoBibliografica citacao = new CitacaoBibliografica();
        citacao.setNomeCitacao("Citação Default");
        return citacao;
    }
}
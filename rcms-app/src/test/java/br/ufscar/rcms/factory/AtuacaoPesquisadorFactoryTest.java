package br.ufscar.rcms.factory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import br.ufscar.rcms.modelo.entidades.AtuacaoPesquisador;
import br.ufscar.rcms.modelo.entidades.Pesquisador;

public class AtuacaoPesquisadorFactoryTest {

    private String descricaoCompleta = "Grande área: Ciências Exatas e da Terra / Área: Ciência da Computação / Subárea: Metodologia e Técnicas da Computação/Especialidade: Engenharia de Software.";
    private String descricaoCompleta2 = "Grande área: Ciências Exatas e da Terra / Área: Ciência da Computação / Subárea: Metodologia e Técnicas da Computação/Especialidade: Reengenharia de Software.";
    private String descricaoAreaEspecialidade = "Área: Ciência da Computação /Especialidade: Engenharia de Software.";
    private String descricaoGrandeArea = "Grande área: Ciências Exatas e da Terra";
    private String descricaoArea = "Área: Ciência da Computação";
    private String descricaoSubarea = "Subárea: Metodologia e Técnicas da Computação ";
    private String descricaoEspecialidade = "Especialidade: Engenharia de Software.";
    private String invalidDescricao = "hgugufgn";

    private Pesquisador pesquisador;

    @Before
    public void init() {
        pesquisador = new Pesquisador();
    }

    @Test
    public void createAtuacaoPesquisadorInvalidDescricaoTest() {

        AtuacaoPesquisador atuacao = AtuacaoPesquisadorFactory.createAtuacaoPesquisador(pesquisador, invalidDescricao);
        pesquisador.addAtuacoesPesquisador(atuacao);

        AtuacaoPesquisador atuacao1 = pesquisador.getAreaAtuacoes().get(0);

        assertAtuacaoPesquisador(atuacao1, null, null, null, null);
    }

    @Test
    public void createAtuacaoPesquisadorDescricaoEspecialidadeTest() {

        AtuacaoPesquisador atuacao = AtuacaoPesquisadorFactory.createAtuacaoPesquisador(pesquisador, descricaoEspecialidade);
        pesquisador.addAtuacoesPesquisador(atuacao);

        AtuacaoPesquisador atuacao1 = pesquisador.getAreaAtuacoes().get(0);

        assertAtuacaoPesquisador(atuacao1, null, null, null, "Engenharia de Software.");
    }

    @Test
    public void createAtuacaoPesquisadorDescricaoSubareaTest() {

        AtuacaoPesquisador atuacao = AtuacaoPesquisadorFactory.createAtuacaoPesquisador(pesquisador, descricaoSubarea);
        pesquisador.addAtuacoesPesquisador(atuacao);

        AtuacaoPesquisador atuacao1 = pesquisador.getAreaAtuacoes().get(0);

        assertAtuacaoPesquisador(atuacao1, null, null, "Metodologia e Técnicas da Computação", null);
    }

    @Test
    public void createAtuacaoPesquisadorDescricaoAreaTest() {

        AtuacaoPesquisador atuacao = AtuacaoPesquisadorFactory.createAtuacaoPesquisador(pesquisador, descricaoArea);
        pesquisador.addAtuacoesPesquisador(atuacao);

        AtuacaoPesquisador atuacao1 = pesquisador.getAreaAtuacoes().get(0);

        assertAtuacaoPesquisador(atuacao1, null, "Ciência da Computação", null, null);
    }

    @Test
    public void createAtuacaoPesquisadorDescricaoGrandeAreaTest() {

        AtuacaoPesquisador atuacao = AtuacaoPesquisadorFactory.createAtuacaoPesquisador(pesquisador, descricaoGrandeArea);
        pesquisador.addAtuacoesPesquisador(atuacao);

        AtuacaoPesquisador atuacao1 = pesquisador.getAreaAtuacoes().get(0);

        assertAtuacaoPesquisador(atuacao1, "Ciências Exatas e da Terra", null, null, null);
    }

    @Test
    public void createAtuacaoPesquisadorDescricaoAreaEspecialidadeTest() {

        AtuacaoPesquisador atuacao = AtuacaoPesquisadorFactory.createAtuacaoPesquisador(pesquisador, descricaoAreaEspecialidade);
        pesquisador.addAtuacoesPesquisador(atuacao);

        AtuacaoPesquisador atuacao1 = pesquisador.getAreaAtuacoes().get(0);

        assertAtuacaoPesquisador(atuacao1, null, "Ciência da Computação", null, "Engenharia de Software.");
    }

    @Test
    public void createAtuacaoPesquisadorDescricaoCompletaTest() {

        AtuacaoPesquisador atuacaoPesquisador = AtuacaoPesquisadorFactory.createAtuacaoPesquisador(pesquisador, descricaoCompleta);
        AtuacaoPesquisador atuacaoPesquisador2 = AtuacaoPesquisadorFactory.createAtuacaoPesquisador(pesquisador, descricaoCompleta2);

        pesquisador.addAtuacoesPesquisador(atuacaoPesquisador, atuacaoPesquisador2);

        AtuacaoPesquisador atuacao1 = pesquisador.getAreaAtuacoes().get(0);
        AtuacaoPesquisador atuacao2 = pesquisador.getAreaAtuacoes().get(1);

        assertAtuacaoPesquisador(atuacao1, "Ciências Exatas e da Terra", "Ciência da Computação", "Metodologia e Técnicas da Computação", "Engenharia de Software.");
        assertAtuacaoPesquisador(atuacao2, "Ciências Exatas e da Terra", "Ciência da Computação", "Metodologia e Técnicas da Computação", "Reengenharia de Software.");

    }

    private void assertAtuacaoPesquisador(AtuacaoPesquisador atuacao, String grandeArea, String area, String subarea, String especialidade) {
        assertEquals(especialidade, atuacao.getEspecializacao().getDescricao());
        assertEquals(subarea, atuacao.getEspecializacao().getSubAreaAtuacao().getDescricao());
        assertEquals(area, atuacao.getEspecializacao().getSubAreaAtuacao().getAreaAtuacao().getDescricao());
        assertEquals(grandeArea, atuacao.getEspecializacao().getSubAreaAtuacao().getAreaAtuacao().getGrandeAreaAtuacao().getDescricao());
    }
}
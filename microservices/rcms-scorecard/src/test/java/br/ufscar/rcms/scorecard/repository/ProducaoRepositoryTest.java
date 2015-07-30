package br.ufscar.rcms.scorecard.repository;

import static org.junit.Assert.assertNotNull;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.scorecard.model.entity.CitacaoBibliografica;
import br.ufscar.rcms.scorecard.model.entity.Pesquisador;
import br.ufscar.rcms.scorecard.model.entity.Producao;
import br.ufscar.rcms.scorecard.util.Fixture;

public class ProducaoRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private ProducaoRepository producaoRepository;

    @Before
    public void setUp() {

        Pesquisador pesquisador = Fixture.newPesquisador();
        CitacaoBibliografica citacao = Fixture.newCitacao();
        Producao producao = Fixture.newProducao();

        citacao.setPesquisador(pesquisador);
        pesquisador.addCitacaoBibliografica(citacao);
        producao.setCitacaoBibliograficas(Arrays.asList(citacao));

        save(pesquisador, producao);
    }

    @Test
    public void testFindAll() {
        List<Producao> producoes = producaoRepository.findAll();
        producoes.forEach(this::assertProducoes);
    }

    private void assertProducoes(final Producao producao) {
        assertNotNull(producao.getAno());
        assertNotNull(producao.getId());
        assertNotNull(producao.getIdProducao());
        assertNotNull(producao.getLink());
        assertNotNull(producao.getPdf());
        assertNotNull(producao.getTitulo());

        List<CitacaoBibliografica> citacoes = producao.getCitacaoBibliograficas();
        citacoes.forEach(this::assertCitacoes);
    }

    private void assertCitacoes(final CitacaoBibliografica citacao) {
        assertNotNull(citacao.getId());
        assertNotNull(citacao.getIdCitacaoBibliografica());
        assertNotNull(citacao.getNomeCitacao());

        Pesquisador pesquisador = citacao.getPesquisador();
        assertPesquisador(pesquisador);
    }

    private void assertPesquisador(final Pesquisador pesquisador) {
        assertNotNull(pesquisador.getCodigoLattes());
        assertNotNull(pesquisador.getEmail());
        assertNotNull(pesquisador.getFlagAdministrador());
        assertNotNull(pesquisador.getId());
        assertNotNull(pesquisador.getIdUsuario());
        assertNotNull(pesquisador.getLogin());
        assertNotNull(pesquisador.getNome());
        assertNotNull(pesquisador.getResumoProfissional());
        assertNotNull(pesquisador.getSenha());
        assertNotNull(pesquisador.getSexo());
    }
}
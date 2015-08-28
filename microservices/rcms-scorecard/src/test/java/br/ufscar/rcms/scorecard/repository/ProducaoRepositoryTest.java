package br.ufscar.rcms.scorecard.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.commons.util.Fixture;
import br.ufscar.rcms.scorecard.model.entity.Producao;

public class ProducaoRepositoryTest extends AbstractRepositoryTest {

    @Autowired
    private ProducaoRepository producaoRepository;

    @Before
    public void setUp() {
        save(Fixture.newProducao());
    }

    @Test
    public void testFindAll() {
        List<Producao> producoes = producaoRepository.findAll();
        producoes.forEach(this::assertProducoes);
    }

    private void assertProducoes(final Producao producao) {

        assertFalse(producao.getAuthors().isEmpty());
        assertNotNull(producao.getId());
        assertNotNull(producao.getLastUpdate());
        assertNotNull(producao.getId());
        assertNotNull(producao.getTitle());
        assertNotNull(producao.getYear());
    }
}
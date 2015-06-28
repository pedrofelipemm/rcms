package br.ufscar.rcms.dao;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.config.UnitTestConfig;
import br.ufscar.rcms.modelo.entidades.Entidade;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UnitTestConfig.class })
@Transactional
public class AbstractDAOTestBase extends AbstractTransactionalJUnit4SpringContextTests {

    static {
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void configTest() {
        assertEquals("org.apache.logging.log4j.core.async.AsyncLoggerContextSelector", System.getProperty("Log4jContextSelector"));
    }

    protected Entidade salvar(Entidade entidade) {
        entidade = entityManager.merge(entidade);
        entityManager.flush();
        return entidade;
    }

    protected void salvar(final Entidade... entidades) {
        for (Entidade entidade : entidades) {
            entityManager.merge(entidade);
        }
        entityManager.flush();
    }
}
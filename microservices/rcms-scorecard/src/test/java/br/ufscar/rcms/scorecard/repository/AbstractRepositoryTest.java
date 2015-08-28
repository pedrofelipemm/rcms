package br.ufscar.rcms.scorecard.repository;

import static org.junit.Assert.assertEquals;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.model.entity.Entity;
import br.ufscar.rcms.scorecard.UnitTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = { UnitTestConfig.class })
@Transactional
public abstract class AbstractRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {

    static {
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
    }

    @PersistenceContext(name = "scorecardPersistenceUnit")
    private EntityManager entityManager;

    @Test
    public void configTest() {
        assertEquals("org.apache.logging.log4j.core.async.AsyncLoggerContextSelector", System.getProperty("Log4jContextSelector"));
    }

    protected void save(final Entity entity) {
        entityManager.persist(entity);
        entityManager.flush();
    }

    protected void save(final Entity... entities) {
        for (Entity entity : entities) {
            entityManager.persist(entity);
        }
        entityManager.flush();
    }

}
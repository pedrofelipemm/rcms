package br.ufscar.rcms.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

    @PersistenceContext
    EntityManager entityManager;

    public void salvar(Entidade... entidades) {
        for (Entidade entidade : entidades) {
            entityManager.merge(entidade);
        }
        entityManager.flush();
    }
}
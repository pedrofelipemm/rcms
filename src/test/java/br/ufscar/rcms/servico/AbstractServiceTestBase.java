package br.ufscar.rcms.servico;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.config.UnitTestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { UnitTestConfig.class })
@Transactional
public class AbstractServiceTestBase extends AbstractTransactionalJUnit4SpringContextTests {

    static {
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
    }

    @Test
    public void configTest() {
        assertEquals("org.apache.logging.log4j.core.async.AsyncLoggerContextSelector", System.getProperty("Log4jContextSelector"));
    }
}
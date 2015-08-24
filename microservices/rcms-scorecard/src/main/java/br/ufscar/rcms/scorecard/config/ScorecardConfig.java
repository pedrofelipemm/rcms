package br.ufscar.rcms.scorecard.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(
        basePackages = "br.ufscar.rcms.scorecard",
        entityManagerFactoryRef = "scorecardEntityManagerFactory",
        transactionManagerRef = "scorecardTransactionManager")
@EnableTransactionManagement
public class ScorecardConfig {

    @Autowired
    private Environment env;

    @Autowired
    private JpaVendorAdapter jpaVendorAdapter;


    @Bean(name = "scorecardEntityManagerFactory")
    // @Qualifier(value = "scorecardEntityManagerFactory")
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "br.ufscar.rcms.scorecard.model.entity" });
        em.setJpaVendorAdapter(jpaVendorAdapter);
        em.setJpaPropertyMap(additionalProperties());
        em.setPersistenceUnitName("scorecardPersistenceUnit");
        em.afterPropertiesSet();
        return em.getObject();
    }


    @Bean(name = "scorecardEntityManager")
    public EntityManager entityManager() {
        return entityManagerFactory().createEntityManager();
    }


    @Bean(name = "scorecardDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("database.driver.class.name"));
        dataSource.setUrl(env.getProperty("database.scorecard.url"));
        dataSource.setUsername(env.getProperty("database.username"));
        dataSource.setPassword(env.getProperty("database.password"));
        return dataSource;
    }

    @Primary
    @Bean(name = "scorecardTransactionManager")
    public PlatformTransactionManager transactionManager() {
        return new JpaTransactionManager(entityManagerFactory());
    }

    private Map<String, ?> additionalProperties() {
        HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
        return properties;
    }
}
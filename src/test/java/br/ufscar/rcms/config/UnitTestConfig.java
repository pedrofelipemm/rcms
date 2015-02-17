package br.ufscar.rcms.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@ComponentScan("br.ufscar.rcms")
@EnableTransactionManagement
// TODO: PEDRO - load from properties
@PropertySource("classpath:application-unit.properties")
public class UnitTestConfig {

    // @Value("${spring.packages.to.scan}")
    private String[] packesToScan = new String[] { "br.ufscar.rcms.modelo.entidades" };

    // @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHBM2DLL = "create";

    // @Value("${hibernate.dialect}")
    private String hibernateDialect = "org.hibernate.dialect.PostgreSQLDialect";

    // @Value("${database.driver.class.name}")
    private String databaseDriverClassName = "org.postgresql.Driver";

    // @Value("${database.test.url}")
    private String databaseUrl = "jdbc:postgresql://127.0.0.1:5432/rcmsTest";

    // @Value("${database.test.username}")
    private String databaseUsername = "postgres";

    // @Value("${database.test.password}")
    private String databasePassword = "postgres";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {

        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(packesToScan);

        JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean
    public DataSource dataSource() {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(databaseDriverClassName);
        dataSource.setUrl(databaseUrl);
        dataSource.setUsername(databaseUsername);
        dataSource.setPassword(databasePassword);

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {

        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(emf);

        return transactionManager;
    }

    @Bean
    public PersistenceExceptionTranslationPostProcessor exceptionTranslation() {

        return new PersistenceExceptionTranslationPostProcessor();
    }

    private Properties additionalProperties() {

        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", hibernateHBM2DLL);
        properties.setProperty("hibernate.dialect", hibernateDialect);
        return properties;
    }
}

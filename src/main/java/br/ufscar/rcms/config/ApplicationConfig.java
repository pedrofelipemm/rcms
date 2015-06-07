package br.ufscar.rcms.config;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.lib.PersonIdent;
import org.eclipse.jgit.revwalk.RevCommit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
@EnableTransactionManagement(proxyTargetClass = true)
@PropertySource("file:${user.home}/RCMS/config/application.properties")
public class ApplicationConfig {

    static {
        System.setProperty("Log4jContextSelector", "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector");
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationConfig.class);

    @Value("${spring.packages.to.scan}")
    private String[] packesToScan;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHBM2DLL;

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSql;

    @Value("${database.driver.class.name}")
    private String databaseDriverClassName;

    @Value("${database.url}")
    private String databaseUrl;

    @Value("${database.username}")
    private String databaseUsername;

    @Value("${database.password}")
    private String databasePassword;

    public ApplicationConfig() {
        LOGGER.info(commitInfo());
        LOGGER.info("User:" + System.getProperty("user.name"));
        LOGGER.error("Java Virtual Machine:" + System.getProperty("java.vm.vendor") + " - " + System.getProperty("java.vm.name"));
        LOGGER.error("Java Runtime:" + System.getProperty("java.runtime.version"));
        LOGGER.info("User Country" + System.getProperty("user.country"));
        LOGGER.info("User Language:" + System.getProperty("user.language"));
        LOGGER.info("Operational System:" + System.getProperty("os.name"));
        LOGGER.info("JNU Encoding:" + System.getProperty("sun.jnu.encoding"));
    }

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
        properties.setProperty("hibernate.show_sql", hibernateShowSql);
        return properties;
    }

    private String commitInfo() {
        String result = "";
        try {
            File gitWorkDir = new File("./");
            Git git = Git.open(gitWorkDir);
            Iterable<RevCommit> log = git.log().call();
            for (Iterator<RevCommit> iterator = log.iterator(); iterator.hasNext();) {
                RevCommit rev = iterator.next();
                PersonIdent author = rev.getAuthorIdent();
                String date = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(author.getWhen());
                String message = rev.getFullMessage();
                String name = author.getName();
                StringBuilder builder = new StringBuilder("\n### Current Version ###");
                builder.append("\nCommit Date: ").append(date);
                builder.append("\nCommit Author: ").append(name);
                builder.append("\nCommit Message: ").append(message);
                result = builder.toString();
                break;
            }
        } catch (Exception e) {
            LOGGER.error("Error in attempt to retrieve commit info: ", e);
        }
        return result;
    }
}
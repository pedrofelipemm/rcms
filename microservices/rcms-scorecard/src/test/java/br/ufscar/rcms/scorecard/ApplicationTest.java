package br.ufscar.rcms.scorecard;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("br.ufscar")
@EntityScan("br.ufscar")
@EnableJpaRepositories
@PropertySource("file:${user.home}/RCMS/config/application.properties")
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration.class,
        org.springframework.boot.actuate.autoconfigure.ManagementSecurityAutoConfiguration.class })
public class ApplicationTest {

    private static final String URL_MAPPING = "/rcms-scorecard/api/*";

    // public static void main(final String... args) {
    // SpringApplication.run(ApplicationTest.class, args);
    // }
    //
    // @Bean
    // public DispatcherServlet dispatcherServlet() {
    // return new DispatcherServlet();
    // }
    //
    // @Bean
    // public ServletRegistrationBean dispatcherServletRegistration() {
    //
    // ServletRegistrationBean registration = new
    // ServletRegistrationBean(dispatcherServlet(), URL_MAPPING);
    // registration.setName(DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_REGISTRATION_BEAN_NAME);
    // return registration;
    // }
}
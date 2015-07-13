package br.ufscar.rcms.view.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class CustomContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        servletContextEvent.getServletContext().setInitParameter("primefaces.THEME", "bootstrap");
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
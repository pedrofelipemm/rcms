package br.ufscar.rcms.view.listener;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ServletListener implements ServletContextListener {

	private static final Map<String, String> propriedades = new HashMap<String, String>();

	static {
		propriedades.put("org.apache.el.parser.COERCE_TO_ZERO", "false");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {

		for (Entry<String, String> entry : propriedades.entrySet()) {
			System.getProperties().put(entry.getKey(), entry.getValue());
		}
	}

	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
	}
}

package br.ufscar.rcms.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.servico.ConfiguracaoService;

@Component(value = "configurationFilter")
@WebFilter(urlPatterns = { "/paginas/*" })
public class ConfigurationFilter implements Filter {

	@Autowired(required = true)
	private ConfiguracaoService configuracaoService;

	private final String URL_CONFIG_INICIAL = "/paginas/configuracao-inicial.xhtml";
	private final String URL_HOME = "/paginas/index.xhtml";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest requestHttp = ((HttpServletRequest) req);
		HttpServletResponse responseHttp = ((HttpServletResponse) res);
		Long attrConfig = (Long) req.getServletContext().getAttribute("attrConfig");
		boolean configurado = true;
		if (attrConfig == null) {
			configurado = verificaConfiguracao();
		} else {
			Long diferenca = System.currentTimeMillis() - attrConfig;
			if ((diferenca / 3600000) > 1) {
				configurado = verificaConfiguracao();
			}
		}
		if(configurado){
			req.getServletContext().setAttribute("attrConfig", System.currentTimeMillis());
			if ((requestHttp.getContextPath() + URL_CONFIG_INICIAL).equals(requestHttp.getRequestURI())) {
				responseHttp.sendRedirect(requestHttp.getContextPath() + URL_HOME);
			} else {
				chain.doFilter(req, res);
			}
		} else{
			if ((requestHttp.getContextPath() + URL_CONFIG_INICIAL).equals(requestHttp.getRequestURI())) {
				chain.doFilter(req, res);
			} else{
				responseHttp.sendRedirect(requestHttp.getContextPath() + URL_CONFIG_INICIAL);
			}
		}
	}

	private boolean verificaConfiguracao() {
		return getConfiguracaoService().verificaConfiguracao(Configuracao.Tipo.getTiposConfigSistema());
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		/*
		 * ServletContext servletContext = filterConfig.getServletContext();
		 * WebApplicationContext webApplicationContext =
		 * WebApplicationContextUtils .getWebApplicationContext(servletContext);
		 * 
		 * AutowireCapableBeanFactory autowireCapableBeanFactory =
		 * webApplicationContext.getAutowireCapableBeanFactory();
		 * 
		 * autowireCapableBeanFactory.configureBean(this,
		 * "configurationFilter");
		 */

	}

	public ConfiguracaoService getConfiguracaoService() {
		return configuracaoService;
	}

	public void setConfiguracaoService(ConfiguracaoService configuracaoService) {
		this.configuracaoService = configuracaoService;
	}

}

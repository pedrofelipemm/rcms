package br.ufscar.rcms.view.filter;

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

	private final String URL_CONFIG_INICIAL = "/configuracao-inicial.xhtml";

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest requestHttp = ((HttpServletRequest) req);
		HttpServletResponse responseHttp = ((HttpServletResponse) res);
		Long attrConfig = (Long) req.getServletContext().getAttribute("attrConfig");
		boolean configurado = true;
		if (attrConfig == null) {
				configurado = verificaConfiguracao(req);
		} else {
			Long diferenca = System.currentTimeMillis() - attrConfig;
			if ((diferenca / 3600000) > 1) {
				configurado = verificaConfiguracao(req);
			}
		}
		if(configurado){
			chain.doFilter(req, res);
		} else{
			responseHttp.sendRedirect(requestHttp.getContextPath() + URL_CONFIG_INICIAL);
		}
	}

	private boolean verificaConfiguracao(ServletRequest req) {
		boolean configurado = getConfiguracaoService().verificaConfiguracao(Configuracao.Tipo.getTiposConfigSistema());
		if(configurado){
			req.getServletContext().setAttribute("attrConfig", System.currentTimeMillis());
		}
		return configurado;
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {}

	public ConfiguracaoService getConfiguracaoService() {
		return configuracaoService;
	}

	public void setConfiguracaoService(ConfiguracaoService configuracaoService) {
		this.configuracaoService = configuracaoService;
	}

}

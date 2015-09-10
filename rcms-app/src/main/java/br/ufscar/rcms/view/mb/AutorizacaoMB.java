package br.ufscar.rcms.view.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufscar.rcms.servico.AutorizacaoService;

@ViewScoped
@ManagedBean(name = "autorizacaoMB")
public class AutorizacaoMB extends AbstractMB{

	private static final long serialVersionUID = 1909277510237574562L;

	private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMB.class);
	
	@ManagedProperty("#{autorizacaoService}")
    private AutorizacaoService autorizacaoService;
	
	@Override
	protected void limparDados() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void carregarDados() {
		// TODO Auto-generated method stub
	}

}

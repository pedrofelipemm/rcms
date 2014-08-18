package br.ufscar.view.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;

@ViewScoped
@ManagedBean(name = "pesquisadorMB")
public class PesquisadorMB implements Serializable{

	private static final long serialVersionUID = 7023051572658948461L;

	@ManagedProperty("#{pesquisadorService}")
	private PesquisadorService pesquisadorService;

	private Pesquisador pesquisador;

	@PostConstruct
	public void inicializar() {

		limparDados();
	}

	private void limparDados() {

		pesquisador = new Pesquisador();
	}

	public void salvar() {

		pesquisadorService.salvar(pesquisador);
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, 
						"Pesquisador " + pesquisador.getNome() + " Salvo com sucesso", null));

		limparDados();
	}

	public void pesquisar() {

	}

	public PesquisadorService getPesquisadorService() {

		return pesquisadorService;
	}

	public void setPesquisadorService(PesquisadorService pesquisadorService) {

		this.pesquisadorService = pesquisadorService;
	}

	public Pesquisador getPesquisador() {

		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {

		this.pesquisador = pesquisador;
	}

}

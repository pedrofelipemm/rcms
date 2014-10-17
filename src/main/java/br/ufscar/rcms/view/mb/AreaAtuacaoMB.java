package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.servico.AreaAtuacaoService;

@ViewScoped
@ManagedBean(name = "areaAtuacaoMB")
public class AreaAtuacaoMB extends AbstractMB {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6030291216383161652L;
	
	@ManagedProperty("#{areaAtuacaoService}")
	private AreaAtuacaoService areaAtuacaoService;
	private AreaAtuacao area;
	
	@PostConstruct
    public void inicializar() {
		area = new AreaAtuacao();
	}
	
	public void salvar() {
		if (area != null) {
			AreaAtuacao areaExistente = areaAtuacaoService
					.BuscarPorDescricao(area.getDescricao());
			
			if (areaExistente == null){
				areaAtuacaoService.Salvar(area);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informa��o:", "�rea de atua��o cadastrada com sucesso!"));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aten��o:", "J� existe uma �rea com esta descri��o."));
			}

		}
	}


	public AreaAtuacaoService getAreaAtuacaoService() {
		return areaAtuacaoService;
	}

	public void setAreaAtuacaoService(AreaAtuacaoService areaAtuacaoService) {
		this.areaAtuacaoService = areaAtuacaoService;
	}

	
	public AreaAtuacao getArea() {
		return area;
	}

	public void setArea(AreaAtuacao area) {
		this.area = area;
	}
}

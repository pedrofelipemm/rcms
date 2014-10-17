package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.servico.IdiomaService;

@ViewScoped
@ManagedBean(name = "idiomaMB")
public class IdiomaMB extends AbstractMB {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7215958678138311243L;
	
	@ManagedProperty("#{idiomaService}")
	private IdiomaService idiomaService;
	
	private Idioma idioma;
	
	@PostConstruct
    public void inicializar() {
		setIdioma(new Idioma());
	}
	
	public void salvar() {
		if (getIdioma() != null) {
			Idioma idiomaExistente = getIdiomaService()
					.BuscarPorDescricao(getIdioma().getDescricao());
			
			if (idiomaExistente == null){
				getIdiomaService().Salvar(getIdioma());
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Informação:", "Idioma cadastrado com sucesso!"));
			}else{
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Atenção:", "Já existe um idioma com esta descrição."));
			}

		}
	}

	public IdiomaService getIdiomaService() {
		return idiomaService;
	}

	public void setIdiomaService(IdiomaService idiomaService) {
		this.idiomaService = idiomaService;
	}

	public Idioma getIdioma() {
		return idioma;
	}

	public void setIdioma(Idioma idioma) {
		this.idioma = idioma;
	}

}

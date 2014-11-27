package br.ufscar.rcms.view.mb;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;
import br.ufscar.rcms.servico.FormacaoAcademicaService;

@ViewScoped
@ManagedBean(name="formacaoAcademicaMB")
public class FormacaoAcademicaMB extends AbstractMB{

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{formacaoAcademicaService}")
    private FormacaoAcademicaService formacaoAcademicaService;
	
	private FormacaoAcademica formacaoAcademica;
	
	/**
	 * Salva os dados de Formacao Academica
	 */
	public void salvar(){
		//adicionarMensagemInfoByKey("formacaoAcademica.salvo.sucesso", formacaoAcademica.getPesquisador().getNome());
		formacaoAcademicaService.salvar(formacaoAcademica);
	}
	
	public FormacaoAcademicaService getFormacaoAcademicaService() {
		return formacaoAcademicaService;
	}

	public void setFormacaoAcademicaService(FormacaoAcademicaService formacaoAcademicaService) {
		this.formacaoAcademicaService = formacaoAcademicaService;
	}

	public FormacaoAcademica getFormacaoAcademica() {
		return formacaoAcademica;
	}

	public void setFormacaoAcademica(FormacaoAcademica formacaoAcademica) {
		this.formacaoAcademica = formacaoAcademica;
	}


}

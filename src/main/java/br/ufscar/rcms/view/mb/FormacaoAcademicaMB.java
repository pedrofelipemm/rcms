package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufscar.rcms.modelo.entidades.FormacaoAcademica;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.FormacaoAcademicaService;
import br.ufscar.rcms.servico.PesquisadorService;

@ViewScoped
@ManagedBean(name = "formacaoAcademicaMB")
public class FormacaoAcademicaMB extends AbstractMB {

    private static final long serialVersionUID = 1L;

    @ManagedProperty("#{formacaoAcademicaService}")
    private FormacaoAcademicaService formacaoAcademicaService;

    @ManagedProperty("#{pesquisadorService}")
    private PesquisadorService pesquisadorService;

    private DataModel<Pesquisador> pesquisadores;

    private DataModel<FormacaoAcademica> formacoes;

    private FormacaoAcademica formacaoAcademica;

    @PostConstruct
    public void carregarDados() {
        pesquisadores = new ListDataModel<Pesquisador>(pesquisadorService.buscarTodos());
        formacoes = new ListDataModel<FormacaoAcademica>(formacaoAcademicaService.buscarTodos());
    }

    /**
     * Salva os dados de Formacao Academica
     */
    public void salvar() {
        // adicionarMensagemInfoByKey("formacaoAcademica.salvo.sucesso", formacaoAcademica.getPesquisador().getNome());
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

    public PesquisadorService getPesquisadorService() {
        return pesquisadorService;
    }

    public void setPesquisadorService(PesquisadorService pesquisadorService) {
        this.pesquisadorService = pesquisadorService;
    }

    public DataModel<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(DataModel<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

    public DataModel<FormacaoAcademica> getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(DataModel<FormacaoAcademica> formacoes) {
        this.formacoes = formacoes;
    }

}

package br.ufscar.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;

@ViewScoped
@ManagedBean(name = "pesquisadorMB")
public class PesquisadorMB extends AbstractMB {

    private static final long serialVersionUID = 7023051572658948461L;

    @ManagedProperty("#{pesquisadorService}")
    private PesquisadorService pesquisadorService;

    private Pesquisador pesquisador;

    private UploadedFile fotoPesquisador;

    @PostConstruct
    public void inicializar() {

        limparDados();
    }

    private void limparDados() {

        pesquisador = new Pesquisador();
    }

    public void handleFileUpload(FileUploadEvent event) {

        fotoPesquisador = event.getFile();

    }

    public void salvar() {

        pesquisadorService.salvar(pesquisador);
        // TODO i18n
        // TODO encapsular
        FacesContext.getCurrentInstance().addMessage(
                null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Pesquisador " + pesquisador.getNome()
                        + " Salvo com sucesso", null));

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

    public UploadedFile getFotoPesquisador() {
        return fotoPesquisador;
    }

    public void setFotoPesquisador(UploadedFile fotoPesquisador) {
        this.fotoPesquisador = fotoPesquisador;
    }
}

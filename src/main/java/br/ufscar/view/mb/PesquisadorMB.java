package br.ufscar.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;

@ViewScoped
@ManagedBean(name = "pesquisadorMB")
public class PesquisadorMB extends AbstractMB {

    private static final long serialVersionUID = 7023051572658948461L;

    @ManagedProperty("#{pesquisadorService}")
    private PesquisadorService pesquisadorService;

    private Pesquisador pesquisador;

    private Part fotoPesquisador;

    @PostConstruct
    public void inicializar() {

        limparDados();
    }

    private void limparDados() {

        pesquisador = new Pesquisador();
    }

    private String getFileName(Part part) {

        final String partHeader = part.getHeader("content-disposition");
        for (String content : part.getHeader("content-disposition").split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
            }
        }
        return null;
    }

    // TODO CRIAR HIERARQUIA DE EXCEPTION
    public void salvar() {

        // File file = new File(getFileName(fotoPesquisador));
        // pesquisador.setFoto(Files.readAllBytes(file.toPath()));

        pesquisadorService.salvar(pesquisador);

        adicionarMensagemInfoByKey("pesquisador.salvo.sucesso", pesquisador.getNome());

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

    public Part getFotoPesquisador() {
        return fotoPesquisador;
    }

    public void setFotoPesquisador(Part fotoPesquisador) {
        this.fotoPesquisador = fotoPesquisador;
    }

}

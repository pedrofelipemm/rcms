package br.ufscar.view.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.AreaAtuacaoService;
import br.ufscar.rcms.servico.PesquisadorService;

@ViewScoped
@ManagedBean(name = "pesquisadorMB")
public class PesquisadorMB extends AbstractMB {

    private static final long serialVersionUID = 7023051572658948461L;

    @ManagedProperty("#{pesquisadorService}")
    private PesquisadorService pesquisadorService;

    @ManagedProperty("#{areaAtuacaoService}")
    private AreaAtuacaoService areaAtuacaoService;

    private Pesquisador pesquisador;

    private AreaAtuacao areaSelecionada;
    private List<AreaAtuacao> areas;

    private Part fotoPesquisador;

    @PostConstruct
    public void inicializar() {

        areas = new ArrayList<AreaAtuacao>();

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

    public void addAreaAtuacao() {
        if (areaSelecionada != null) {
            pesquisador.getAreaAtuacoes().add(areaSelecionada);
            areas.remove(areas.indexOf(areaSelecionada));
        }
    }

    public void removerAreaAtuacao() {
        if (areaSelecionada != null) {
            pesquisador.getAreaAtuacoes().remove(pesquisador.getAreaAtuacoes().indexOf(areaSelecionada));
            areas.add(areaSelecionada);
        }
    }

    public AreaAtuacaoService getAreaAtuacaoService() {
        return areaAtuacaoService;
    }

    public void setAreaAtuacaoService(AreaAtuacaoService areaAtuacaoService) {
        this.areaAtuacaoService = areaAtuacaoService;
    }

    public AreaAtuacao getAreaSelecionada() {
        return areaSelecionada;
    }

    public void setAreaSelecionada(AreaAtuacao areaSelecionada) {
        this.areaSelecionada = areaSelecionada;
    }

    public List<AreaAtuacao> getAreas() {
        return areas;
    }

    public void setAreas(List<AreaAtuacao> areas) {
        this.areas = areas;
    }
}

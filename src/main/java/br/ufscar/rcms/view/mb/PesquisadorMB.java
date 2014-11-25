package br.ufscar.rcms.view.mb;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.AreaAtuacaoService;
import br.ufscar.rcms.servico.IdiomaService;
import br.ufscar.rcms.servico.PesquisadorService;

@ViewScoped
@ManagedBean(name = "pesquisadorMB")
public class PesquisadorMB extends AbstractMB {

    private static final long serialVersionUID = 7023051572658948461L;

    @ManagedProperty("#{pesquisadorService}")
    private PesquisadorService pesquisadorService;

    @ManagedProperty("#{areaAtuacaoService}")
    private AreaAtuacaoService areaAtuacaoService;

    @ManagedProperty("#{idiomaService}")
    private IdiomaService idiomaService;

    private Pesquisador pesquisador;

    private AreaAtuacao areaSelecionada;

    private List<AreaAtuacao> areas;

    private transient Part fotoPesquisador;

    private List<Idioma> idiomas;
    private Idioma idiomaSelecionado;

    @PostConstruct
    public void inicializar() {

        limparDados();

        areas = areaAtuacaoService.buscarTodas();
        idiomas = idiomaService.buscarTodas();
    }

    private void limparDados() {

        pesquisador = new Pesquisador();
        // TODO TEMP
        pesquisador.setFlagAdministrador(true);
    }

    public void salvar() {

        // TODO TRATAR
        if (fotoPesquisador != null) {
            converterFotoPesquisador(pesquisador);
        }

        pesquisadorService.salvar(pesquisador);

        adicionarMensagemInfoByKey("pesquisador.salvo.sucesso", pesquisador.getNome());

        limparDados();
    }

    private void converterFotoPesquisador(Pesquisador pesquisador) {

        try {
            pesquisador.setFoto(IOUtils.toByteArray(fotoPesquisador.getInputStream()));
        } catch (IOException e) {
            // TODO TRATAR EXCEPTION
            e.printStackTrace();
        }
    }

    public void pesquisar() {

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

    public void addIdioma() {
        if (idiomaSelecionado != null) {
            pesquisador.getIdiomas().add(idiomaSelecionado);
            idiomas.remove(idiomas.indexOf(idiomaSelecionado));
        }
    }

    public void removerIdioma() {
        if (idiomaSelecionado != null) {
            pesquisador.getIdiomas().remove(pesquisador.getIdiomas().indexOf(idiomaSelecionado));
            idiomas.add(idiomaSelecionado);
        }
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

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public Idioma getIdiomaSelecionado() {
        return idiomaSelecionado;
    }

    public void setIdiomaSelecionado(Idioma idiomaSelecionado) {
        this.idiomaSelecionado = idiomaSelecionado;
    }

    public IdiomaService getIdiomaService() {
        return idiomaService;
    }

    public void setIdiomaService(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

}

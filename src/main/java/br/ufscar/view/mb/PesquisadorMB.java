package br.ufscar.view.mb;

import java.io.IOException;
import java.io.InputStream;
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

        limparDados();
    }

    private void limparDados() {

        pesquisador = new Pesquisador();
        areas = new ArrayList<AreaAtuacao>();
    }

    // TODO CRIAR HIERARQUIA DE EXCEPTION
    public void salvar() {

        converterFotoPesquisador(pesquisador);
        pesquisadorService.salvar(pesquisador);

        adicionarMensagemInfoByKey("pesquisador.salvo.sucesso", pesquisador.getNome());

        limparDados();
    }

    private void converterFotoPesquisador(Pesquisador pesquisador) {

        byte[] buffer = new byte[(int) fotoPesquisador.getSize()];
        InputStream inputStream;

        try {

            inputStream = fotoPesquisador.getInputStream();
            inputStream.read(buffer);
            pesquisador.setFoto(buffer);
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
}

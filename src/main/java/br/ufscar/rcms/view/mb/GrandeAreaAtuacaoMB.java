package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufscar.rcms.factory.AreaAtuacaoFactory;
import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.servico.AreaAtuacaoService;
import br.ufscar.rcms.servico.GrandeAreaAtuacaoService;

@ViewScoped
@ManagedBean(name = "grandeAreaAtuacaoMB")
public class GrandeAreaAtuacaoMB extends AbstractMB {

    private static final long serialVersionUID = 3154941155054286496L;

    @ManagedProperty("#{grandeAreaAtuacaoService}")
    private GrandeAreaAtuacaoService grandeAreaAtuacaoService;

    @ManagedProperty("#{areaAtuacaoService}")
    private AreaAtuacaoService areaAtuacaoService;
    private GrandeAreaAtuacao gdeArea;
    private transient DataModel<GrandeAreaAtuacao> todasAsGrandeAreas;
    private transient DataModel<AreaAtuacao> areas;
    private AreaAtuacao areaSelecionada;

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
    }

    private void carregarDados() {
        todasAsGrandeAreas = new ListDataModel<GrandeAreaAtuacao>(grandeAreaAtuacaoService.buscarTodas());
        GrandeAreaAtuacao gdeareaEditar = (GrandeAreaAtuacao) getFlashObject(FLASH_KEY_GRANDE_AREA_ATUACAO);
        if (gdeareaEditar != null) {
            gdeArea = gdeareaEditar;
            areas = new ListDataModel<AreaAtuacao>(gdeArea.getAreasDeAtuacao());
        }
    }

    private void limparDados() {
        gdeArea = AreaAtuacaoFactory.createGrandeAreaEmpty();
        areaSelecionada = AreaAtuacaoFactory.createAreaAtuacaoEmpty();
    }

    public void salvar() {

        if (gdeArea.getIdGrandeAreaAtuacao() != null) {
            getGrandeAreaAtuacaoService().alterar(gdeArea);
            adicionarMensagemInfoByKey("area.atuacao.alterada.sucesso", gdeArea.getDescricao());
        } else {
            getGrandeAreaAtuacaoService().salvar(gdeArea);
            adicionarMensagemInfoByKey("area.atuacao.cadastrada.sucesso", gdeArea.getDescricao());
        }

        areas = new ListDataModel<AreaAtuacao>(gdeArea.getAreasDeAtuacao());

    }

    public String editar(GrandeAreaAtuacao area) {
        setFlashObject(FLASH_KEY_GRANDE_AREA_ATUACAO, area);

        return CADASTRO_GRANDE_AREA;
    }

    public String excluir(GrandeAreaAtuacao area) {

        grandeAreaAtuacaoService.remover(area);

        return CONSULTA_GRANDE_AREA;
    }

    public void adicionarAreaAtuacao() {
        gdeArea.getAreasDeAtuacao().add(areaSelecionada);
        areaSelecionada = AreaAtuacaoFactory.createAreaAtuacaoEmpty();
        areas = new ListDataModel<AreaAtuacao>(gdeArea.getAreasDeAtuacao());
    }

    public void removerAreaAtuacao(AreaAtuacao area) {
        gdeArea.getAreasDeAtuacao().remove(gdeArea.getAreasDeAtuacao().indexOf(area));
        areas = new ListDataModel<AreaAtuacao>(gdeArea.getAreasDeAtuacao());
    }

    public GrandeAreaAtuacao getGdeArea() {
        return gdeArea;
    }

    public void setGdeArea(GrandeAreaAtuacao gdeArea) {
        this.gdeArea = gdeArea;
    }

    public DataModel<AreaAtuacao> getAreas() {
        return areas;
    }

    public void setAreas(DataModel<AreaAtuacao> areas) {
        this.areas = areas;
    }

    public GrandeAreaAtuacaoService getGrandeAreaAtuacaoService() {
        return grandeAreaAtuacaoService;
    }

    public void setGrandeAreaAtuacaoService(GrandeAreaAtuacaoService grandeAreaAtuacaoService) {
        this.grandeAreaAtuacaoService = grandeAreaAtuacaoService;
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

    public DataModel<GrandeAreaAtuacao> getTodasAsGrandeAreas() {
        return todasAsGrandeAreas;
    }

    public void setTodasAsGrandeAreas(DataModel<GrandeAreaAtuacao> todasAsGrandeAreas) {
        this.todasAsGrandeAreas = todasAsGrandeAreas;
    }

}

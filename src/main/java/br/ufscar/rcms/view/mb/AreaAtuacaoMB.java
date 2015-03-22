package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufscar.rcms.factory.AreaAtuacaoFactory;
import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import br.ufscar.rcms.servico.AreaAtuacaoService;

@ViewScoped
@ManagedBean(name = "areaAtuacaoMB")
public class AreaAtuacaoMB extends AbstractMB {

    private static final long serialVersionUID = -1374520348706209763L;

    @ManagedProperty("#{areaAtuacaoService}")
    private AreaAtuacaoService areaAtuacaoService;

    private AreaAtuacao area;
    private transient DataModel<SubAreaAtuacao> subAreas;
    private transient DataModel<AreaAtuacao> todasAsAreas;
    private SubAreaAtuacao subAreaSelecionada;

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
    }

    private void carregarDados() {
        todasAsAreas = new ListDataModel<AreaAtuacao>(areaAtuacaoService.buscarTodas());
        subAreaSelecionada = new SubAreaAtuacao();

        AreaAtuacao areaEditar = (AreaAtuacao) getFlashObject(FLASH_KEY_AREA_ATUACAO);
        if (areaEditar != null) {
            area = areaEditar;
            subAreas = new ListDataModel<SubAreaAtuacao>(area.getSubAreasAtuacao());
        }

    }

    private void limparDados() {
        area = AreaAtuacaoFactory.createAreaAtuacaoEmpty();
    }

    public void salvar() {

        for (SubAreaAtuacao subAreaAtuacao : area.getSubAreasAtuacao()) {
            subAreaAtuacao.setAreaAtuacao(area);
        }

        if (area.getIdAreaAtuacao() != null) {
            areaAtuacaoService.alterar(area);
            adicionarMensagemInfoByKey("area.atuacao.alterada.sucesso", area.getDescricao());
        } else {
            areaAtuacaoService.salvar(area);
            adicionarMensagemInfoByKey("area.atuacao.cadastrada.sucesso", area.getDescricao());
        }
        subAreas = new ListDataModel<SubAreaAtuacao>(area.getSubAreasAtuacao());

    }

    public String editar(AreaAtuacao area) {
        setFlashObject(FLASH_KEY_AREA_ATUACAO, area);

        return CADASTRO_AREA_ATUACAO;
    }

    public String excluir(AreaAtuacao area) {

        areaAtuacaoService.remover(area);

        return CONSULTA_AREA_ATUACAO;
    }

    public void adicionarSubAreaAtuacao() {
        area.getSubAreasAtuacao().add(subAreaSelecionada);
        subAreaSelecionada = new SubAreaAtuacao();
        subAreas = new ListDataModel<SubAreaAtuacao>(area.getSubAreasAtuacao());
    }

    public void removerSubAreaAtuacao(SubAreaAtuacao area) {
        this.area.getSubAreasAtuacao().remove(this.area.getSubAreasAtuacao().indexOf(area));
        subAreas = new ListDataModel<SubAreaAtuacao>(this.area.getSubAreasAtuacao());
    }

    public AreaAtuacaoService getAreaAtuacaoService() {
        return areaAtuacaoService;
    }

    public void setAreaAtuacaoService(AreaAtuacaoService areaAtuacaoService) {
        this.areaAtuacaoService = areaAtuacaoService;
    }

    public SubAreaAtuacao getSubAreaSelecionada() {
        return subAreaSelecionada;
    }

    public void setSubAreaSelecionada(SubAreaAtuacao subAreaSelecionada) {
        this.subAreaSelecionada = subAreaSelecionada;
    }

    public DataModel<SubAreaAtuacao> getSubAreas() {
        return subAreas;
    }

    public void setSubAreas(DataModel<SubAreaAtuacao> subAreas) {
        this.subAreas = subAreas;
    }

    public AreaAtuacao getArea() {
        return area;
    }

    public void setArea(AreaAtuacao area) {
        this.area = area;
    }

    public DataModel<AreaAtuacao> getTodasAsAreas() {
        return todasAsAreas;
    }

    public void setTodasAsAreas(DataModel<AreaAtuacao> todasAsAreas) {
        this.todasAsAreas = todasAsAreas;
    }

}

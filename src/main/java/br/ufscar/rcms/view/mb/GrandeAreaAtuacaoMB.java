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
	private transient DataModel<AreaAtuacao> areas;
	private AreaAtuacao areaSelecionada;

	@PostConstruct
	public void inicializar() {
		limparDados();
	}
	
	private void limparDados() {
		gdeArea = AreaAtuacaoFactory.CreateGrandeAreaEmpty();
		areaSelecionada = AreaAtuacaoFactory.CreateAreaAtuacaoEmpty();
	}

	public void salvar() {

		if (gdeArea.getId() != null) {
			getGrandeAreaAtuacaoService().alterar(gdeArea);
			adicionarMensagemInfoByKey("area.atuacao.alterada.sucesso",
					gdeArea.getDescricao());
		} else {
			getGrandeAreaAtuacaoService().salvar(gdeArea);
			adicionarMensagemInfoByKey(
					"area.atuacao.cadastrada.sucesso",
					gdeArea.getDescricao());
		}

		areas = new ListDataModel<AreaAtuacao>(gdeArea.getAreasDeAtuacao());

	}

	public void adicionarAreaAtuacao() {
		gdeArea.getAreasDeAtuacao().add(areaSelecionada);
		areaSelecionada = AreaAtuacaoFactory.CreateAreaAtuacaoEmpty();
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

	public void setGrandeAreaAtuacaoService(
			GrandeAreaAtuacaoService grandeAreaAtuacaoService) {
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

}

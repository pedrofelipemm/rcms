package br.ufscar.rcms.view.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.primefaces.event.SelectEvent;

import br.ufscar.rcms.factory.AreaAtuacaoFactory;
import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.EspecializacaoAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import br.ufscar.rcms.servico.AreaAtuacaoService;
import br.ufscar.rcms.servico.EspecializacaoAreaAtuacaoService;
import br.ufscar.rcms.servico.GrandeAreaAtuacaoService;
import br.ufscar.rcms.servico.SubAreaAtuacaoService;

@ViewScoped
@ManagedBean(name = "areaAtuacaoMB")
public class AreaAtuacaoMB extends AbstractMB {

	private static final long serialVersionUID = -1374520348706209763L;

	@ManagedProperty("#{areaAtuacaoService}")
	private AreaAtuacaoService areaAtuacaoService;

	@ManagedProperty("#{especializacaoAreaAtuacaoService}")
	private EspecializacaoAreaAtuacaoService especializacaoService;

	@ManagedProperty("#{grandeAreaAtuacaoService}")
	private GrandeAreaAtuacaoService grandeAreaService;
	
	@ManagedProperty("#{subAreaAtuacaoService}")
	private SubAreaAtuacaoService subAreaAtuacaoService;

	private AreaAtuacao area;
	private transient DataModel<SubAreaAtuacao> subAreas;
	private transient DataModel<AreaAtuacao> todasAsAreas;
	private List<EspecializacaoAreaAtuacao> todasAsEspecializacoes;
	private SubAreaAtuacao subAreaSelecionada;
	private GrandeAreaAtuacao gdeAreaSelecionada;
	private AreaAtuacao areaSelecionada;
	private EspecializacaoAreaAtuacao especSelecionada;
	private String gdeAreaInserindoOuEditando;
	private String areaInserindoOuEditando;
	private String subAreaInserindoOuEditando;
	private String especInserindoOuEditando;

	@PostConstruct
	public void inicializar() {
		limparDados();
		carregarDados();
	}

	@Override
	protected void carregarDados() {
		todasAsEspecializacoes = especializacaoService.buscarTodas();

		EspecializacaoAreaAtuacao areaEditar = (EspecializacaoAreaAtuacao) getFlashObject(FLASH_KEY_AREA_ATUACAO);
		if (areaEditar != null) {
			subAreaSelecionada = areaEditar.getSubAreaAtuacao();
			areaSelecionada = subAreaSelecionada.getAreaAtuacao();
			gdeAreaSelecionada = areaSelecionada.getGrandeAreaAtuacao();
			especInserindoOuEditando = areaEditar.getDescricao();
		}

	}

	@Override
	protected void limparDados() {
		area = AreaAtuacaoFactory.createAreaAtuacaoEmpty();
		areaSelecionada = null;
		gdeAreaSelecionada = null;
		subAreaSelecionada = null;
		especSelecionada = null;
	}

	public List<GrandeAreaAtuacao> completeGdeArea(String query) {
		setAreaSelecionada(null);
		subAreaSelecionada = null;
		setEspecSelecionada(null);
		setGdeAreaSelecionada(null);

		List<GrandeAreaAtuacao> gdeAreas = grandeAreaService.buscarTodas();
		List<GrandeAreaAtuacao> gdeAreasFiltradas = new ArrayList<GrandeAreaAtuacao>();

		for (GrandeAreaAtuacao gdeArea : gdeAreas) {
			if (gdeArea.getDescricao().toLowerCase()
					.startsWith(query.toLowerCase())) {
				gdeAreasFiltradas.add(gdeArea);
			}
		}

		return gdeAreasFiltradas;
	}

	public void onItemSelectGdeArea(SelectEvent event) {
		gdeAreaSelecionada = null;

		if (event.getObject() != null) {
			gdeAreaSelecionada = (GrandeAreaAtuacao) event.getObject();
		}

	}

	public void excluirGdeArea() {
		if (gdeAreaSelecionada == null)
			return;

		grandeAreaService.remover(gdeAreaSelecionada);
		gdeAreaSelecionada = null;
	}

	public void inserirGrandeArea() {
		if (gdeAreaInserindoOuEditando.isEmpty())
			return;

		GrandeAreaAtuacao gdeArea = new GrandeAreaAtuacao();
		gdeArea.setDescricao(gdeAreaInserindoOuEditando);
		grandeAreaService.salvar(gdeArea);
		gdeAreaSelecionada = gdeArea;
		gdeAreaInserindoOuEditando = "";

	}

	public List<AreaAtuacao> completeArea(String query) {
		setAreaSelecionada(null);
		subAreaSelecionada = null;
		setEspecSelecionada(null);

		List<AreaAtuacao> areasFiltradas = new ArrayList<AreaAtuacao>();

		for (AreaAtuacao area : gdeAreaSelecionada.getAreasDeAtuacao()) {
			if (area.getDescricao().toLowerCase()
					.startsWith(query.toLowerCase())) {
				areasFiltradas.add(area);
			}
		}

		return areasFiltradas;
	}
	
	public void onItemSelectArea(SelectEvent event) {
		areaSelecionada = null;

		if (event.getObject() != null) {
			areaSelecionada = (AreaAtuacao) event.getObject();
		}

	}
	
	public void excluirArea() {
		if (areaSelecionada == null)
			return;
		gdeAreaSelecionada.getAreasDeAtuacao().remove(areaSelecionada);
		areaAtuacaoService.remover(areaSelecionada);
		grandeAreaService.alterar(gdeAreaSelecionada);
		areaSelecionada = null;
	}
	
	public void inserirArea() {
		if (areaInserindoOuEditando.isEmpty() || (gdeAreaSelecionada == null))
			return;

		AreaAtuacao area = new AreaAtuacao();
		area.setDescricao(areaInserindoOuEditando);
		area.setGrandeAreaAtuacao(gdeAreaSelecionada);
		areaAtuacaoService.salvar(area);
		gdeAreaSelecionada.addAreaAtuacao(area);
		areaSelecionada = area;
		areaInserindoOuEditando = "";

	}

	public List<SubAreaAtuacao> completeSub(String query) {
		subAreaSelecionada = null;
		setEspecSelecionada(null);

		List<SubAreaAtuacao> subFiltradas = new ArrayList<SubAreaAtuacao>();

		for (SubAreaAtuacao sub : areaSelecionada.getSubAreasAtuacao()) {
			if (sub.getDescricao().toLowerCase()
					.startsWith(query.toLowerCase())) {
				subFiltradas.add(sub);
			}
		}

		return subFiltradas;
	}
	
	public void onItemSelectSubArea(SelectEvent event) {
		subAreaSelecionada = null;

		if (event.getObject() != null) {
			subAreaSelecionada = (SubAreaAtuacao) event.getObject();
		}

	}
	
	public void excluirSubArea() {
		if (subAreaSelecionada == null)
			return;
		areaSelecionada.getSubAreasAtuacao().remove(subAreaSelecionada);
		subAreaAtuacaoService.remover(subAreaSelecionada);
		areaAtuacaoService.alterar(areaSelecionada);
		subAreaSelecionada = null;
	}
	
	public void inserirSubArea() {
		if (subAreaInserindoOuEditando.isEmpty() || (areaSelecionada == null))
			return;

		SubAreaAtuacao area = new SubAreaAtuacao();
		area.setDescricao(subAreaInserindoOuEditando);
		area.setAreaAtuacao(areaSelecionada);
		subAreaAtuacaoService.salvar(area);
		areaSelecionada.addSubAreaAtuacao(area);
		subAreaSelecionada = area;
		subAreaInserindoOuEditando = "";

	}	
	
	public void excluirEspec() {
		if (especSelecionada == null)
			return;

		especializacaoService.remover(especSelecionada);
		especSelecionada = null;
	}
	
	public void excluirEspec(EspecializacaoAreaAtuacao especializacao) {
		SubAreaAtuacao subArea = especializacao.getSubAreaAtuacao();
		subArea.getEspecializacoes().remove(especializacao);
		especializacao.setSubAreaAtuacao(null);
		subAreaAtuacaoService.alterar(subArea);
		especializacaoService.remover(especializacao);		
		todasAsEspecializacoes = especializacaoService.buscarTodas();
	}
	
	public void inserirEspec() {
		if (especInserindoOuEditando.isEmpty())
			return;
	
		EspecializacaoAreaAtuacao espec = new EspecializacaoAreaAtuacao();
		espec.setDescricao(especInserindoOuEditando);
		espec.setSubAreaAtuacao(subAreaSelecionada);
		especializacaoService.salvar(espec);
		especSelecionada = espec;

	}

	public String salvar() {

		inserirEspec();
		
		return CONSULTA_AREA_ATUACAO;

	}

	public String editar(AreaAtuacao area) {
		setFlashObject(FLASH_KEY_AREA_ATUACAO, area);

		return CADASTRO_AREA_ATUACAO;
	}

	public String editar(EspecializacaoAreaAtuacao especializacao) {
		setFlashObject(FLASH_KEY_AREA_ATUACAO, especializacao);

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
		this.area.getSubAreasAtuacao().remove(
				this.area.getSubAreasAtuacao().indexOf(area));
		subAreas = new ListDataModel<SubAreaAtuacao>(
				this.area.getSubAreasAtuacao());
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

	public EspecializacaoAreaAtuacaoService getEspecializacaoService() {
		return especializacaoService;
	}

	public void setEspecializacaoService(
			EspecializacaoAreaAtuacaoService especializacaoService) {
		this.especializacaoService = especializacaoService;
	}

	public List<EspecializacaoAreaAtuacao> getTodasAsEspecializacoes() {
		return todasAsEspecializacoes;
	}

	public void setTodasAsEspecializacoes(
			List<EspecializacaoAreaAtuacao> todasAsEspecializacoes) {
		this.todasAsEspecializacoes = todasAsEspecializacoes;
	}

	public GrandeAreaAtuacao getGdeAreaSelecionada() {
		return gdeAreaSelecionada;
	}

	public void setGdeAreaSelecionada(GrandeAreaAtuacao gdeAreaSelecionada) {
		this.gdeAreaSelecionada = gdeAreaSelecionada;
	}

	public GrandeAreaAtuacaoService getGrandeAreaService() {
		return grandeAreaService;
	}

	public void setGrandeAreaService(GrandeAreaAtuacaoService grandeAreaService) {
		this.grandeAreaService = grandeAreaService;
	}

	public AreaAtuacao getAreaSelecionada() {
		return areaSelecionada;
	}

	public void setAreaSelecionada(AreaAtuacao areaSelecionada) {
		this.areaSelecionada = areaSelecionada;
	}

	public EspecializacaoAreaAtuacao getEspecSelecionada() {
		return especSelecionada;
	}

	public void setEspecSelecionada(EspecializacaoAreaAtuacao especSelecionada) {
		this.especSelecionada = especSelecionada;
	}

	public String getGdeAreaInserindoOuEditando() {
		return gdeAreaInserindoOuEditando;
	}

	public void setGdeAreaInserindoOuEditando(String gdeAreaInserindoOuEditando) {
		this.gdeAreaInserindoOuEditando = gdeAreaInserindoOuEditando;
	}

	public String getAreaInserindoOuEditando() {
		return areaInserindoOuEditando;
	}

	public void setAreaInserindoOuEditando(String areaInserindoOuEditando) {
		this.areaInserindoOuEditando = areaInserindoOuEditando;
	}

	public String getSubAreaInserindoOuEditando() {
		return subAreaInserindoOuEditando;
	}

	public void setSubAreaInserindoOuEditando(String subAreaInserindoOuEditando) {
		this.subAreaInserindoOuEditando = subAreaInserindoOuEditando;
	}

	public String getEspecInserindoOuEditando() {
		return especInserindoOuEditando;
	}

	public void setEspecInserindoOuEditando(String especInserindoOuEditando) {
		this.especInserindoOuEditando = especInserindoOuEditando;
	}

	public SubAreaAtuacaoService getSubAreaAtuacaoService() {
		return subAreaAtuacaoService;
	}

	public void setSubAreaAtuacaoService(SubAreaAtuacaoService subAreaAtuacaoService) {
		this.subAreaAtuacaoService = subAreaAtuacaoService;
	}

}

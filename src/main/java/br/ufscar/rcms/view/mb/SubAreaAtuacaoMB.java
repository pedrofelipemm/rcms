package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufscar.rcms.factory.AreaAtuacaoFactory;
import br.ufscar.rcms.modelo.entidades.EspecializacaoAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import br.ufscar.rcms.servico.SubAreaAtuacaoService;

@ViewScoped
@ManagedBean(name = "subAreaAtuacaoMB")
public class SubAreaAtuacaoMB extends AbstractMB {

	private static final long serialVersionUID = -1374520348706209763L;

	@ManagedProperty("#{subAreaAtuacaoService}")
	private SubAreaAtuacaoService subAreaAtuacaoService;

	private SubAreaAtuacao subArea;
	private transient DataModel<EspecializacaoAreaAtuacao> especializacoes;
	private transient DataModel<SubAreaAtuacao> todasAsSubAreas;
	private EspecializacaoAreaAtuacao especializacaoSelecionada;

	@PostConstruct
	public void inicializar() {
		limparDados();
		carregarDados();
	}

	private void carregarDados() {
		setTodasAsSubAreas(new ListDataModel<SubAreaAtuacao>(
				getSubAreaAtuacaoService().buscarTodas()));
		setEspecializacaoSelecionada(new EspecializacaoAreaAtuacao());

		SubAreaAtuacao areaEditar = (SubAreaAtuacao) getFlashObject(FLASH_KEY_SUBAREA_ATUACAO);
		if (areaEditar != null) {
			setSubArea(areaEditar);
			setEspecializacoes(new ListDataModel<EspecializacaoAreaAtuacao>(
					getSubArea().getEspecializacoes()));
		}

	}

	private void limparDados() {
		setSubArea(AreaAtuacaoFactory.createSubAreaAtuacaoEmpty());
	}

	public void salvar() {

		if (getSubArea().getId() != null) {
			getSubAreaAtuacaoService().alterar(getSubArea());
			adicionarMensagemInfoByKey("area.atuacao.alterada.sucesso",
					getSubArea().getDescricao());
		} else {
			getSubAreaAtuacaoService().salvar(getSubArea());
			adicionarMensagemInfoByKey("area.atuacao.cadastrada.sucesso",
					getSubArea().getDescricao());
		}
		setEspecializacoes(new ListDataModel<EspecializacaoAreaAtuacao>(getSubArea().getEspecializacoes()));

	}

	public String editar(SubAreaAtuacao area) {
		setFlashObject(FLASH_KEY_SUBAREA_ATUACAO, area);

		return CADASTRO_SUBAREA_ATUACAO;
	}

	public String excluir(SubAreaAtuacao area) {

		getSubAreaAtuacaoService().remover(area);

		return CONSULTA_SUBAREA_ATUACAO;
	}

	public void adicionarEspecializacao() {
		getSubArea().getEspecializacoes().add(getEspecializacaoSelecionada());
		setEspecializacaoSelecionada(new EspecializacaoAreaAtuacao());
		setEspecializacoes(new ListDataModel<EspecializacaoAreaAtuacao>(getSubArea().getEspecializacoes()));
	}

	public void removerEspecializacao(EspecializacaoAreaAtuacao espec) {
		this.getSubArea().getEspecializacoes().remove(
				this.getSubArea().getEspecializacoes().indexOf(espec));
		setEspecializacoes(new ListDataModel<EspecializacaoAreaAtuacao>(
				this.getSubArea().getEspecializacoes()));
	}

	public SubAreaAtuacaoService getSubAreaAtuacaoService() {
		return subAreaAtuacaoService;
	}

	public void setSubAreaAtuacaoService(SubAreaAtuacaoService subAreaAtuacaoService) {
		this.subAreaAtuacaoService = subAreaAtuacaoService;
	}

	public DataModel<EspecializacaoAreaAtuacao> getEspecializacoes() {
		return especializacoes;
	}

	public void setEspecializacoes(DataModel<EspecializacaoAreaAtuacao> especializacoes) {
		this.especializacoes = especializacoes;
	}

	public SubAreaAtuacao getSubArea() {
		return subArea;
	}

	public void setSubArea(SubAreaAtuacao subArea) {
		this.subArea = subArea;
	}

	public DataModel<SubAreaAtuacao> getTodasAsSubAreas() {
		return todasAsSubAreas;
	}

	public void setTodasAsSubAreas(DataModel<SubAreaAtuacao> todasAsSubAreas) {
		this.todasAsSubAreas = todasAsSubAreas;
	}

	public EspecializacaoAreaAtuacao getEspecializacaoSelecionada() {
		return especializacaoSelecionada;
	}

	public void setEspecializacaoSelecionada(EspecializacaoAreaAtuacao especializacaoSelecionada) {
		this.especializacaoSelecionada = especializacaoSelecionada;
	}

}

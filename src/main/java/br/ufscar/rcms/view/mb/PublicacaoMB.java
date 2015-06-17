package br.ufscar.rcms.view.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufscar.rcms.factory.PublicacaoFactory;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;
import br.ufscar.rcms.modelo.entidades.OutraProducaoBibliografica;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.modelo.entidades.TextoEmJornal;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.PublicacaoService;

@ViewScoped
@ManagedBean(name = "publicacaoMB")
public class PublicacaoMB extends AbstractMB {

	@ManagedProperty("#{pesquisadorService}")
	private PesquisadorService pesquisadorService;
	@ManagedProperty("#{publicacaoService}")
	private PublicacaoService publicacaoService;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(PublicacaoMB.class);

	private static final long serialVersionUID = -3678684230889264324L;
	private List<Pesquisador> todosPesquisadores;
	private List<CitacaoBibliografica> todasCitacoesDoPesquisador;
	private CitacaoBibliografica nomeCitacao;
	private Producao publicacao;
	private Pesquisador pesquisadorSelecionado;
	private String tipoPublicacao;
	private List<Producao> publicacoes;

	@PostConstruct
	public void inicializar() {
		limparDados();
		carregarDados();
	}

	public void adicionarNomeCitacao() {
		if(nomeCitacao != null){
			publicacao.getCitacaoBibliograficas().add(nomeCitacao);
		}
	}

	public void excluirNomeCitacao(CitacaoBibliografica citacao) {
		publicacao.getCitacaoBibliograficas().remove(citacao);
	}

	public String salvar() {
		try {

			if (publicacao != null) {
				Producao publicacaoTipada = PublicacaoFactory.CastProducaoByTipo(tipoPublicacao, publicacao);

				getPublicacaoService().saveOrUpdate(publicacaoTipada);

				adicionarMensagemInfoByKey("publicacao.salva.sucesso",
						publicacao.getTitulo());

				limparDados();

				keepMessagesOnRedirect();
			}

		} catch (Exception exception) {
			adicionarMensagemErroByKey("erro.salvar.publicacao",
					publicacao.getTitulo());
			LOGGER.error(exception.getMessage(), exception);
		} 
		
	    return CONSULTA_PUBLICACAO;
	}
	
	public String editar(Producao producao){
		setFlashObject(FLASH_KEY_PUBLICACAO, producao);

        return CADASTRO_PUBLICACAO;
	}
	
	public String excluir(Producao producao){
		publicacaoService.remove(producao);
		limparDados();
		return CONSULTA_PUBLICACAO;
	}

	public void changePesquisador() {
		if(pesquisadorSelecionado != null){
			pesquisadorSelecionado = pesquisadorService
					.buscarTodosDados(pesquisadorSelecionado.getIdUsuario());
			todasCitacoesDoPesquisador = pesquisadorSelecionado
					.getCitacaoBibliograficas();
		}else{
			todasCitacoesDoPesquisador.clear();
		}
	}

	@Override
	protected void limparDados() {
		todosPesquisadores = new ArrayList<Pesquisador>();
		todasCitacoesDoPesquisador = new ArrayList<CitacaoBibliografica>();
		nomeCitacao = new CitacaoBibliografica();
		publicacao = new OutraProducaoBibliografica();
		pesquisadorSelecionado = null;
		tipoPublicacao = "";
		publicacoes = new ArrayList<Producao>();
	}

	@Override
	protected void carregarDados() {
		todosPesquisadores = pesquisadorService.buscarTodos();
		publicacoes = publicacaoService.buscarTodas();
		
		Producao publicacaoEditando = (Producao)getFlashObject(FLASH_KEY_PUBLICACAO);
		if(publicacaoEditando != null){
			this.publicacao = publicacaoService.buscarPorId(publicacaoEditando.getIdProducao());
		}
	}
	
	public CitacaoBibliografica getNomeCitacao() {
		return nomeCitacao;
	}

	public void setNomeCitacao(CitacaoBibliografica nomeCitacao) {
		this.nomeCitacao = nomeCitacao;
	}

	public Producao getPublicacao() {
		return publicacao;
	}

	public void setPublicacao(Producao publicacao) {
		this.publicacao = publicacao;
	}
	
	public List<Pesquisador> getTodosPesquisadores() {
		return todosPesquisadores;
	}

	public void setTodosPesquisadores(List<Pesquisador> todosPesquisadores) {
		this.todosPesquisadores = todosPesquisadores;
	}

	public PesquisadorService getPesquisadorService() {
		return pesquisadorService;
	}

	public void setPesquisadorService(PesquisadorService pesquisadorService) {
		this.pesquisadorService = pesquisadorService;
	}

	public Pesquisador getPesquisadorSelecionado() {
		return pesquisadorSelecionado;
	}

	public void setPesquisadorSelecionado(Pesquisador pesquisadorSelecionado) {
		this.pesquisadorSelecionado = pesquisadorSelecionado;
	}

	public List<CitacaoBibliografica> getTodasCitacoesDoPesquisador() {
		return todasCitacoesDoPesquisador;
	}

	public void setTodasCitacoesDoPesquisador(
			List<CitacaoBibliografica> todasCitacoesDoPesquisador) {
		this.todasCitacoesDoPesquisador = todasCitacoesDoPesquisador;
	}

	public String getTipoPublicacao() {
		return tipoPublicacao;
	}

	public void setTipoPublicacao(String tipoPublicacao) {
		this.tipoPublicacao = tipoPublicacao;
	}

	public PublicacaoService getPublicacaoService() {
		return publicacaoService;
	}

	public void setPublicacaoService(PublicacaoService publicacaoService) {
		this.publicacaoService = publicacaoService;
	}

	public List<Producao> getPublicacoes() {
		return publicacoes;
	}

	public void setPublicacoes(List<Producao> publicacoes) {
		this.publicacoes = publicacoes;
	}
}

package br.ufscar.rcms.view.mb;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.AtuacaoPesquisador;
import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.modelo.entidades.EspecializacaoAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import br.ufscar.rcms.modelo.lattes.PesquisadorLattes;
import br.ufscar.rcms.servico.AreaAtuacaoService;
import br.ufscar.rcms.servico.IdiomaService;
import br.ufscar.rcms.servico.LattesService;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.exception.CurriculoLattesNaoEncontradoException;

@ViewScoped
@ManagedBean(name = "pesquisadorMB")
public class PesquisadorMB extends AbstractMB {

    private static final long serialVersionUID = 7023051572658948461L;

    private static final Logger LOGGER = LoggerFactory.getLogger(PesquisadorMB.class);

    @ManagedProperty("#{pesquisadorService}")
    private PesquisadorService pesquisadorService;

    @ManagedProperty("#{areaAtuacaoService}")
    private AreaAtuacaoService areaAtuacaoService;

    @ManagedProperty("#{idiomaService}")
    private IdiomaService idiomaService;

    @ManagedProperty("#{lattesService}")
    private LattesService lattesService;

    private Pesquisador pesquisador;
    
    private transient Part fotoPesquisador;

    private List<Idioma> idiomas;

    private Idioma idiomaSelecionado;
    
    private GrandeAreaAtuacao grandeAreaSelecionada;
    private AreaAtuacao areaAtuacaoSelecionada;
    private DataModel<AreaAtuacao> areasAtuacaoParaSelecionar;
    private SubAreaAtuacao subAreaAtuacaoSelecionada;
    private DataModel<SubAreaAtuacao> subAreasParaSelecionar;
    private EspecializacaoAreaAtuacao especializacaoSelecionada;
    private DataModel<EspecializacaoAreaAtuacao> especializacoesParaSelecionar;
    private AtuacaoPesquisador atuacaoSelecionada;

    private transient DataModel<Pesquisador> pesquisadores;

    @PostConstruct
    public void inicializar() {

        limparDados();
        carregarDados();
    }

    protected void carregarDados() {

        // areas = areaAtuacaoService.buscarTodas();

        idiomas = idiomaService.buscarTodas();

        pesquisadores = new ListDataModel<Pesquisador>(pesquisadorService.buscarTodos());

        Pesquisador pesquisadorEdicao = (Pesquisador) getFlashObject(FLASH_KEY_PESQUISADOR);
        if (pesquisadorEdicao != null) {
            pesquisador = pesquisadorService.buscarTodosDados(pesquisadorEdicao.getIdUsuario());

            // TODO PEDRO
            if (pesquisador.getEndereco() == null) {
                pesquisadorEdicao.setEndereco(new Endereco());
            }
        }
    }

    protected void limparDados() {
        pesquisador = new Pesquisador();
        // TODO PEDRO
        pesquisador.setEndereco(new Endereco());
    }

    public String salvar() {

        // TODO PEDRO TRATAR
        if (fotoPesquisador != null) {
            converterFotoPesquisador(pesquisador);
        }

        pesquisadorService.salvarOuAtualizar(pesquisador);

        adicionarMensagemInfoByKey("pesquisador.salvo.sucesso", pesquisador.getNome());

        limparDados();

        return CONSULTA_PESQUISADORES;
    }

    public String exibir(Pesquisador pesquisador) {

        pesquisador = pesquisadorService.buscarTodosDados(pesquisador.getIdUsuario());

        setFlashObject(FLASH_KEY_PESQUISADOR, pesquisador);

        return EXIBE_PESQUISADOR;
    }

    public String editar(Pesquisador pesquisador) {

        setFlashObject(FLASH_KEY_PESQUISADOR, pesquisador);

        return CADASTRO_PESQUISADOR;
    }

    public String excluir(Pesquisador pesquisador) {

        pesquisadorService.remover(pesquisador);

        return CONSULTA_PESQUISADORES;
    }

    private void converterFotoPesquisador(Pesquisador pesquisador) {

        try {
            pesquisador.setFoto(IOUtils.toByteArray(fotoPesquisador.getInputStream()));
        } catch (IOException e) {
            // TODO PEDRO TRATAR EXCEPTION
            LOGGER.error(e.getMessage(), e);
        }
    }

    public void pesquisar() {

    }


    // public void addIdioma() {
    // if (idiomaSelecionado != null) {
    // pesquisador.getIdiomas().add(idiomaSelecionado);
    // idiomas.remove(idiomas.indexOf(idiomaSelecionado));
    // }
    // }
    //
    // public void removerIdioma() {
    // if (idiomaSelecionado != null) {
    // pesquisador.getIdiomas().remove(pesquisador.getIdiomas().indexOf(idiomaSelecionado));
    // idiomas.add(idiomaSelecionado);
    // }
    // }

    public String baixarDadosPesquisadorLattes() {
        try {
            /*
             * Random gerador = new Random(); String.valueOf(gerador.nextInt());
             */
            lattesService.executarComandoLattes(getPesquisador());
            return CONSULTA_PESQUISADORES;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void carregarCurriculoLattes() {

        try {
            PesquisadorLattes pesquisadorLattes = lattesService.carregarCurriculoLattes(pesquisador.getCodigoLattes());
        } catch (CurriculoLattesNaoEncontradoException e) {
            // TODO PEDRO
            LOGGER.error(e.getMessage(), e);
        }

    }

    public String salvarDadosLattes(Pesquisador pesquisador) {

        try {

            this.pesquisador = lattesService.salvarDadosLattes(pesquisador);
            adicionarMensagemInfoByKey("pesquisador.importacao.sucesso", this.pesquisador.getNome());
            limparDados();

        } catch (InvalidDataAccessApiUsageException e) {
            adicionarMensagemAlerta("Curr�culo lattes j� importado!");
        } catch (CurriculoLattesNaoEncontradoException e) {
            adicionarMensagemErro(e.getMessage());
        }

        keepMessagesOnRedirect();
        return CONSULTA_PESQUISADORES;
    }
    
    public void changeGrandeAreaSelecionada(){
    	this.areasAtuacaoParaSelecionar = null;
		this.subAreasParaSelecionar = null;
		this.especializacoesParaSelecionar = null;
		
    	if(this.grandeAreaSelecionada != null){
    		this.areasAtuacaoParaSelecionar = new ListDataModel<AreaAtuacao>(this.grandeAreaSelecionada.getAreasDeAtuacao());
    	}
    }
    
    public void changeAreaSelecionada(){
    	subAreasParaSelecionar = null;
		especializacoesParaSelecionar = null;
		
    	if(this.areaAtuacaoSelecionada != null){
    		this.subAreasParaSelecionar = new ListDataModel<SubAreaAtuacao>(this.areaAtuacaoSelecionada.getSubAreasAtuacao());
    	}
    }
    
    public void changeSubAreaSelecionada(){
    	
    	especializacoesParaSelecionar = null;
    	if(this.subAreaAtuacaoSelecionada != null){
    		this.especializacoesParaSelecionar = new ListDataModel<EspecializacaoAreaAtuacao>(this.subAreaAtuacaoSelecionada.getEspecializacoes());
    	}
    }
    
    public void removerAtuacao(){
    	if (atuacaoSelecionada != null){
    		pesquisador.getAreaAtuacoes().remove(atuacaoSelecionada);
    	}
    }
    
    public void adicionarAtuacao(){
    	
    	if (grandeAreaSelecionada != null){
    		AtuacaoPesquisador a = new AtuacaoPesquisador(grandeAreaSelecionada, areaAtuacaoSelecionada,
    				subAreaAtuacaoSelecionada, especializacaoSelecionada, pesquisador);

        	pesquisador.getAreaAtuacoes().add(a);
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

    public LattesService getLattesService() {
        return lattesService;
    }

    public void setLattesService(LattesService lattesService) {
        this.lattesService = lattesService;
    }

    public DataModel<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(DataModel<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }
    
    public GrandeAreaAtuacao getGrandeAreaSelecionada() {
		return grandeAreaSelecionada;
	}

	public void setGrandeAreaSelecionada(GrandeAreaAtuacao grandeAreaSelecionada) {
		this.grandeAreaSelecionada = grandeAreaSelecionada;
	}

	public AreaAtuacao getAreaAtuacaoSelecionada() {
		return areaAtuacaoSelecionada;
	}

	public void setAreaAtuacaoSelecionada(AreaAtuacao areaAtuacaoSelecionada) {
		this.areaAtuacaoSelecionada = areaAtuacaoSelecionada;
	}

	public DataModel<AreaAtuacao> getAreasAtuacaoParaSelecionar() {
		return areasAtuacaoParaSelecionar;
	}

	public void setAreasAtuacaoParaSelecionar(
			DataModel<AreaAtuacao> areasAtuacaoParaSelecionar) {
		this.areasAtuacaoParaSelecionar = areasAtuacaoParaSelecionar;
	}

	public SubAreaAtuacao getSubAreaAtuacaoSelecionada() {
		return subAreaAtuacaoSelecionada;
	}

	public void setSubAreaAtuacaoSelecionada(SubAreaAtuacao subAreaAtuacaoSelecionada) {
		this.subAreaAtuacaoSelecionada = subAreaAtuacaoSelecionada;
	}

	public DataModel<SubAreaAtuacao> getSubAreasParaSelecionar() {
		return subAreasParaSelecionar;
	}

	public void setSubAreasParaSelecionar(DataModel<SubAreaAtuacao> subAreasParaSelecionar) {
		this.subAreasParaSelecionar = subAreasParaSelecionar;
	}

	public EspecializacaoAreaAtuacao getEspecializacaoSelecionada() {
		return especializacaoSelecionada;
	}

	public void setEspecializacaoSelecionada(EspecializacaoAreaAtuacao especializacaoSelecionada) {
		this.especializacaoSelecionada = especializacaoSelecionada;
	}

	public DataModel<EspecializacaoAreaAtuacao> getEspecializacoesParaSelecionar() {
		return especializacoesParaSelecionar;
	}

	public void setEspecializacoesParaSelecionar(
			DataModel<EspecializacaoAreaAtuacao> especializacoesParaSelecionar) {
		this.especializacoesParaSelecionar = especializacoesParaSelecionar;
	}

	public AtuacaoPesquisador getAtuacaoSelecionada() {
		return atuacaoSelecionada;
	}

	public void setAtuacaoSelecionada(AtuacaoPesquisador atuacaoSelecionada) {
		this.atuacaoSelecionada = atuacaoSelecionada;
	}

}

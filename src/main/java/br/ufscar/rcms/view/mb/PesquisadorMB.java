package br.ufscar.rcms.view.mb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import br.ufscar.rcms.factory.CompreensaoIdiomaFactory;
import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.AtuacaoPesquisador;
import br.ufscar.rcms.modelo.entidades.CompreensaoIdioma;
import br.ufscar.rcms.modelo.entidades.EspecializacaoAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.modelo.entidades.LinhaDePesquisa;
import br.ufscar.rcms.modelo.entidades.OrganizacaoEvento;
import br.ufscar.rcms.modelo.entidades.ParticipacaoEvento;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.SubAreaAtuacao;
import br.ufscar.rcms.servico.AreaAtuacaoService;
import br.ufscar.rcms.servico.GrandeAreaAtuacaoService;
import br.ufscar.rcms.servico.IdiomaService;
import br.ufscar.rcms.servico.LattesService;
import br.ufscar.rcms.servico.LinhaDePesquisaService;
import br.ufscar.rcms.servico.OrganizacaoEventoService;
import br.ufscar.rcms.servico.ParticipacaoEventoService;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.exception.CurriculoLattesNaoEncontradoException;
import br.ufscar.rcms.servico.exception.PesquisadorNaoEncontradoException;

@ViewScoped
@ManagedBean(name = "pesquisadorMB")
public class PesquisadorMB extends AbstractMB {

    private static final long serialVersionUID = 7023051572658948461L;

    private static final Logger LOGGER = LoggerFactory.getLogger(PesquisadorMB.class);

    @ManagedProperty("#{imageCacheMB}")
    private ImageCacheMB cache;

    @ManagedProperty("#{pesquisadorService}")
    private PesquisadorService pesquisadorService;

    @ManagedProperty("#{areaAtuacaoService}")
    private AreaAtuacaoService areaAtuacaoService;

    @ManagedProperty("#{idiomaService}")
    private IdiomaService idiomaService;

    @ManagedProperty("#{lattesService}")
    private LattesService lattesService;

    @ManagedProperty("#{grandeAreaAtuacaoService}")
    private GrandeAreaAtuacaoService grandeAreaService;

    @ManagedProperty("#{linhaDePesquisaService}")
    private LinhaDePesquisaService linhaDePesquisaService;

    @ManagedProperty("#{participacaoEventoService}")
    private ParticipacaoEventoService participacaoEventoService;

    @ManagedProperty("#{organizacaoEventoService}")
    private OrganizacaoEventoService organizacaoEventoService;

    private Pesquisador pesquisador;
    private transient DataModel<Pesquisador> pesquisadores;
    private transient Part fotoPesquisador;

    private CompreensaoIdioma compreensaoIdioma;
    private transient DataModel<CompreensaoIdioma> compreensaoIdiomas;

    private Idioma idioma;
    private transient List<Idioma> idiomas;

    private GrandeAreaAtuacao grandeAreaSelecionada;
    private DataModel<GrandeAreaAtuacao> todasAsGrandeAreas;

    private AreaAtuacao areaAtuacaoSelecionada;
    private DataModel<AreaAtuacao> areasAtuacaoParaSelecionar;

    private SubAreaAtuacao subAreaAtuacaoSelecionada;
    private DataModel<SubAreaAtuacao> subAreasParaSelecionar;

    private EspecializacaoAreaAtuacao especializacaoSelecionada;
    private DataModel<EspecializacaoAreaAtuacao> especializacoesParaSelecionar;

    private AtuacaoPesquisador atuacaoSelecionada;

    private LinhaDePesquisa linhaDePesquisaSelecionada;
    private transient List<LinhaDePesquisa> linhasDePesquisa;

    private ParticipacaoEvento participacaoEvento;
    private transient List<ParticipacaoEvento> participacaoEventos;
    private List<ParticipacaoEvento> participacaoEventosToDelete;

    private OrganizacaoEvento organizacaoEvento;
    private transient List<OrganizacaoEvento> organizacaoEventos;
    private List<OrganizacaoEvento> organizacaoEventosToDelete;

    @PostConstruct
    public void inicializar() {

        limparDados();
        carregarDados();
    }

    protected void carregarDados() {
        pesquisadores = new ListDataModel<Pesquisador>(pesquisadorService.buscarTodos());
        idiomas = new ArrayList<Idioma>(idiomaService.buscarTodos());
        todasAsGrandeAreas = new ListDataModel<GrandeAreaAtuacao>(grandeAreaService.buscarTodas());
        linhasDePesquisa = new ArrayList<LinhaDePesquisa>(linhaDePesquisaService.buscarTodas());

        Pesquisador pesquisadorEdicao = (Pesquisador) getFlashObject(FLASH_KEY_PESQUISADOR);
        if (pesquisadorEdicao != null) {
            pesquisador = pesquisadorService.buscarTodosDados(pesquisadorEdicao.getIdUsuario());
            removeNullValues(pesquisador.getCompreensaoIdiomas(), pesquisador.getParticipacaoEventos());
        }
    }

    protected void limparDados() {
        pesquisador = new Pesquisador();
        compreensaoIdioma = new CompreensaoIdioma();
        participacaoEventosToDelete = new ArrayList<ParticipacaoEvento>();
        participacaoEvento = new ParticipacaoEvento();
        organizacaoEvento = new OrganizacaoEvento();
        organizacaoEventosToDelete = new ArrayList<OrganizacaoEvento>();
        getFlash().clear();
    }

    public String salvar() {

        try {
            pesquisadorService.salvarOuAtualizar(pesquisador);
            participacaoEventoService.remover(participacaoEventosToDelete);
            organizacaoEventoService.remover(organizacaoEventosToDelete);
            adicionarMensagemInfoByKey("pesquisador.salvo.sucesso", pesquisador.getNome());

            limparDados();

            keepMessagesOnRedirect();
            return CONSULTA_PESQUISADORES;
        } catch (Exception exception) {
            adicionarMensagemErroByKey("erro.salvar.pesquisador", pesquisador.getNome());
            LOGGER.error(exception.getMessage(), exception);
        }
        return CONSULTA_PESQUISADORES;
    }

    public void uploadFile() {
        cache.sendFotoPesquisador(fotoPesquisador);
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

        try {
            pesquisadorService.remover(pesquisador);
        } catch (PesquisadorNaoEncontradoException e) {
            adicionarMensagemErroByKey("pesquisador.nao.econtrado", pesquisador.getNome());
        }
        limparDados();

        return CONSULTA_PESQUISADORES;
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

    public DataModel<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(DataModel<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

    // Foto
    public Part getFotoPesquisador() {
        return fotoPesquisador;
    }

    public void setFotoPesquisador(Part fotoPesquisador) {
        this.fotoPesquisador = fotoPesquisador;
    }

    // Lattes
    public LattesService getLattesService() {
        return lattesService;
    }

    public void setLattesService(LattesService lattesService) {
        this.lattesService = lattesService;
    }

    public String baixarDadosPesquisadorLattes() {
        try {
            lattesService.executarComandoLattes(getPesquisador());
            return CONSULTA_PESQUISADORES;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String salvarDadosLattes(Pesquisador pesquisador) {

        try {

            this.pesquisador = lattesService.salvarDadosLattes(pesquisador);
            adicionarMensagemInfoByKey("pesquisador.importacao.sucesso", this.pesquisador.getNome());
            limparDados();

        } catch (InvalidDataAccessApiUsageException e) {
            adicionarMensagemAlerta("Currículo lattes já importado!");
        } catch (CurriculoLattesNaoEncontradoException e) {
            adicionarMensagemErro(e.getMessage());
        }

        keepMessagesOnRedirect();
        return CONSULTA_PESQUISADORES;
    }

    public void pesquisar() {
    }

    // Idioma
    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public CompreensaoIdioma getCompreensaoIdioma() {
        return compreensaoIdioma;
    }

    public void setCompreensaoIdioma(CompreensaoIdioma compreensaoIdioma) {
        this.compreensaoIdioma = compreensaoIdioma;
    }

    public IdiomaService getIdiomaService() {
        return idiomaService;
    }

    public void setIdiomaService(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    public void adicionarCompreensaoIdioma() {
        pesquisador.addCompreensaoIdiomas(CompreensaoIdiomaFactory.createCompreensaoIdioma(idioma,
                compreensaoIdioma.getProficiencia(), pesquisador));
        compreensaoIdioma = new CompreensaoIdioma();
    }

    public void removerCompreensaoIdioma(CompreensaoIdioma compreensaoIdioma) {
        pesquisador.removeCompreensaoIdiomas(compreensaoIdioma);
    }

    public DataModel<CompreensaoIdioma> getCompreensaoIdiomas() {
        return compreensaoIdiomas;
    }

    public void setCompreensaoIdiomas(DataModel<CompreensaoIdioma> compreensaoIdiomas) {
        this.compreensaoIdiomas = compreensaoIdiomas;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    // Área de Atuação
    public void changeGrandeAreaSelecionada(){
    	areasAtuacaoParaSelecionar = null;
		subAreasParaSelecionar = null;
		especializacoesParaSelecionar = null;

    	if(grandeAreaSelecionada != null){
    		areasAtuacaoParaSelecionar = new ListDataModel<AreaAtuacao>(grandeAreaSelecionada.getAreasDeAtuacao());
    	}
    }

    public void changeAreaSelecionada(){
    	subAreasParaSelecionar = null;
		especializacoesParaSelecionar = null;

    	if(areaAtuacaoSelecionada != null){
    		subAreasParaSelecionar = new ListDataModel<SubAreaAtuacao>(areaAtuacaoSelecionada.getSubAreasAtuacao());
    	}
    }

    public void changeSubAreaSelecionada(){

    	especializacoesParaSelecionar = null;
    	if(subAreaAtuacaoSelecionada != null){
    		especializacoesParaSelecionar = new ListDataModel<EspecializacaoAreaAtuacao>(subAreaAtuacaoSelecionada.getEspecializacoes());
    	}
    }

    public void removerAtuacao(){
    	if (atuacaoSelecionada != null){
    		pesquisador.getAreaAtuacoes().remove(atuacaoSelecionada);
    	}
    }

    public void adicionarAtuacao(){

    	if (grandeAreaSelecionada != null){
    		areaAtuacaoSelecionada.setGrandeAreaAtuacao(grandeAreaSelecionada);
    		subAreaAtuacaoSelecionada.setAreaAtuacao(areaAtuacaoSelecionada);
    		especializacaoSelecionada.setSubAreaAtuacao(subAreaAtuacaoSelecionada);

    		AtuacaoPesquisador a = new AtuacaoPesquisador(especializacaoSelecionada, pesquisador);

        	pesquisador.getAreaAtuacoes().add(a);
    	}
    }

    public void adicionarParticipacaoEvento() {
        pesquisador.addParticipacaoEventos(participacaoEvento);
        participacaoEvento.setPesquisador(pesquisador);
        participacaoEvento = new ParticipacaoEvento();
    }

    public void removerParticipacaoEvento(ParticipacaoEvento participacaoEvento) {
        pesquisador.removeParticipacaoEventos(participacaoEvento);
        participacaoEventosToDelete.add(participacaoEvento);
    }

    public void adicionarOrganizacaoEvento(){
    	pesquisador.addOrgazicaoEventos(organizacaoEvento);
    	organizacaoEvento.setPesquisador(pesquisador);
    	organizacaoEvento = new OrganizacaoEvento();
    }

    public void removerOrganizacaoEvento(OrganizacaoEvento organizacaoEvento){
    	pesquisador.removeOrganizacaoEventos(organizacaoEvento);
    	organizacaoEventosToDelete.add(organizacaoEvento);
    }

    public AreaAtuacaoService getAreaAtuacaoService() {
        return areaAtuacaoService;
    }

    public void setAreaAtuacaoService(AreaAtuacaoService areaAtuacaoService) {
        this.areaAtuacaoService = areaAtuacaoService;
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

    public void setAreasAtuacaoParaSelecionar(DataModel<AreaAtuacao> areasAtuacaoParaSelecionar) {
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

    public void setEspecializacoesParaSelecionar(DataModel<EspecializacaoAreaAtuacao> especializacoesParaSelecionar) {
        this.especializacoesParaSelecionar = especializacoesParaSelecionar;
    }

    public AtuacaoPesquisador getAtuacaoSelecionada() {
        return atuacaoSelecionada;
    }

    public void setAtuacaoSelecionada(AtuacaoPesquisador atuacaoSelecionada) {
        this.atuacaoSelecionada = atuacaoSelecionada;
    }

    public DataModel<GrandeAreaAtuacao> getTodasAsGrandeAreas() {
        return todasAsGrandeAreas;
    }

    public GrandeAreaAtuacaoService getGrandeAreaService() {
        return grandeAreaService;
    }

    public void setGrandeAreaService(GrandeAreaAtuacaoService grandeAreaService) {
        this.grandeAreaService = grandeAreaService;
    }

    public void setTodasAsGrandeAreas(DataModel<GrandeAreaAtuacao> todasAsGrandeAreas) {
        this.todasAsGrandeAreas = todasAsGrandeAreas;
    }

    // Linha De Pesquisa
    public LinhaDePesquisa getLinhaDePesquisaSelecionada() {
        return linhaDePesquisaSelecionada;
    }

    public void setLinhaDePesquisaSelecionada(LinhaDePesquisa linhaDePesquisaSelecionada) {
        this.linhaDePesquisaSelecionada = linhaDePesquisaSelecionada;
    }

    public List<LinhaDePesquisa> getLinhasDePesquisa() {
        return linhasDePesquisa;
    }

    public void setLinhasDePesquisa(List<LinhaDePesquisa> linhasDePesquisa) {
        this.linhasDePesquisa = linhasDePesquisa;
    }

    public LinhaDePesquisaService getLinhaDePesquisaService() {
        return linhaDePesquisaService;
    }

    public void setLinhaDePesquisaService(LinhaDePesquisaService linhaDePesquisaService) {
        this.linhaDePesquisaService = linhaDePesquisaService;
    }

    public void adicionarLinhaDePesquisa() {

        if (linhaDePesquisaSelecionada != null) {

            pesquisador.getLinhasDePesquisa().add(linhaDePesquisaSelecionada);
        }
    }

    public void removerLinhaDePesquisa(LinhaDePesquisa linhaPesquisa) {
        pesquisador.getLinhasDePesquisa().remove(linhaPesquisa);
    }

    public ParticipacaoEvento getParticipacaoEvento() {
        return participacaoEvento;
    }

    public void setParticipacaoEvento(ParticipacaoEvento participacaoEvento) {
        this.participacaoEvento = participacaoEvento;
    }

    public List<ParticipacaoEvento> getParticipacaoEventos() {
        return participacaoEventos;
    }

    public void setParticipacaoEventos(List<ParticipacaoEvento> participacaoEventos) {
        this.participacaoEventos = participacaoEventos;
    }

    public ParticipacaoEventoService getParticipacaoEventoService() {
        return participacaoEventoService;
    }

    public void setParticipacaoEventoService(ParticipacaoEventoService participacaoEventoService) {
        this.participacaoEventoService = participacaoEventoService;
    }

    public List<ParticipacaoEvento> getParticipacaoEventosToDelete() {
        return participacaoEventosToDelete;
    }

    public void setParticipacaoEventosToDelete(List<ParticipacaoEvento> participacaoEventosToDelete) {
        this.participacaoEventosToDelete = participacaoEventosToDelete;
    }

    public ImageCacheMB getCache() {
        return cache;
    }

    public void setCache(ImageCacheMB cache) {
        this.cache = cache;
    }

	public OrganizacaoEvento getOrganizacaoEvento() {
		return organizacaoEvento;
	}

	public void setOrganizacaoEvento(OrganizacaoEvento organizacaoEvento) {
		this.organizacaoEvento = organizacaoEvento;
	}

	public List<OrganizacaoEvento> getOrganizacaoEventos() {
		return organizacaoEventos;
	}

	public void setOrganizacaoEventos(List<OrganizacaoEvento> organizacaoEventos) {
		this.organizacaoEventos = organizacaoEventos;
	}

	public List<OrganizacaoEvento> getOrganizacaoEventosToDelete() {
		return organizacaoEventosToDelete;
	}

	public void setOrganizacaoEventosToDelete(
			List<OrganizacaoEvento> organizacaoEventosToDelete) {
		this.organizacaoEventosToDelete = organizacaoEventosToDelete;
	}

	public OrganizacaoEventoService getOrganizacaoEventoService() {
		return organizacaoEventoService;
	}

	public void setOrganizacaoEventoService(OrganizacaoEventoService organizacaoEventoService) {
		this.organizacaoEventoService = organizacaoEventoService;
	}
}
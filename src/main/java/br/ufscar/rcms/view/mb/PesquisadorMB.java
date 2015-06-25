package br.ufscar.rcms.view.mb;

import static br.ufscar.rcms.util.FileUtils.extractFileExtension;
import static br.ufscar.rcms.util.MiscellanyUtil.isEmpty;

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

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import br.ufscar.rcms.comparator.PesquisadorComparator;
import br.ufscar.rcms.factory.CompreensaoIdiomaFactory;
import br.ufscar.rcms.modelo.entidades.AreaAtuacao;
import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.AtuacaoPesquisador;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;
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
import br.ufscar.rcms.servico.CitacaoBibliograficaService;
import br.ufscar.rcms.servico.GrandeAreaAtuacaoService;
import br.ufscar.rcms.servico.IdiomaService;
import br.ufscar.rcms.servico.LattesService;
import br.ufscar.rcms.servico.LinhaDePesquisaService;
import br.ufscar.rcms.servico.OrganizacaoEventoService;
import br.ufscar.rcms.servico.ParticipacaoEventoService;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.exception.ArquivoNaoEncontradoException;
import br.ufscar.rcms.servico.exception.CurriculoLattesNaoEncontradoException;
import br.ufscar.rcms.servico.exception.PesquisadorNaoEncontradoException;
import br.ufscar.rcms.view.model.SortableDataModel;

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

    @ManagedProperty("#{grandeAreaAtuacaoService}")
    private GrandeAreaAtuacaoService grandeAreaService;

    @ManagedProperty("#{linhaDePesquisaService}")
    private LinhaDePesquisaService linhaDePesquisaService;

    @ManagedProperty("#{citacaoBibliograficaService}")
    private CitacaoBibliograficaService citacaoBibliograficaService;

    @ManagedProperty("#{participacaoEventoService}")
    private ParticipacaoEventoService participacaoEventoService;

    @ManagedProperty("#{organizacaoEventoService}")
    private OrganizacaoEventoService organizacaoEventoService;

    private Pesquisador pesquisador;
    private DataModel<Pesquisador> pesquisadores;
    private Part fotoPesquisador;

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

    private CitacaoBibliografica citacaoBibliografica;
    private transient List<CitacaoBibliografica> citacoesBibliograficas;

    private ParticipacaoEvento participacaoEvento;
    private transient List<ParticipacaoEvento> participacaoEventos;

    private OrganizacaoEvento organizacaoEvento;
    private transient List<OrganizacaoEvento> organizacaoEventos;

    private transient List<ArtigoEmPeriodico> listaArtigoEmPeriodico;

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
    }

    @Override
    protected void carregarDados() {
        pesquisadores = new SortableDataModel<Pesquisador>(new ListDataModel<Pesquisador>(pesquisadorService.buscarTodos()));
        ((SortableDataModel<Pesquisador>) pesquisadores).sortBy(new PesquisadorComparator());
        idiomas = new ArrayList<Idioma>(idiomaService.buscarTodos());
        todasAsGrandeAreas = new ListDataModel<GrandeAreaAtuacao>(grandeAreaService.buscarTodas());
        linhasDePesquisa = new ArrayList<LinhaDePesquisa>(linhaDePesquisaService.buscarTodas());

        final Pesquisador pesquisadorEdicao = (Pesquisador) getFlashObject(FLASH_KEY_PESQUISADOR);
        if (pesquisadorEdicao != null) {
            pesquisador = pesquisadorService.buscarTodosDados(pesquisadorEdicao.getIdUsuario());
            removeNullValues(pesquisador.getCompreensaoIdiomas(), pesquisador.getParticipacaoEventos());

        }
    }

    @Override
    protected void limparDados() {
        pesquisador = new Pesquisador();
        compreensaoIdioma = new CompreensaoIdioma();
        citacaoBibliografica = new CitacaoBibliografica();
        participacaoEvento = new ParticipacaoEvento();
        organizacaoEvento = new OrganizacaoEvento();
        getFlash().clear();
    }

    public String salvar() {

        try {
            pesquisador = pesquisadorService.salvarOuAtualizar(pesquisador);
            adicionarMensagemInfoByKey("pesquisador.salvo.sucesso", pesquisador.getNome());

            limparDados();

        } catch (final Exception exception) {
            adicionarMensagemErroByKey("erro.salvar.pesquisador", pesquisador.getNome());
            LOGGER.error(exception.getMessage(), exception);
        }
        keepMessagesOnRedirect();
        return CONSULTA_PESQUISADORES;
    }

    public void uploadFile() {
        if (!isEmpty(fotoPesquisador)) {
            try {
                pesquisador.getFoto().setFile(IOUtils.toByteArray(fotoPesquisador.getInputStream()));
                pesquisador.getFoto().setFileExtension(extractFileExtension(fotoPesquisador.getSubmittedFileName()));
            } catch (final IOException ioException) {
                LOGGER.error(String.format("Erro ao realizar upload de imagem para o pesquisador: %s", pesquisador.getNome()), ioException);
                adicionarMensagemErroByKey("erro.enviar.imagem");
            }
        }
    }

    public String exibir(Pesquisador pesquisador) {

        pesquisador = pesquisadorService.buscarTodosDados(pesquisador.getIdUsuario());

        setFlashObject(FLASH_KEY_PESQUISADOR, pesquisador);

        return EXIBE_PESQUISADOR;
    }

    public String editar(final Pesquisador pesquisador) {

        setFlashObject(FLASH_KEY_PESQUISADOR, pesquisador);

        return CADASTRO_PESQUISADOR;
    }

    public String excluir(final Pesquisador pesquisador) {

        try {
            pesquisadorService.remover(pesquisador);
            adicionarMensagemInfoByKey("pesquisador.removido.sucesso", pesquisador.getNome());
        } catch (final PesquisadorNaoEncontradoException e) {
            adicionarMensagemErroByKey("pesquisador.nao.econtrado", pesquisador.getNome());
        }
        limparDados();

        keepMessagesOnRedirect();
        return CONSULTA_PESQUISADORES;
    }

    public PesquisadorService getPesquisadorService() {

        return pesquisadorService;
    }

    public void setPesquisadorService(final PesquisadorService pesquisadorService) {

        this.pesquisadorService = pesquisadorService;
    }

    public Pesquisador getPesquisador() {

        return pesquisador;
    }

    public void setPesquisador(final Pesquisador pesquisador) {

        this.pesquisador = pesquisador;
    }

    public DataModel<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(final DataModel<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

    // Foto
    public Part getFotoPesquisador() {
        return fotoPesquisador;
    }

    public void setFotoPesquisador(final Part fotoPesquisador) {
        this.fotoPesquisador = fotoPesquisador;
    }

    // Lattes
    public LattesService getLattesService() {
        return lattesService;
    }

    public void setLattesService(final LattesService lattesService) {
        this.lattesService = lattesService;
    }

    public String importarDadosPesquisadorLattes(final Pesquisador pesquisador) {
        try {
            lattesService.executarComandoLattes(pesquisador);

            return CONSULTA_PESQUISADORES;
        } catch (final IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String salvarDadosLattes(final Pesquisador pesquisador) {

        try {

            this.pesquisador = lattesService.salvarDadosLattes(pesquisador);
            adicionarMensagemInfoByKey("pesquisador.importacao.sucesso", this.pesquisador.getNome());
            limparDados();

        } catch (final InvalidDataAccessApiUsageException e) {
            LOGGER.error("Erro ao salvar dados do lattes", e);
            adicionarMensagemAlerta(String.format("Erro ao salvar dados importados do Lattes, pesquisador: %s ", pesquisador.getNome()));
        } catch (final CurriculoLattesNaoEncontradoException e) {
            LOGGER.error("Erro ao salvar dados do lattes", e);
            adicionarMensagemErro(e.getMessage());
        } catch (final ArquivoNaoEncontradoException e) {
            LOGGER.error("Erro ao salvar dados do lattes", e);
            adicionarMensagemErro(e.getMessage());
        } catch (final Exception exception) {
            adicionarMensagemErroByKey("erro.salvar.pesquisador", pesquisador.getNome());
            LOGGER.error(exception.getMessage(), exception);
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

    public void setIdiomas(final List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public CompreensaoIdioma getCompreensaoIdioma() {
        return compreensaoIdioma;
    }

    public void setCompreensaoIdioma(final CompreensaoIdioma compreensaoIdioma) {
        this.compreensaoIdioma = compreensaoIdioma;
    }

    public IdiomaService getIdiomaService() {
        return idiomaService;
    }

    public void setIdiomaService(final IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    public void adicionarCompreensaoIdioma() {
        pesquisador.addCompreensaoIdiomas(CompreensaoIdiomaFactory.createCompreensaoIdioma(idioma,
                compreensaoIdioma.getProficiencia(), pesquisador));
        compreensaoIdioma = new CompreensaoIdioma();
    }

    public void removerCompreensaoIdioma(final CompreensaoIdioma compreensaoIdioma) {
        pesquisador.removeCompreensaoIdiomas(compreensaoIdioma);
    }

    public DataModel<CompreensaoIdioma> getCompreensaoIdiomas() {
        return compreensaoIdiomas;
    }

    public void setCompreensaoIdiomas(final DataModel<CompreensaoIdioma> compreensaoIdiomas) {
        this.compreensaoIdiomas = compreensaoIdiomas;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(final Idioma idioma) {
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

            final AtuacaoPesquisador a = new AtuacaoPesquisador(especializacaoSelecionada, pesquisador);

        	pesquisador.getAreaAtuacoes().add(a);
    	}
    }

    public void adicionarCitacaoBibliografica() {
        pesquisador.addCitacaoBibliografica(citacaoBibliografica);
        citacaoBibliografica.setPesquisador(pesquisador);
        citacaoBibliografica = new CitacaoBibliografica();
    }

    public void removerCitacaoBibliografica(final CitacaoBibliografica citacaoBibliografica) {
        pesquisador.removeCitacaoBibliografica(citacaoBibliografica);
    }

    public void adicionarParticipacaoEvento() {
        pesquisador.addParticipacaoEventos(participacaoEvento);
        participacaoEvento.setPesquisador(pesquisador);
        participacaoEvento = new ParticipacaoEvento();
    }

    public void removerParticipacaoEvento(final ParticipacaoEvento participacaoEvento) {
        pesquisador.removeParticipacaoEventos(participacaoEvento);
    }

    public void adicionarOrganizacaoEvento(){
    	pesquisador.addOrgazicaoEventos(organizacaoEvento);
    	organizacaoEvento.setPesquisador(pesquisador);
    	organizacaoEvento = new OrganizacaoEvento();
    }

    public void removerOrganizacaoEvento(final OrganizacaoEvento organizacaoEvento){
    	pesquisador.removeOrganizacaoEventos(organizacaoEvento);
    }

    public AreaAtuacaoService getAreaAtuacaoService() {
        return areaAtuacaoService;
    }

    public void setAreaAtuacaoService(final AreaAtuacaoService areaAtuacaoService) {
        this.areaAtuacaoService = areaAtuacaoService;
    }

    public GrandeAreaAtuacao getGrandeAreaSelecionada() {
        return grandeAreaSelecionada;
    }

    public void setGrandeAreaSelecionada(final GrandeAreaAtuacao grandeAreaSelecionada) {
        this.grandeAreaSelecionada = grandeAreaSelecionada;
    }

    public AreaAtuacao getAreaAtuacaoSelecionada() {
        return areaAtuacaoSelecionada;
    }

    public void setAreaAtuacaoSelecionada(final AreaAtuacao areaAtuacaoSelecionada) {
        this.areaAtuacaoSelecionada = areaAtuacaoSelecionada;
    }

    public DataModel<AreaAtuacao> getAreasAtuacaoParaSelecionar() {
        return areasAtuacaoParaSelecionar;
    }

    public void setAreasAtuacaoParaSelecionar(final DataModel<AreaAtuacao> areasAtuacaoParaSelecionar) {
        this.areasAtuacaoParaSelecionar = areasAtuacaoParaSelecionar;
    }

    public SubAreaAtuacao getSubAreaAtuacaoSelecionada() {
        return subAreaAtuacaoSelecionada;
    }

    public void setSubAreaAtuacaoSelecionada(final SubAreaAtuacao subAreaAtuacaoSelecionada) {
        this.subAreaAtuacaoSelecionada = subAreaAtuacaoSelecionada;
    }

    public DataModel<SubAreaAtuacao> getSubAreasParaSelecionar() {
        return subAreasParaSelecionar;
    }

    public void setSubAreasParaSelecionar(final DataModel<SubAreaAtuacao> subAreasParaSelecionar) {
        this.subAreasParaSelecionar = subAreasParaSelecionar;
    }

    public EspecializacaoAreaAtuacao getEspecializacaoSelecionada() {
        return especializacaoSelecionada;
    }

    public void setEspecializacaoSelecionada(final EspecializacaoAreaAtuacao especializacaoSelecionada) {
        this.especializacaoSelecionada = especializacaoSelecionada;
    }

    public DataModel<EspecializacaoAreaAtuacao> getEspecializacoesParaSelecionar() {
        return especializacoesParaSelecionar;
    }

    public void setEspecializacoesParaSelecionar(final DataModel<EspecializacaoAreaAtuacao> especializacoesParaSelecionar) {
        this.especializacoesParaSelecionar = especializacoesParaSelecionar;
    }

    public AtuacaoPesquisador getAtuacaoSelecionada() {
        return atuacaoSelecionada;
    }

    public void setAtuacaoSelecionada(final AtuacaoPesquisador atuacaoSelecionada) {
        this.atuacaoSelecionada = atuacaoSelecionada;
    }

    public DataModel<GrandeAreaAtuacao> getTodasAsGrandeAreas() {
        return todasAsGrandeAreas;
    }

    public GrandeAreaAtuacaoService getGrandeAreaService() {
        return grandeAreaService;
    }

    public void setGrandeAreaService(final GrandeAreaAtuacaoService grandeAreaService) {
        this.grandeAreaService = grandeAreaService;
    }

    public void setTodasAsGrandeAreas(final DataModel<GrandeAreaAtuacao> todasAsGrandeAreas) {
        this.todasAsGrandeAreas = todasAsGrandeAreas;
    }

    // Linha De Pesquisa
    public LinhaDePesquisa getLinhaDePesquisaSelecionada() {
        return linhaDePesquisaSelecionada;
    }

    public void setLinhaDePesquisaSelecionada(final LinhaDePesquisa linhaDePesquisaSelecionada) {
        this.linhaDePesquisaSelecionada = linhaDePesquisaSelecionada;
    }

    public List<LinhaDePesquisa> getLinhasDePesquisa() {
        return linhasDePesquisa;
    }

    public void setLinhasDePesquisa(final List<LinhaDePesquisa> linhasDePesquisa) {
        this.linhasDePesquisa = linhasDePesquisa;
    }

    public LinhaDePesquisaService getLinhaDePesquisaService() {
        return linhaDePesquisaService;
    }

    public void setLinhaDePesquisaService(final LinhaDePesquisaService linhaDePesquisaService) {
        this.linhaDePesquisaService = linhaDePesquisaService;
    }

    public void adicionarLinhaDePesquisa() {

        if (linhaDePesquisaSelecionada != null) {

            pesquisador.getLinhasDePesquisa().add(linhaDePesquisaSelecionada);
        }
    }

    public void removerLinhaDePesquisa(final LinhaDePesquisa linhaPesquisa) {
        pesquisador.getLinhasDePesquisa().remove(linhaPesquisa);
    }

    public CitacaoBibliografica getCitacaoBibliografica() {
        return citacaoBibliografica;
    }

    public void setCitacaoBibliografica(final CitacaoBibliografica citacaoBibliografica) {
        this.citacaoBibliografica = citacaoBibliografica;
    }

    public List<CitacaoBibliografica> getCitacoesBibliograficas() {
        return citacoesBibliograficas;
    }

    public void setCitacoesBibliograficas(final List<CitacaoBibliografica> citacoesBibliograficas) {
        this.citacoesBibliograficas = citacoesBibliograficas;
    }

    public CitacaoBibliograficaService getCitacaoBibliograficaService() {
        return citacaoBibliograficaService;
    }

    public void setCitacaoBibliograficaService(final CitacaoBibliograficaService citacaoBibliograficaService) {
        this.citacaoBibliograficaService = citacaoBibliograficaService;
    }

    public ParticipacaoEvento getParticipacaoEvento() {
        return participacaoEvento;
    }

    public void setParticipacaoEvento(final ParticipacaoEvento participacaoEvento) {
        this.participacaoEvento = participacaoEvento;
    }

    public List<ParticipacaoEvento> getParticipacaoEventos() {
        return participacaoEventos;
    }

    public void setParticipacaoEventos(final List<ParticipacaoEvento> participacaoEventos) {
        this.participacaoEventos = participacaoEventos;
    }

    public ParticipacaoEventoService getParticipacaoEventoService() {
        return participacaoEventoService;
    }

    public void setParticipacaoEventoService(final ParticipacaoEventoService participacaoEventoService) {
        this.participacaoEventoService = participacaoEventoService;
    }

    public OrganizacaoEvento getOrganizacaoEvento() {
        return organizacaoEvento;
    }

    public void setOrganizacaoEvento(final OrganizacaoEvento organizacaoEvento) {
        this.organizacaoEvento = organizacaoEvento;
    }

    public List<OrganizacaoEvento> getOrganizacaoEventos() {
        return organizacaoEventos;
    }

    public void setOrganizacaoEventos(final List<OrganizacaoEvento> organizacaoEventos) {
        this.organizacaoEventos = organizacaoEventos;
    }

    public OrganizacaoEventoService getOrganizacaoEventoService() {
        return organizacaoEventoService;
    }

    public void setOrganizacaoEventoService(final OrganizacaoEventoService organizacaoEventoService) {
        this.organizacaoEventoService = organizacaoEventoService;
    }

    public List<ArtigoEmPeriodico> getListaArtigoEmPeriodico() {

        this.listaArtigoEmPeriodico = this.pesquisadorService.buscarArtigosEmPeriodicos(pesquisador.getIdUsuario());

        return listaArtigoEmPeriodico;
    }

    public void setListaArtigoEmPeriodico(List<ArtigoEmPeriodico> listaArtigoEmPeriodico) {
        this.listaArtigoEmPeriodico = listaArtigoEmPeriodico;
    }
}
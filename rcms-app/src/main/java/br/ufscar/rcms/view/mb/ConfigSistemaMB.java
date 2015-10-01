package br.ufscar.rcms.view.mb;

import static br.ufscar.rcms.commons.util.FileUtils.extractFileExtension;
import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.PartialViewContext;
import javax.faces.event.ValueChangeEvent;
import javax.persistence.Transient;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoIndice;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoSistema;
import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.modelo.entidades.ProjetoPesquisa;
import br.ufscar.rcms.modelo.entidades.TransientFile;
import br.ufscar.rcms.servico.ConfiguracaoService;
import br.ufscar.rcms.servico.ProducaoService;
import br.ufscar.rcms.servico.ProjetoPesquisaService;

@ViewScoped
@ManagedBean(name = "configSistemaMB")
public class ConfigSistemaMB extends AbstractMB {

    private static final long serialVersionUID = -8168079468992950249L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigSistemaMB.class);

    private static final String ESTILO_ADMIN_RCMS = "RCMS";

    @ManagedProperty("#{configuracaoService}")
    private ConfiguracaoService configuracaoService;

    @ManagedProperty("#{projetoPesquisaService}")
    private ProjetoPesquisaService projetoPesquisaService;

    @ManagedProperty("#{producaoService}")
    private ProducaoService producaoService;

    private String idioma;

    private String idiomaBanco;

    private Map<String, String> idiomas;

    private String estiloAdmin;
    private Map<String, String> estilosAdmin;

    private String temaPortal;
    private Map<String, String> temasPortal;

    private Part imagemLogo;

    @Transient
    private TransientFile foto = new TransientFile();

    private String nomeGrupo;

    private String descGrupo;

    private String logotipo;

    private Boolean importacaoLattesAutomcatica = false;

    protected Set<Configuracao> configuracoes;

    protected List<ConfiguracaoIndice> indicesProjeto;

    protected List<ConfiguracaoIndice> indicesProjetoCarousel;

    protected List<ConfiguracaoIndice> indicesProducao;

    private List<ProjetoPesquisa> projetosDePesquisa;
    private List<ProjetoPesquisa> projetosSelecionados;
    private ProjetoPesquisa projetoDePesquisaSelecionado;

    private List<ProjetoPesquisa> projetosDePesquisaCarousel;
    private List<ProjetoPesquisa> projetosDePesquisaCarouselSelecionados;
    private ProjetoPesquisa projetoDePesquisaCarouselSelecionado;

    private String blocoCarouselOuProjetos;

    private Producao producaoSelecionada;
    private List<Producao> producoesSelecionadas;

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
    }

    @Override
    protected void limparDados() {
    }

    @Override
    protected void carregarDados() {
        carregarCombos();
        carregarConfiguracoes();
    }

    private void carregarCombos() {
        carregarIdiomas();
        carregarEstilosAdmin();
        carregarTemasPortal();
        carregarProjetos();
        carregarProducoes();
        carregarIndices();
    }

    private void carregarConfiguracoes() {
        List<Configuracao> listaConfigs = getConfiguracaoService().buscarPorTipo(
                Configuracao.Tipo.getTiposConfigSistema());

        configuracoes = new HashSet<Configuracao>();

        if (listaConfigs != null && !listaConfigs.isEmpty()) {
            configuracoes.addAll(listaConfigs);
        }

        Configuracao configIdioma = getConfiguracao(Configuracao.Tipo.IDIOMA);
        if(isEmpty(idioma)){
	        idioma = isEmpty(configIdioma.getValue()) ? idioma : configIdioma.getValue();
        }

        idiomaBanco = isEmpty(configIdioma.getValue()) ? idiomaBanco : configIdioma.getValue();

        alterarIdioma();

        Configuracao configEstiloAdmin = getConfiguracao(Configuracao.Tipo.ESTILO_ADMIN);
        estiloAdmin = isEmpty(configEstiloAdmin.getValue()) ? estiloAdmin : configEstiloAdmin.getValue();

        Configuracao configEstiloPortal = getConfiguracao(Configuracao.Tipo.ESTILO_PORTAL);
        temaPortal = (configEstiloPortal == null || isEmpty(configEstiloPortal.getValue())) ? temaPortal
                : configEstiloPortal.getValue();

        Configuracao configAutoImport = getConfiguracao(Configuracao.Tipo.IMPORTACAO_LATTES_AUTOMATICA);
        importacaoLattesAutomcatica = isEmpty(configAutoImport.getValue()) ? importacaoLattesAutomcatica : Boolean
                .valueOf(configAutoImport.getValue());

        Configuracao configLogotipo = getConfiguracao(Configuracao.Tipo.LOGOTIPO);
        logotipo = isEmpty(configLogotipo.getValue()) ? logotipo : configLogotipo.getValue();

        Configuracao configNomeGrupo = getConfiguracao(Configuracao.Tipo.NOME_GRUPO);
        nomeGrupo = isEmpty(configNomeGrupo.getValue()) ? nomeGrupo : configNomeGrupo.getValue();

        Configuracao configDescGrupo = getConfiguracao(Configuracao.Tipo.DESCRICAO_GRUPO);
        descGrupo = isEmpty(configDescGrupo.getValue()) ? descGrupo : configDescGrupo.getValue();

        this.projetosDePesquisaCarouselSelecionados = new ArrayList<ProjetoPesquisa>();
        for (Configuracao configuracao : configuracaoService
                .buscarPorTipo(Configuracao.Tipo.INDICE_PROJETO_PESQUISA_CAROUSEL)) {
            this.projetosDePesquisaCarouselSelecionados.add(projetoPesquisaService
                    .buscar(((ConfiguracaoIndice) configuracao).getId()));
            indicesProjetoCarousel.add((ConfiguracaoIndice) configuracao);
        }

        this.projetosSelecionados = new ArrayList<ProjetoPesquisa>();
        for (Configuracao configuracao : configuracaoService.buscarPorTipo(Configuracao.Tipo.INDICE_PROJETO_PESQUISA)) {
            this.projetosSelecionados.add(projetoPesquisaService.buscar(((ConfiguracaoIndice) configuracao).getId()));
            indicesProjeto.add((ConfiguracaoIndice) configuracao);
        }

        this.producoesSelecionadas = new ArrayList<Producao>();
        for (Configuracao configuracao : configuracaoService.buscarPorTipo(Configuracao.Tipo.INDICE_PRODUCAO)) {
            this.producoesSelecionadas.add(producaoService.buscarPorId(((ConfiguracaoIndice) configuracao).getId()));
            indicesProducao.add((ConfiguracaoIndice) configuracao);
        }
    }

    public String salvar() {
        salvarConfiguracoes();

        keepMessagesOnRedirect();
        return PAINEL_CONTROLE;

    }

    private void salvarConfiguracoes() {

        try {
            Configuracao config;
            config = getConfiguracao(Configuracao.Tipo.IDIOMA);
            config.setValue(idiomaBanco);
            getConfiguracoes().add(config);
            config = getConfiguracao(Configuracao.Tipo.IMPORTACAO_LATTES_AUTOMATICA);
            config.setValue(importacaoLattesAutomcatica.toString());
            getConfiguracoes().add(config);
            config = getConfiguracao(Configuracao.Tipo.ESTILO_ADMIN);
            config.setValue(estiloAdmin);
            getConfiguracoes().add(config);
            config = getConfiguracao(Configuracao.Tipo.ESTILO_PORTAL);
            config.setValue(temaPortal);
            getConfiguracoes().add(config);
            config = getConfiguracao(Configuracao.Tipo.NOME_GRUPO);
            config.setValue(nomeGrupo);
            getConfiguracoes().add(config);
            config = getConfiguracao(Configuracao.Tipo.DESCRICAO_GRUPO);
            config.setValue(descGrupo);
            getConfiguracoes().add(config);

            if (!isEmpty(getImagemLogo())) {
                uploadFile();
                setLogotipo(getConfiguracaoService().salvarLogotipo(getFoto()));
                getConfiguracoes().add(config);
                config = getConfiguracao(Configuracao.Tipo.LOGOTIPO);
                config.setValue(logotipo);
                getConfiguracoes().add(config);
            }

            for (Configuracao conf : getConfiguracoes()) {
                getConfiguracaoService().saveOrUpdate(conf);
            }

            // Projetos para o carousel
            for (ConfiguracaoIndice confIndice : this.indicesProducao) {
            	Producao p = getProducaoByConfiguracao(confIndice.getId());
            	if(p == null){
            		this.configuracaoService.remover(confIndice);
            	}
            }

            for (ConfiguracaoIndice confIndice : this.indicesProjeto) {
            	ProjetoPesquisa p = getProjetoByConfiguracao(confIndice.getId());
            	if(p == null){
            		this.configuracaoService.remover(confIndice);
            	}
            }

            for (ConfiguracaoIndice confIndice : this.indicesProjetoCarousel) {
            	ProjetoPesquisa p = getProjetoCarouselByConfiguracao(confIndice.getId());
            	if(p == null){
            		this.configuracaoService.remover(confIndice);
            	}
            }

            for (ProjetoPesquisa projeto : this.projetosDePesquisaCarouselSelecionados) {
            	ConfiguracaoIndice configuracao = getConfiguracaoIndiceProjetoCarousel(projeto.getIdProjetoPesquisa());
                configuracao.setId(projeto.getIdProjetoPesquisa());
            	configuracao.setKey(Configuracao.Tipo.INDICE_PROJETO_PESQUISA_CAROUSEL);
                this.configuracaoService.saveOrUpdate(configuracao);
            }

            // Projetos da página inicial
            for (ProjetoPesquisa projeto : this.projetosSelecionados) {
                ConfiguracaoIndice configuracao = getConfiguracaoIndiceProjeto(projeto.getIdProjetoPesquisa());
                configuracao.setId(projeto.getIdProjetoPesquisa());
            	configuracao.setKey(Configuracao.Tipo.INDICE_PROJETO_PESQUISA);
                this.configuracaoService.saveOrUpdate(configuracao);
            }

            // Produções da página inicial
            for (Producao producao : this.producoesSelecionadas) {
            	ConfiguracaoIndice configuracao = getConfiguracaoIndiceProducao(producao.getIdProducao());
                configuracao.setId(producao.getIdProducao());
            	configuracao.setKey(Configuracao.Tipo.INDICE_PRODUCAO);
                this.configuracaoService.saveOrUpdate(configuracao);
            }

            limparDados();
            adicionarMensagemInfoByKey("configuracoes.salva.sucesso");

        } catch (final Exception exception) {
            adicionarMensagemErroByKey("erro.salvar.configuracoes");
            LOGGER.error(exception.getMessage(), exception);
        }
    }

    public boolean loadCustomScript() {
        return estiloAdmin != null && !estiloAdmin.equals(ESTILO_ADMIN_RCMS);
    }

    private void carregarIdiomas() {
    	if(isEmpty(getSession().getAttribute("idioma"))){
    		idioma = getViewRoot().getLocale().toString();
    	} else {
    		idioma = (String) getSession().getAttribute("idioma");
    	}
        idiomas = new HashMap<String, String>();
        idiomas.put(getMessage("ingles"), "en");
        idiomas.put(getMessage("portugues"), "pt_BR");
    }

    private void carregarEstilosAdmin() {
        estilosAdmin = new HashMap<String, String>();
        estilosAdmin.put(ESTILO_ADMIN_RCMS, ESTILO_ADMIN_RCMS);
    }

    private void carregarTemasPortal() {
        temaPortal = "portal";
        temasPortal = new HashMap<String, String>();
        temasPortal.put(getMessage("temas.portal.padrao"), "portal");
        temasPortal.put(getMessage("temas.portal.alegria"), "alegria");
        temasPortal.put(getMessage("temas.portal.confianca"), "confianca");
        temasPortal.put(getMessage("temas.portal.surpresa"), "surpresa");
        temasPortal.put(getMessage("temas.portal.raiva"), "raiva");
    }

    /*
     * private void carregarNomeGrupo() { if(getConfiguracao(Configuracao.Tipo.NOME_GRUPO) != null){
     * setNomeGrupo(getConfiguracao(Configuracao.Tipo.NOME_GRUPO).getValue()); } }
     *
     * private void carregarDescGrupo() { if(getConfiguracao(Configuracao.Tipo.DESCRICAO_GRUPO) != null){
     * setDescGrupo(getConfiguracao(Configuracao.Tipo.DESCRICAO_GRUPO).getValue()); } }
     */

    /*
     * private void carregarLogotipo() { if(getConfiguracao(Configuracao.Tipo.LOGOTIPO) != null){
     * setLogotipo(getConfiguracao(Configuracao.Tipo.LOGOTIPO).getValue()); } }
     */

    public void adicionarProjetoDePesquisaCarousel() {

        if (projetoDePesquisaCarouselSelecionado != null) {

            this.projetosDePesquisaCarouselSelecionados.add(projetoDePesquisaCarouselSelecionado);
        }
    }

    public void removerProjetoDePesquisaCarousel(final ProjetoPesquisa projetoDePesquisa) {

        this.projetosDePesquisaCarouselSelecionados.remove(projetoDePesquisa);
    }

    public void adicionarProjetoDePesquisa() {

        if (projetoDePesquisaSelecionado != null) {

            this.projetosSelecionados.add(projetoDePesquisaSelecionado);
        }
    }

    public void removerProjetoDePesquisa(final ProjetoPesquisa projetoDePesquisa) {

        this.projetosSelecionados.remove(projetoDePesquisa);
    }

    public List<Producao> completeProducao(final String query) {
        List<Producao> allProducoes = producaoService.buscarTodas();
        List<Producao> filteredProducoes = new ArrayList<Producao>();

        for (int i = 0; i < allProducoes.size(); i++) {
            Producao p = allProducoes.get(i);
            if (p.getTitulo().toLowerCase().startsWith(query.toLowerCase())) {
                filteredProducoes.add(p);
            }
        }

        return filteredProducoes;
    }

    public void adicionarProducao() {

        if (this.producaoSelecionada != null) {

            this.producoesSelecionadas.add(this.producaoSelecionada);

            this.producaoSelecionada = null;
        }
    }

    public void removerProducao(final Producao excluirProducao) {

        this.producoesSelecionadas.remove(excluirProducao);
    }

    private void carregarProjetos() {
        this.projetosDePesquisaCarousel = projetoPesquisaService.buscarTodos();
        this.projetosDePesquisa = projetoPesquisaService.buscarTodos();
    }

    private void carregarProducoes() {
        this.producoesSelecionadas = new ArrayList<Producao>();
    }

    private void carregarIndices() {
        this.indicesProducao = new ArrayList<ConfiguracaoIndice>();
        this.indicesProjeto = new ArrayList<ConfiguracaoIndice>();
        this.indicesProjetoCarousel = new ArrayList<ConfiguracaoIndice>();
    }

    public Configuracao getConfiguracao(final Configuracao.Tipo tipo) {
        return configuracoes.stream().filter(c -> c.getKey().equals(tipo)).findFirst()
                .orElse(new ConfiguracaoSistema(tipo));
    }

    public ConfiguracaoIndice getConfiguracaoIndiceProjeto(final Long idProjeto) {
        return indicesProjeto.stream().filter(c -> c.getId().equals(idProjeto)).findFirst()
                .orElse(new ConfiguracaoIndice());
    }

    public ProjetoPesquisa getProjetoByConfiguracao(final Long idConfiguracao) {
        return projetosSelecionados.stream().filter(c -> c.getIdProjetoPesquisa().equals(idConfiguracao)).findFirst()
                .orElse(null);
    }

    public Producao getProducaoByConfiguracao(final Long idConfiguracao) {
        return producoesSelecionadas.stream().filter(c -> c.getIdProducao().equals(idConfiguracao)).findFirst()
                .orElse(null);
    }

    public ProjetoPesquisa getProjetoCarouselByConfiguracao(final Long idConfiguracao) {
        return projetosDePesquisaCarouselSelecionados.stream().filter(c -> c.getIdProjetoPesquisa().equals(idConfiguracao)).findFirst()
                .orElse(null);
    }

    public ConfiguracaoIndice getConfiguracaoIndiceProducao(final Long idProducao) {
        return indicesProducao.stream().filter(c -> c.getId().equals(idProducao)).findFirst()
                .orElse(new ConfiguracaoIndice());
    }

    public ConfiguracaoIndice getConfiguracaoIndiceProjetoCarousel(final Long idProjeto) {
        return indicesProjetoCarousel.stream().filter(c -> c.getId().equals(idProjeto)).findFirst()
                .orElse(new ConfiguracaoIndice());
    }

    public void alterarIdioma() {
        final PartialViewContext partialViewContext = getPartialViewContext();
        partialViewContext.getRenderIds().addAll(partialViewContext.getExecuteIds());
        getViewRoot().setLocale(new Locale(idioma));
    }

    public void alterarIdioma(final ValueChangeEvent event) {
        final PartialViewContext partialViewContext = getPartialViewContext();
        partialViewContext.getRenderIds().addAll(partialViewContext.getExecuteIds());
        setIdioma((String) event.getNewValue());
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
        setIdioma((String) event.getNewValue());
        session.setAttribute("idioma", idioma);
        getViewRoot().setLocale(new Locale((String) event.getNewValue()));
    }

    public void uploadFile() {
        try {
            getFoto().setFile(IOUtils.toByteArray(imagemLogo.getInputStream()));
            getFoto().setFileExtension(extractFileExtension(imagemLogo.getSubmittedFileName()));
        } catch (final IOException ioException) {
            LOGGER.error("Erro ao realizar upload do logotipo", ioException);
            adicionarMensagemErroByKey("erro.enviar.imagem");
        }
    }

    @Override
    protected HttpSession getSession(){
    	return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(final String idioma) {
        this.idioma = idioma;
    }

    public String getIdiomaBanco() {
		return idiomaBanco;
	}

	public void setIdiomaBanco(final String idiomaBanco) {
		this.idiomaBanco = idiomaBanco;
	}

	public Map<String, String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(final Map<String, String> idiomas) {
        this.idiomas = idiomas;
    }

    public String getEstiloAdmin() {
        return estiloAdmin;
    }

    public void setEstiloAdmin(final String estiloAdmin) {
        this.estiloAdmin = estiloAdmin;
    }

    public Map<String, String> getEstilosAdmin() {
        return estilosAdmin;
    }

    public void setEstilosAdmin(final Map<String, String> estilosAdmin) {
        this.estilosAdmin = estilosAdmin;
    }

    public String getTemaPortal() {
        return temaPortal;
    }

    public void setTemaPortal(final String temaPortal) {
        this.temaPortal = temaPortal;
    }

    public Map<String, String> getTemasPortal() {
        return temasPortal;
    }

    public void setTemasPortal(final Map<String, String> temasPortal) {
        this.temasPortal = temasPortal;
    }

    public Part getImagemLogo() {
        return imagemLogo;
    }

    public void setImagemLogo(final Part imagemLogo) {
        this.imagemLogo = imagemLogo;
    }

    public TransientFile getFoto() {
        return foto;
    }

    public void setFoto(final TransientFile foto) {
        this.foto = foto;
    }

    public Boolean getImportacaoLattesAutomcatica() {
        return importacaoLattesAutomcatica;
    }

    public void setImportacaoLattesAutomcatica(final Boolean importacaoLattesAutomcatica) {
        this.importacaoLattesAutomcatica = importacaoLattesAutomcatica;
    }

    public ConfiguracaoService getConfiguracaoService() {
        return configuracaoService;
    }

    public void setConfiguracaoService(final ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }

    public ProjetoPesquisaService getProjetoPesquisaService() {
        return projetoPesquisaService;
    }

    public void setProjetoPesquisaService(final ProjetoPesquisaService projetoPesquisaService) {
        this.projetoPesquisaService = projetoPesquisaService;
    }

    public ProducaoService getProducaoService() {
        return producaoService;
    }

    public void setProducaoService(final ProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    public Set<Configuracao> getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(final Set<Configuracao> configuracoes) {
        this.configuracoes = configuracoes;
    }

    public List<ConfiguracaoIndice> getIndicesProjeto() {
		return indicesProjeto;
	}

	public void setIndicesProjeto(final List<ConfiguracaoIndice> indicesProjeto) {
		this.indicesProjeto = indicesProjeto;
	}

	public List<ConfiguracaoIndice> getIndicesProjetoCarousel() {
		return indicesProjetoCarousel;
	}

	public void setIndicesProjetoCarousel(final List<ConfiguracaoIndice> indicesProjetoCarousel) {
		this.indicesProjetoCarousel = indicesProjetoCarousel;
	}

	public List<ConfiguracaoIndice> getIndicesProducao() {
		return indicesProducao;
	}

	public void setIndicesProducao(final List<ConfiguracaoIndice> indicesProducao) {
		this.indicesProducao = indicesProducao;
	}

	public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(final String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public String getDescGrupo() {
        return descGrupo;
    }

    public void setDescGrupo(final String descGrupo) {
        this.descGrupo = descGrupo;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(final String logotipo) {
        this.logotipo = logotipo;
    }

    public List<ProjetoPesquisa> getProjetosDePesquisa() {
        return projetosDePesquisa;
    }

    public void setProjetosDePesquisa(final List<ProjetoPesquisa> projetosDePesquisa) {
        this.projetosDePesquisa = projetosDePesquisa;
    }

    public List<ProjetoPesquisa> getProjetosSelecionados() {
        return projetosSelecionados;
    }

    public void setProjetosSelecionados(final List<ProjetoPesquisa> projetosSelecionados) {
        this.projetosSelecionados = projetosSelecionados;
    }

    public ProjetoPesquisa getProjetoDePesquisaSelecionado() {
        return projetoDePesquisaSelecionado;
    }

    public void setProjetoDePesquisaSelecionado(final ProjetoPesquisa projetoDePesquisaSelecionado) {
        this.projetoDePesquisaSelecionado = projetoDePesquisaSelecionado;
    }

    public Producao getProducaoSelecionada() {
        return producaoSelecionada;
    }

    public void setProducaoSelecionada(final Producao producaoSelecionada) {
        this.producaoSelecionada = producaoSelecionada;
    }

    public List<Producao> getProducoesSelecionadas() {
        return producoesSelecionadas;
    }

    public void setProducoesSelecionadas(final List<Producao> producoesSelecionadas) {
        this.producoesSelecionadas = producoesSelecionadas;
    }

    public List<ProjetoPesquisa> getProjetosDePesquisaCarouselSelecionados() {
        return projetosDePesquisaCarouselSelecionados;
    }

    public void setProjetosDePesquisaCarouselSelecionados(final List<ProjetoPesquisa> projetosDePesquisaCarouselSelecionados) {
        this.projetosDePesquisaCarouselSelecionados = projetosDePesquisaCarouselSelecionados;
    }

    public String getBlocoCarouselOuProjetos() {
        return blocoCarouselOuProjetos;
    }

    public void setBlocoCarouselOuProjetos(final String blocoCarouselOuProjetos) {
        this.blocoCarouselOuProjetos = blocoCarouselOuProjetos;
    }

    public ProjetoPesquisa getProjetoDePesquisaCarouselSelecionado() {
        return projetoDePesquisaCarouselSelecionado;
    }

    public void setProjetoDePesquisaCarouselSelecionado(final ProjetoPesquisa projetoDePesquisaCarouselSelecionado) {
        this.projetoDePesquisaCarouselSelecionado = projetoDePesquisaCarouselSelecionado;
    }

    public List<ProjetoPesquisa> getProjetosDePesquisaCarousel() {
        return projetosDePesquisaCarousel;
    }

    public void setProjetosDePesquisaCarousel(final List<ProjetoPesquisa> projetosDePesquisaCarousel) {
        this.projetosDePesquisaCarousel = projetosDePesquisaCarousel;
    }
}
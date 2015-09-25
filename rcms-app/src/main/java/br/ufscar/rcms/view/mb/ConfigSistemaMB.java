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
import javax.faces.context.PartialViewContext;
import javax.persistence.Transient;
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

    private List<ProjetoPesquisa> projetosDePesquisa;
    private List<ProjetoPesquisa> projetosSelecionados;
    private ProjetoPesquisa projetoDePesquisaSelecionado;

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
    }

    private void carregarConfiguracoes() {
        List<Configuracao> listaConfigs = getConfiguracaoService().buscarPorTipo(
                Configuracao.Tipo.getTiposConfigSistema());

        configuracoes = new HashSet<Configuracao>();

        if (listaConfigs != null && !listaConfigs.isEmpty()) {
            configuracoes.addAll(listaConfigs);
        }

        Configuracao configIdioma = getConfiguracao(Configuracao.Tipo.IDIOMA);
        idioma = isEmpty(configIdioma.getValue()) ? idioma : configIdioma.getValue();

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

        this.projetosSelecionados = new ArrayList<ProjetoPesquisa>();
        for (Configuracao configuracao : configuracaoService.buscarPorTipo(Configuracao.Tipo.INDICE_PROJETO_PESQUISA)) {
            this.projetosSelecionados.add(projetoPesquisaService.buscar(((ConfiguracaoIndice) configuracao).getId()));
        }

        this.producoesSelecionadas = new ArrayList<Producao>();
        for (Configuracao configuracao : configuracaoService.buscarPorTipo(Configuracao.Tipo.INDICE_PRODUCAO)) {
            this.producoesSelecionadas.add(producaoService.buscarPorId(((ConfiguracaoIndice) configuracao).getId()));
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
            config.setValue(idioma);
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

            for (ProjetoPesquisa projeto : this.projetosSelecionados) {
                ConfiguracaoIndice configuracao = new ConfiguracaoIndice();
                configuracao.setId(projeto.getIdProjetoPesquisa());
                configuracao.setKey(Configuracao.Tipo.INDICE_PROJETO_PESQUISA);
                this.configuracaoService.saveOrUpdate(configuracao);
            }

            for (Producao producao : this.producoesSelecionadas) {
                ConfiguracaoIndice configuracao = new ConfiguracaoIndice();
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
        idioma = getViewRoot().getLocale().toString();
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

    public void adicionarProjetoDePesquisa() {

        if (projetoDePesquisaSelecionado != null) {

            this.projetosSelecionados.add(projetoDePesquisaSelecionado);
        }
    }

    public void removerProjetoDePesquisa(final ProjetoPesquisa projetoDePesquisa) {

        this.projetosSelecionados.remove(projetoDePesquisa);
    }

    public List<Producao> completeProducao(String query) {
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
        this.projetosDePesquisa = projetoPesquisaService.buscarTodos();
    }

    private void carregarProducoes() {
        this.producoesSelecionadas = new ArrayList<Producao>();
    }

    public Configuracao getConfiguracao(final Configuracao.Tipo tipo) {
        return configuracoes.stream().filter(c -> c.getKey().equals(tipo)).findFirst()
                .orElse(new ConfiguracaoSistema(tipo));
    }

    public void alterarIdioma() {
        final PartialViewContext partialViewContext = getPartialViewContext();
        partialViewContext.getRenderIds().addAll(partialViewContext.getExecuteIds());
        getViewRoot().setLocale(new Locale(idioma));
    }

    public void uploadFile() {
        try {
            getFoto().setFile(IOUtils.toByteArray(imagemLogo.getInputStream()));
            getFoto().setFileExtension(extractFileExtension(imagemLogo.getSubmittedFileName()));
        } catch (final IOException ioException) {
            LOGGER.error(String.format("Erro ao realizar upload do logotipo", ioException));
            adicionarMensagemErroByKey("erro.enviar.imagem");
        }
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(final String idioma) {
        this.idioma = idioma;
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

    public void setImagemLogo(Part imagemLogo) {
        this.imagemLogo = imagemLogo;
    }

    public TransientFile getFoto() {
        return foto;
    }

    public void setFoto(TransientFile foto) {
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

    public void setConfiguracaoService(ConfiguracaoService configuracaoService) {
        this.configuracaoService = configuracaoService;
    }

    public ProjetoPesquisaService getProjetoPesquisaService() {
        return projetoPesquisaService;
    }

    public void setProjetoPesquisaService(ProjetoPesquisaService projetoPesquisaService) {
        this.projetoPesquisaService = projetoPesquisaService;
    }

    public ProducaoService getProducaoService() {
        return producaoService;
    }

    public void setProducaoService(ProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    public Set<Configuracao> getConfiguracoes() {
        return configuracoes;
    }

    public void setConfiguracoes(Set<Configuracao> configuracoes) {
        this.configuracoes = configuracoes;
    }

    public String getNomeGrupo() {
        return nomeGrupo;
    }

    public void setNomeGrupo(String nomeGrupo) {
        this.nomeGrupo = nomeGrupo;
    }

    public String getDescGrupo() {
        return descGrupo;
    }

    public void setDescGrupo(String descGrupo) {
        this.descGrupo = descGrupo;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }

    public List<ProjetoPesquisa> getProjetosDePesquisa() {
        return projetosDePesquisa;
    }

    public void setProjetosDePesquisa(List<ProjetoPesquisa> projetosDePesquisa) {
        this.projetosDePesquisa = projetosDePesquisa;
    }

    public List<ProjetoPesquisa> getProjetosSelecionados() {
        return projetosSelecionados;
    }

    public void setProjetosSelecionados(List<ProjetoPesquisa> projetosSelecionados) {
        this.projetosSelecionados = projetosSelecionados;
    }

    public ProjetoPesquisa getProjetoDePesquisaSelecionado() {
        return projetoDePesquisaSelecionado;
    }

    public void setProjetoDePesquisaSelecionado(ProjetoPesquisa projetoDePesquisaSelecionado) {
        this.projetoDePesquisaSelecionado = projetoDePesquisaSelecionado;
    }

    public Producao getProducaoSelecionada() {
        return producaoSelecionada;
    }

    public void setProducaoSelecionada(Producao producaoSelecionada) {
        this.producaoSelecionada = producaoSelecionada;
    }

    public List<Producao> getProducoesSelecionadas() {
        return producoesSelecionadas;
    }

    public void setProducoesSelecionadas(List<Producao> producoesSelecionadas) {
        this.producoesSelecionadas = producoesSelecionadas;
    }
}
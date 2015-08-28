package br.ufscar.rcms.view.mb;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.PartialViewContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoIndice;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoSistema;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.modelo.entidades.ProjetoPesquisa;
import br.ufscar.rcms.servico.ConfiguracaoService;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.ProducaoService;
import br.ufscar.rcms.servico.ProjetoPesquisaService;

@ViewScoped
@ManagedBean(name = "configMB")
public class ConfigMB extends AbstractMB {

    private static final long serialVersionUID = -8168079468992950249L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigMB.class);

    private static final String ESTILO_ADMIN_RCMS = "RCMS";

    @ManagedProperty("#{pesquisadorService}")
    private PesquisadorService pesquisadorService;

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

    private Boolean importacaoLattesAutomcatica = false;

    private String usuario;
    private Pesquisador pesquisador;

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
    protected void limparDados() {}

    @Override
    protected void carregarDados() {
        usuario = getPrincipal().getUsername();
        pesquisador = pesquisadorService.buscarPorLogin(usuario);

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
        if (!isEmpty(pesquisador)) {
            Configuracao configIdioma = pesquisador.getConfiguracao(Configuracao.Tipo.IDIOMA);
            idioma = isEmpty(configIdioma.getValue()) ? idioma : configIdioma.getValue();

            Configuracao configEstiloAdmin = pesquisador.getConfiguracao(Configuracao.Tipo.ESTILO_ADMIN);
            estiloAdmin = isEmpty(configEstiloAdmin.getValue()) ? estiloAdmin : configEstiloAdmin.getValue();

            Configuracao configAutoImport = pesquisador.getConfiguracao(Configuracao.Tipo.IMPORTACAO_LATTES_AUTOMATICA);
            importacaoLattesAutomcatica = isEmpty(configAutoImport.getValue()) ? importacaoLattesAutomcatica : Boolean
                    .valueOf(configAutoImport.getValue());

            alterarIdioma();
        }

        ConfiguracaoSistema configEstiloPortal = configuracaoService.buscarPorKey(Configuracao.Tipo.ESTILO_PORTAL);
        temaPortal = (configEstiloPortal == null || isEmpty(configEstiloPortal.getValue())) ? temaPortal
                : configEstiloPortal.getValue();

        this.projetosSelecionados = new ArrayList<ProjetoPesquisa>();
        for (Configuracao configuracao : configuracaoService.buscarPorTipo(Configuracao.Tipo.INDICE_PROJETO_PESQUISA)) {
            this.projetosSelecionados.add(projetoPesquisaService.buscar(((ConfiguracaoIndice) configuracao).getId()));
        }

        this.producoesSelecionadas = new ArrayList<Producao>();
        for (Configuracao configuracao : configuracaoService.buscarPorTipo(Configuracao.Tipo.INDICE_PRODUCAO)) {
            this.producoesSelecionadas.add(producaoService.buscarPorId(((ConfiguracaoIndice) configuracao).getId()));
        }
    }

    private void carregarProjetos() {
        this.projetosDePesquisa = projetoPesquisaService.buscarTodos();
    }

    private void carregarProducoes() {
        this.producoesSelecionadas = new ArrayList<Producao>();
    }

    public void alterarIdioma() {
        final PartialViewContext partialViewContext = getPartialViewContext();
        partialViewContext.getRenderIds().addAll(partialViewContext.getExecuteIds());
        getViewRoot().setLocale(new Locale(idioma));
    }

    public String salvar() {

        if (isEmpty(pesquisador)) {
            adicionarMensagemAlertaByKey("usuario.nao.cadastrado", usuario);
        } else {
            salvarConfiguracoes(pesquisador);
        }

        keepMessagesOnRedirect();
        return PAINEL_CONTROLE;
    }

    private void salvarConfiguracoes(final Pesquisador pesquisador) {

        try {

            pesquisador.getConfiguracao(Configuracao.Tipo.IMPORTACAO_LATTES_AUTOMATICA).setValue(importacaoLattesAutomcatica.toString());
            pesquisador.getConfiguracao(Configuracao.Tipo.ESTILO_ADMIN).setValue(estiloAdmin);
            // pesquisador.getConfiguracao(Configuracao.Tipo.ESTILO_PORTAL).setValue(temaPortal);
            pesquisador.getConfiguracao(Configuracao.Tipo.IDIOMA).setValue(idioma);

            pesquisadorService.saveOrUpdate(pesquisador);

            ConfiguracaoSistema configuracaoTema = configuracaoService.buscarPorKey(Configuracao.Tipo.ESTILO_PORTAL);
            configuracaoTema.setValue(temaPortal);
            this.configuracaoService.saveOrUpdate(configuracaoTema);

            for (Configuracao configuracao : configuracaoService
                    .buscarPorTipo(Configuracao.Tipo.INDICE_PROJETO_PESQUISA)) {
                this.configuracaoService.remover(configuracao);
            }

            for (Configuracao configuracao : configuracaoService.buscarPorTipo(Configuracao.Tipo.INDICE_PRODUCAO)) {
                this.configuracaoService.remover(configuracao);
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

    public void loadUser() {
        if (isEmpty(pesquisador)) {
            inicializar();
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

    public PesquisadorService getPesquisadorService() {
        return pesquisadorService;
    }

    public void setPesquisadorService(final PesquisadorService pesquisadorService) {
        this.pesquisadorService = pesquisadorService;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(final String usuario) {
        this.usuario = usuario;
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

    public List<Producao> getProducoesSelecionadas() {
        return producoesSelecionadas;
    }

    public void setProducoesSelecionadas(List<Producao> producoesSelecionadas) {
        this.producoesSelecionadas = producoesSelecionadas;
    }

    public ProducaoService getProducaoService() {
        return producaoService;
    }

    public void setProducaoService(ProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    public ProjetoPesquisaService getProjetoPesquisaService() {
        return projetoPesquisaService;
    }

    public void setProjetoPesquisaService(ProjetoPesquisaService projetoPesquisaService) {
        this.projetoPesquisaService = projetoPesquisaService;
    }

    public List<ProjetoPesquisa> getProjetosSelecionados() {
        return projetosSelecionados;
    }

    public void setProjetosSelecionados(List<ProjetoPesquisa> projetosSelecionados) {
        this.projetosSelecionados = projetosSelecionados;
    }

    public List<ProjetoPesquisa> getProjetosDePesquisa() {
        return projetosDePesquisa;
    }

    public void setProjetosDePesquisa(List<ProjetoPesquisa> projetosDePesquisa) {
        this.projetosDePesquisa = projetosDePesquisa;
    }

    public ProjetoPesquisa getProjetoDePesquisaSelecionado() {
        return projetoDePesquisaSelecionado;
    }

    public void setProjetoDePesquisaSelecionado(final ProjetoPesquisa projetoDePesquisaSelecionado) {
        this.projetoDePesquisaSelecionado = projetoDePesquisaSelecionado;
    }

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

    public Producao getProducaoSelecionada() {
        return producaoSelecionada;
    }

    public void setProducaoSelecionada(Producao producaoSelecionada) {
        this.producaoSelecionada = producaoSelecionada;
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
}
package br.ufscar.rcms.view.mb;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.PartialViewContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;

@SessionScoped
@ManagedBean(name = "configMB")
public class ConfigMB extends AbstractMB {

    private static final long serialVersionUID = -8168079468992950249L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigMB.class);

    private static final String ESTILO_ADMIN_RCMS = "RCMS";

    @ManagedProperty("#{pesquisadorService}")
    private PesquisadorService pesquisadorService;

    private String idioma = getExternalContext().getRequestLocale().toString();
    private Map<String, String> idiomas;

    private String estiloAdmin;
    private Map<String, String> estilosAdmin;

    private String temaPortal;
    private Map<String, String> temasPortal;

    private String usuario;
    private Pesquisador pesquisador;

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
    }

    private void carregarConfiguracoes() {
        if (!isEmpty(pesquisador)) {
            Configuracao configIdioma = pesquisador.getConfiguracao(Configuracao.Tipos.IDIOMA);
            idioma = isEmpty(configIdioma.getValue()) ? idioma : configIdioma.getValue();

            Configuracao configEstiloAdmin = pesquisador.getConfiguracao(Configuracao.Tipos.ESTILO_ADMIN);
            estiloAdmin = isEmpty(configEstiloAdmin.getValue()) ? estiloAdmin : configEstiloAdmin.getValue();

            Configuracao configEstiloPortal = pesquisador.getConfiguracao(Configuracao.Tipos.ESTILO_PORTAL);
            temaPortal = isEmpty(configEstiloPortal.getValue()) ? temaPortal : configEstiloPortal.getValue();

            alterarIdioma();
        }
    }

    public void alterarIdioma() {
        final PartialViewContext partialViewContext = getPartialViewContext();
        partialViewContext.getRenderIds().addAll(partialViewContext.getExecuteIds());
        getViewRoot().setLocale(new Locale(idioma));
    }

    public void alterarEstilo() {
        noCacheRefresh();
    }

    public void alterarTemaPortal() {
        noCacheRefresh();
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

            pesquisador.getConfiguracao(Configuracao.Tipos.ESTILO_ADMIN).setValue(estiloAdmin);
            pesquisador.getConfiguracao(Configuracao.Tipos.ESTILO_PORTAL).setValue(temaPortal);
            pesquisador.getConfiguracao(Configuracao.Tipos.IDIOMA).setValue(idioma);

            // pesquisador.getConfiguracao(Configuracao.Tipos.IMPORTACAO_LATTES_AUTOMATICA).setValue(estiloAdmin);

            pesquisadorService.saveOrUpdate(pesquisador);

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

        /* TESTS */estilosAdmin.put("(TEST)", "estilo-admin-custom");
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
}
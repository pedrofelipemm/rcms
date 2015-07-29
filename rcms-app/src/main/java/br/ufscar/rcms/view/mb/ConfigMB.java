package br.ufscar.rcms.view.mb;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.PartialViewContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SessionScoped
@ManagedBean(name = "configMB")
public class ConfigMB extends AbstractMB{

    private static final long serialVersionUID = -8168079468992950249L;

    private static final Logger LOGGER = LoggerFactory.getLogger(ConfigMB.class);

    private static final String ESTILO_ADMIN_RCMS = "RCMS";

    private String idioma;
    private Map<String, String> idiomas;

    private String estiloAdmin;
    private Map<String, String> estilosAdmin;

    private String temaPortal;
    private Map<String, String> temasPortal;

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
    }

    @Override
    protected void limparDados() {}

    @Override
    protected void carregarDados() {
        carregarIdiomas();
        carregarEstilosAdmin();
        carregarTemasPortal();
        // TODO PEDRO CARREGAR CONFIG DO BANCO
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

        // TODO PEDRO - SAVE CONFIG TABLE - AGUARDANDO IMPLEMENTAÇÃO DE SPRING SECURITY

        limparDados();
        adicionarMensagemInfoByKey("configuracoes.salva.sucesso");
        keepMessagesOnRedirect();
        return PAINEL_CONTROLE;
    }

    public boolean loadCustomScript() {
        return estiloAdmin != null && !estiloAdmin.equals(ESTILO_ADMIN_RCMS);
    }

    private void carregarIdiomas() {
        // TODO PEDRO LANGUANGUES PROPS
        idioma = getViewRoot().getLocale().toString();
        idiomas = new HashMap<String, String>();
        idiomas.put(getMessage("ingles"), "en");
        idiomas.put(getMessage("portugues"), "pt_BR");
    }

    private void carregarEstilosAdmin() {
        estilosAdmin = new HashMap<String, String>();
        estilosAdmin.put(ESTILO_ADMIN_RCMS, ESTILO_ADMIN_RCMS);

        /* TESTS */estilosAdmin.put("(TEST) Green Style", "estilo-admin-custom");
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

    public void setTemaPortal(String temaPortal) {
        this.temaPortal = temaPortal;
    }


    public Map<String, String> getTemasPortal() {
        return temasPortal;
    }

    public void setTemasPortal(Map<String, String> temasPortal) {
        this.temasPortal = temasPortal;
    }
}
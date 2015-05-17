package br.ufscar.rcms.view.mb;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.PartialViewContext;

@ManagedBean(name = "configMB", eager = true)
@SessionScoped
public class ConfigMB extends AbstractMB{

    private static final long serialVersionUID = -8168079468992950249L;

    private static final String ESTILO_RCMS = "RCMS";

    private String idioma;
    private Map<String, String> idiomas;

    private String tema;
    private Map<String, String> temas;

    private String estilo;
    private Map<String, String> estilos;

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
    };

    @Override
    protected void limparDados() {}

    @Override
    protected void carregarDados() {
        carregarIdiomas();
        carregarTemas();
        carregarEstilos();
        // TODO PEDRO CARREGAR CONFIG DO BANCO
    }

    public void alterarIdioma() {
        PartialViewContext partialViewContext = getPartialViewContext();
        partialViewContext.getRenderIds().addAll(partialViewContext.getExecuteIds());
        getViewRoot().setLocale(new Locale(idioma));
    }

    public void alterarTema() {}

    public void alterarEstilo() {
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
        return estilo != null && !estilo.equals(ESTILO_RCMS);
    }

    private void carregarIdiomas() {
        // TODO PEDRO LANGUANGUES PROPS
        idioma = getViewRoot().getLocale().toString();
        idiomas = new HashMap<String, String>();
        idiomas.put(getMessage("ingles"), "en");
        idiomas.put(getMessage("portugues"), "pt_BR");
    }

    private void carregarTemas() {
        tema = "bootstrap";
        temas = new HashMap<String, String>();
        temas.put(getMessage("primefaces"), "none");
        temas.put(getMessage("rcms"), "bootstreap");
        temas.put("aristo", "aristo");
    }

    private void carregarEstilos() {
        estilos = new HashMap<String, String>();
        estilos.put(ESTILO_RCMS, ESTILO_RCMS);

        /* TESTS */estilos.put("(TEST) Green Style", "estilo-admin-custom");
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public Map<String, String> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(Map<String, String> idiomas) {
        this.idiomas = idiomas;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Map<String, String> getTemas() {
        return temas;
    }

    public void setTemas(Map<String, String> temas) {
        this.temas = temas;
    }

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public Map<String, String> getEstilos() {
        return estilos;
    }

    public void setEstilos(Map<String, String> estilos) {
        this.estilos = estilos;
    }
}
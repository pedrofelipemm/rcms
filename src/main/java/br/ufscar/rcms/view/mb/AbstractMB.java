package br.ufscar.rcms.view.mb;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ManagedBean
@SuppressWarnings("serial")
public abstract class AbstractMB implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMB.class);

    private static final String BUNDLE_NAME = "bundle";

    // Pesquisador
    public static final String CADASTRO_PESQUISADOR = "cadastroPesquisador";

    public static final String CONSULTA_PESQUISADORES = "consultaPesquisadores";

    public static final String EXIBE_PESQUISADOR = "pesquisador";

    public static final String FLASH_KEY_PESQUISADOR = "pesquisador";

    // Idioma
    public static final String CADASTRO_IDIOMAS = "cadastroIdiomas";

    public static final String CONSULTA_IDIOMAS = "consultaIdiomas";

    public static final String FLASH_KEY_IDIOMA = "cadastroIdioma";

    // Área de Atuação
    public static final String FLASH_KEY_AREA_ATUACAO = "area";

    public static final String FLASH_KEY_GRANDE_AREA_ATUACAO = "grandearea";

    public static final String CADASTRO_GRANDE_AREA = "cadastroGrandeAreaAtuacao";

    public static final String CONSULTA_GRANDE_AREA = "consultaGrandeAreaAtuacao";

    public static final String CADASTRO_AREA_ATUACAO = "cadastroAreaAtuacao";

    public static final String CONSULTA_AREA_ATUACAO = "consultaAreaAtuacao";

    public static final String FLASH_KEY_SUBAREA_ATUACAO = "subarea";

    public static final String CADASTRO_SUBAREA_ATUACAO = "cadastroSubAreaAtuacao";

    public static final String CONSULTA_SUBAREA_ATUACAO = "constultaSubAreaAtuacao";

    // Sobre O Grupo
    public static final String CADASTRO_SOBRE_O_GRUPO = "cadastroSobreOGrupo";

    public static final String CONSULTA_SOBRE_O_GRUPO = "consultaSobreOGrupo";

    public static final String EXIBE_SOBRE_O_GRUPO = "sobreOGrupo";

    public static final String FLASH_KEY_SOBRE_O_GRUPO = "sobreOGrupo";

    // Linhas de Pesquisa
    public static final String CADASTRO_LINHA_PESQUISA = "cadastroLinhaDePesquisa";

    public static final String CONSULTA_LINHAS_PESQUISA = "consultaLinhaDePesquisa";

    public static final String FLASH_KEY_LINHA_PESQUISA = "linhaDePesquisa";

    public Map<String, Boolean> getTiposUsuario() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put(getMessage("pesquisador"), false);
        map.put(getMessage("administrador"), true);
        return map;
    }

    // TODO PEDRO
    // public abstract void inicializar();
    // protected abstract void limparDados();
    // protected abstract void carregarDados();

    public FacesContext getCurrentInstance() {
        return FacesContext.getCurrentInstance();
    }

    public UIViewRoot getViewRoot() {
        return getCurrentInstance().getViewRoot();
    }

    public ExternalContext getExternalContext() {
        return getCurrentInstance().getExternalContext();
    }

    private Flash getFlash() {
        return getExternalContext().getFlash();
    }

    public void setFlashObject(String key, Object value) {

        if (key == null || value == null) {
            throw new IllegalArgumentException("key.value.nao.nulo");
        }

        if (getFlash() == null) {
            // TODO i18n
            LOGGER.error("Internal Framework Problem");
            return;
        }
        getFlash().put(key, value);
    }

    public Object getFlashObject(String key) {

        if (getFlash() == null) {
            // TODO i18n
            LOGGER.error("Internal Framework Problem");
            return null;
        }

        return getFlash().get(key);
    }

    public HttpServletRequest getRequest() {

        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public HttpServletResponse getResponse() {

        return (HttpServletResponse) getExternalContext().getResponse();
    }

    public ResourceBundle getResourceBundle() {

        return ResourceBundle.getBundle(BUNDLE_NAME, getViewRoot().getLocale());
    }

    public String getMessage(String key) {

        if (key == null) {
            throw new IllegalArgumentException("key.nao.nulo");
        }

        return getResourceBundle().getString(key);
    }

    public String getMessage(String key, String... parametros) {

        String mensagem = getMessage(key);
        if (parametros != null) {
            mensagem = MessageFormat.format(mensagem, (Object[]) parametros);
        }

        return mensagem;
    }

    public void adicionarMensagemInfo(String texto) {
        adicionarMensagem(texto, FacesMessage.SEVERITY_INFO);
    }

    public void adicionarMensagemInfoByKey(String key, String... parameters) {
        adicionarMensagemByKey(FacesMessage.SEVERITY_INFO, key, parameters);
    }

    public void adicionarMensagemErro(String texto) {
        adicionarMensagem(texto, FacesMessage.SEVERITY_ERROR);
    }

    public void adicionarMensagemErroByKey(String key, String... parameters) {
        adicionarMensagemByKey(FacesMessage.SEVERITY_ERROR, key, parameters);
    }

    public void adicionarMensagemAlerta(String texto) {
        adicionarMensagem(texto, FacesMessage.SEVERITY_WARN);
    }

    public void adicionarMensagemAlertaByKey(String key, String... parameters) {
        adicionarMensagemByKey(FacesMessage.SEVERITY_WARN, key, parameters);
    }

    private void adicionarMensagem(String texto, Severity severity) {
        getCurrentInstance().addMessage(null, new FacesMessage(severity, texto, null));
    }
    private void adicionarMensagemByKey(Severity severity, String key, String... parameters) {
        getCurrentInstance().addMessage(null, new FacesMessage(severity, getMessage(key, parameters), null));
    }

    public void keepMessagesOnRedirect() {
        getFlash().setKeepMessages(true);
    }
}
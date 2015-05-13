package br.ufscar.rcms.view.mb;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.context.PartialViewContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufscar.rcms.modelo.entidades.Entidade;

@ManagedBean
@SuppressWarnings("serial")
public abstract class AbstractMB implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMB.class);
    private static final String BUNDLE_NAME = "bundle";
    private static final String EMPTY_STRING = "";

    // Pesquisador
    public static final String CADASTRO_PESQUISADOR = "cadastroPesquisador";
    public static final String CONSULTA_PESQUISADORES = "consultaPesquisadores";
    public static final String EXIBE_PESQUISADOR = "pesquisador";
    public static final String FLASH_KEY_PESQUISADOR = "pesquisador";
    public static final String FLASH_KEY_FOTO_PESQUISADOR = "fotoPesquisador";

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

    // Painel de Controle
    public static final String PAINEL_CONTROLE = "painelControle";

    public Map<String, Boolean> getTiposUsuario() {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        map.put(getMessage("pesquisador"), false);
        map.put(getMessage("administrador"), true);
        return map;
    }

    // TODO PEDRO
    // @PostConstruct
    // public void inicializar() {
    // limparDados();
    // carregarDados();
    // };

    protected abstract void limparDados();

    protected abstract void carregarDados();

    protected FacesContext getCurrentInstance() {
        return FacesContext.getCurrentInstance();
    }

    protected PartialViewContext getPartialViewContext() {
        return getCurrentInstance().getPartialViewContext();
    }

    protected ServletContext getContext() {
        return (ServletContext) getExternalContext().getContext();
    }

    protected UIViewRoot getViewRoot() {
        return getCurrentInstance().getViewRoot();
    }

    protected ExternalContext getExternalContext() {
        return getCurrentInstance().getExternalContext();
    }

    protected HttpSession getSession() {
        return (HttpSession) getExternalContext().getSession(false);
    }

    protected String getSessionId() {
        return getSession().getId();
    }

    protected String getRealPath(String path) {
        return getContext().getRealPath(path);
    }

    protected String getRealPath() {
        return getRealPath(EMPTY_STRING);
    }

    protected Flash getFlash() {
        return getExternalContext().getFlash();
    }

    protected void setFlashObject(String key, Object value) {

        if (key == null || value == null) {
            throw new IllegalArgumentException(getMessage("key.value.nao.nulo"));
        }

        Flash flash = getFlash();
        if (flash == null) {
            LOGGER.error(getMessage("flash.null"));
            return;
        }

        flash.put(key, value);
    }

    protected Object getFlashObject(String key) {

        Flash flash = getFlash();
        if (flash == null) {
            LOGGER.error(getMessage("flash.null"));
            return null;
        }

        return flash.get(key);
    }

    protected HttpServletRequest getRequest() {

        return (HttpServletRequest) getExternalContext().getRequest();
    }

    protected HttpServletResponse getResponse() {

        return (HttpServletResponse) getExternalContext().getResponse();
    }

    protected ResourceBundle getResourceBundle() {

        return ResourceBundle.getBundle(BUNDLE_NAME, getViewRoot().getLocale());
    }

    protected String getMessage(String key) {

        if (key == null) {
            throw new IllegalArgumentException(getMessage("key.nao.nulo"));
        }

        return getResourceBundle().getString(key);
    }

    protected String getMessage(String key, String... parametros) {

        String mensagem = getMessage(key);
        if (parametros != null) {
            mensagem = MessageFormat.format(mensagem, (Object[]) parametros);
        }

        return mensagem;
    }

    protected void adicionarMensagemInfo(String texto) {
        adicionarMensagem(texto, FacesMessage.SEVERITY_INFO);
    }

    protected void adicionarMensagemInfoByKey(String key, String... parameters) {
        adicionarMensagemByKey(FacesMessage.SEVERITY_INFO, key, parameters);
    }

    protected void adicionarMensagemErro(String texto) {
        adicionarMensagem(texto, FacesMessage.SEVERITY_ERROR);
    }

    protected void adicionarMensagemErroByKey(String key, String... parameters) {
        adicionarMensagemByKey(FacesMessage.SEVERITY_ERROR, key, parameters);
    }

    protected void adicionarMensagemAlerta(String texto) {
        adicionarMensagem(texto, FacesMessage.SEVERITY_WARN);
    }

    protected void adicionarMensagemAlertaByKey(String key, String... parameters) {
        adicionarMensagemByKey(FacesMessage.SEVERITY_WARN, key, parameters);
    }

    protected void keepMessagesOnRedirect() {
        getFlash().setKeepMessages(true);
    }

    @SafeVarargs
    protected final void removeNullValues(final List<? extends Entidade>... lists) {
        for (List<? extends Entidade> list : lists) {
            while (list.remove(null)) {}
        }
    }

    protected String getFileNameFromPart(Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                return fileName;
            }
        }
        return null;
    }

    private void adicionarMensagem(String texto, Severity severity) {
        getCurrentInstance().addMessage(null, new FacesMessage(severity, texto, null));
    }

    private void adicionarMensagemByKey(Severity severity, String key, String... parameters) {
        getCurrentInstance().addMessage(null, new FacesMessage(severity, getMessage(key, parameters), null));
    }
}
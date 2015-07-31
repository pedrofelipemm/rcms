package br.ufscar.rcms.view.mb;

import static br.ufscar.rcms.util.MiscellanyUtil.isEmpty;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.application.ViewHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.context.PartialViewContext;
import javax.faces.event.PhaseId;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import br.ufscar.rcms.modelo.entidades.Entidade;

@ManagedBean
@SuppressWarnings("serial")
public abstract class AbstractMB implements Serializable {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractMB.class);
    private static final String BUNDLE_NAME = "bundle";

    public static final String PAGINA_ATUAL = null;

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

    // Projetos de Pesquisa
    public static final String CADASTRO_PROJETO_PESQUISA = "cadastroProjetoPesquisa";
    public static final String CONSULTA_PROJETO_PESQUISA = "consultaProjetoPesquisa";
    public static final String EXIBE_PROJETO_PESQUISA = "projetoPesquisa";
    public static final String FLASH_KEY_PROJETO_PESQUISA = "projetoPesquisa";


    // Painel de Controle
    public static final String PAINEL_CONTROLE = "painelControle";

    // Produções
    public static final String CONSULTA_PRODUCAO = "consultaProducao";
    public static final String CADASTRO_PRODUCAO = "cadastroProducao";
    public static final String FLASH_KEY_PRODUCAO = "producao";

    private static final User DEFAULT_USER = new User("DEFAULT-USER", "123456", new ArrayList<GrantedAuthority>());

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

    protected PhaseId getCurrentPhaseId() {
        return getCurrentInstance().getCurrentPhaseId();
    }

    protected Application getApplication() {
        return getCurrentInstance().getApplication();
    }

    protected ViewHandler getViewHandler() {
        return getApplication().getViewHandler();
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

    protected String getViewId() {
        return getViewRoot().getViewId();
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

    protected String getRealPath(final String path) {
        return getContext().getRealPath(path);
    }

    protected String getRealPath() {
        return getRealPath(StringUtils.EMPTY);
    }

    protected Flash getFlash() {
        return getExternalContext().getFlash();
    }

    protected void setFlashObject(final String key, final Object value) {

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

    protected Object getFlashObject(final String key) {

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

    protected String getMessage(final String key) {

        if (key == null) {
            throw new IllegalArgumentException(getMessage("key.nao.nulo"));
        }

        return getResourceBundle().getString(key);
    }

    protected String getMessage(final String key, final String... parametros) {

        String mensagem = getMessage(key);
        if (parametros != null) {
            mensagem = MessageFormat.format(mensagem, (Object[]) parametros);
        }

        return mensagem;
    }

    protected void adicionarMensagemInfo(final String texto) {
        adicionarMensagem(texto, FacesMessage.SEVERITY_INFO);
    }

    protected void adicionarMensagemInfoByKey(final String key, final String... parameters) {
        adicionarMensagemByKey(FacesMessage.SEVERITY_INFO, key, parameters);
    }

    protected void adicionarMensagemErro(final String texto) {
        adicionarMensagem(texto, FacesMessage.SEVERITY_ERROR);
    }

    protected void adicionarMensagemErroByKey(final String key, final String... parameters) {
        adicionarMensagemByKey(FacesMessage.SEVERITY_ERROR, key, parameters);
    }

    protected void adicionarMensagemAlerta(final String texto) {
        adicionarMensagem(texto, FacesMessage.SEVERITY_WARN);
    }

    protected void adicionarMensagemAlertaByKey(final String key, final String... parameters) {
        adicionarMensagemByKey(FacesMessage.SEVERITY_WARN, key, parameters);
    }

    protected void keepMessagesOnRedirect() {
        getFlash().setKeepMessages(true);
    }

    protected void noCacheRefresh() {
        FacesContext context = getCurrentInstance();
        String viewId = getViewId();
        ViewHandler handler = getViewHandler();
        UIViewRoot root = handler.createView(context, viewId);
        root.setViewId(viewId);
        context.setViewRoot(root);
        HttpServletResponse response = getResponse();
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);
    }

    @SafeVarargs
    protected final void removeNullValues(final List<? extends Entidade>... lists) {
        for (List<? extends Entidade> list : lists) {
            while (list.remove(null)) {}
        }
    }

    protected String getFileNameFromPart(final Part part) {
        final String partHeader = part.getHeader("content-disposition");
        for (String content : partHeader.split(";")) {
            if (content.trim().startsWith("filename")) {
                String fileName = content.substring(content.indexOf('=') + 1).trim().replace("\"", "");
                return fileName;
            }
        }
        return null;
    }

    protected SecurityContext getSecurityContext() {
        return SecurityContextHolder.getContext();
    }

    protected Authentication getAuthentication() {
        return getSecurityContext().getAuthentication();
    }

    protected UserDetails getPrincipal() {
        final Authentication authentication = getAuthentication();
        return isEmpty(authentication) || authentication.getPrincipal() instanceof String ? DEFAULT_USER
                : (UserDetails) authentication.getPrincipal();
    }

    private void adicionarMensagem(final String texto, final Severity severity) {
        getCurrentInstance().addMessage(null, new FacesMessage(severity, texto, null));
    }

    private void adicionarMensagemByKey(final Severity severity, final String key, final String... parameters) {
        getCurrentInstance().addMessage(null, new FacesMessage(severity, getMessage(key, parameters), null));
    }
}
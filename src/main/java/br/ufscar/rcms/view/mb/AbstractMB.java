package br.ufscar.rcms.view.mb;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ManagedBean
@SuppressWarnings("serial")
public abstract class AbstractMB implements Serializable {

    private static final String BUNDLE_NAME = "bundle_pt";

    public FacesContext getCurrentInstance() {

        return FacesContext.getCurrentInstance();
    }

    public UIViewRoot getViewRoot() {

        return getCurrentInstance().getViewRoot();
    }

    public ExternalContext getExternalContext() {

        return getCurrentInstance().getExternalContext();
    }

    public void setFlashObject(String key, Object value) {

        if (key == null || value == null) {
            throw new IllegalArgumentException("key.value.nao.nulo");
        }

        getExternalContext().getFlash().put(key, value);
    }

    public Object getFlashObject(String key) {

        return getExternalContext().getFlash().get(key);
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
}

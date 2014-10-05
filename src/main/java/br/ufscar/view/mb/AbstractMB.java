package br.ufscar.view.mb;

import java.io.Serializable;
import java.text.MessageFormat;
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

@ManagedBean
@SuppressWarnings("serial")
public abstract class AbstractMB implements Serializable {

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

        getFlash().put(key, value);
    }

    public Object getFlashObject(String key) {

        return getFlash().get(key);
    }

    public HttpServletRequest getRequest() {

        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public HttpServletResponse getResponse() {

        return (HttpServletResponse) getExternalContext().getResponse();
    }

    public ResourceBundle getResourceBundle() {

        return ResourceBundle.getBundle("bundle", getViewRoot().getLocale());
    }

    public String getMessage(String key) {

        if (key == null) {
            throw new IllegalArgumentException("key.nao.nulo");
        }

        return getResourceBundle().getString(key);
    }

    public String getMessage(String key, String... parametros) {

        String mensagem = getMessage(key);
        if (parametros == null) {
            throw new IllegalArgumentException("parametros.nao.nulo");
        }

        internacionalizarParametros(parametros);
        mensagem = MessageFormat.format(mensagem, (Object[]) parametros);

        return mensagem;
    }

    private void internacionalizarParametros(String... arrayParametros) {

        if (arrayParametros == null) {
            return;
        }

        for (String parametro : arrayParametros) {

            String parametroInternacionalizado = getMessage(parametro);
            parametro = (parametroInternacionalizado == null) ? parametro : parametroInternacionalizado;
        }
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

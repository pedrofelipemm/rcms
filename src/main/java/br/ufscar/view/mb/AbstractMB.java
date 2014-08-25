package br.ufscar.view.mb;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.ResourceBundle;

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
            //TODO i18n
            throw new IllegalArgumentException("Parâmetros key e value não podem ser nulos");
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
            //TODO i18n
            throw new IllegalArgumentException("Key não pode ser nula");
        }

        return getResourceBundle().getString(key);
    }

    public String getMessage(String key, String... parameters) {

        String mensagem = getMessage(key);
        if (parameters == null) {
            //TODO i18n
            throw new IllegalArgumentException("Parameters não pode ser nulo");
        }

        internacionalizarParametros(parameters);
        mensagem = MessageFormat.format(mensagem, (Object[]) parameters);

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
}

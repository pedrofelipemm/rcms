package br.ufscar.rcms.view.mb;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class ErrorHandler {

    private Map<String, Object> getRequestMap() {
        return FacesContext.getCurrentInstance().getExternalContext().getRequestMap();
    }

    public String getStatusCode() {
        Object val = getRequestMap().get("javax.servlet.error.status_code");
        return val != null ? val.toString() : "";
    }

    public String getMessage() {
        Object val = getRequestMap().get("javax.servlet.error.message");
        return val != null ? val.toString() : "";
    }

    public String getExceptionType() {
        Object val = getRequestMap().get("javax.servlet.error.exception_type");
        return val != null ? val.toString() : "";
    }

    public String getException() {
        Object val = getRequestMap().get("javax.servlet.error.exception");
        return val != null ? val.toString() : "";
    }

    public String getRequestURI() {
        Object val = getRequestMap().get("javax.servlet.error.request_uri");
        return val != null ? val.toString() : "";
    }

    public String getServletName() {
        Object val = getRequestMap().get("javax.servlet.error.servlet_name");
        return val != null ? val.toString() : "";
    }

}


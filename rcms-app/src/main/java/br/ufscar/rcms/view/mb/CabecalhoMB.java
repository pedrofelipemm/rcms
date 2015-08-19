package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ViewScoped
@ManagedBean(name = "cabecalhoMB")
public class CabecalhoMB extends AbstractMB {

    private static final long serialVersionUID = -3772673798496410759L;

    private String menuAtivo;

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
    }

    @Override
    protected void limparDados() {
    }

    @Override
    protected void carregarDados() {
        carregarMenuCSSClass();
    }

    public void carregarMenuCSSClass() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();
        String uri = request.getRequestURL().toString();

        if (uri.contains("sobre-o-grupo"))
            this.setMenuAtivo("sobre-o-grupo");
        else if (uri.contains("pesquisador"))
            this.setMenuAtivo("pesquisador");
        else if (uri.contains("projeto"))
            this.setMenuAtivo("projeto");
        else if (uri.contains("producao"))
            this.setMenuAtivo("producao");
        else
            this.setMenuAtivo("inicio");
    }

    public String getMenuAtivo() {
        return menuAtivo;
    }

    public void setMenuAtivo(String menuAtivo) {
        this.menuAtivo = menuAtivo;
    }

}

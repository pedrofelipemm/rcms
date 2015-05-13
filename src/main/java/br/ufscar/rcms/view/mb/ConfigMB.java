package br.ufscar.rcms.view.mb;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.PartialViewContext;

@ManagedBean(name = "configMB")
@SessionScoped
public class ConfigMB extends AbstractMB{

    private static final long serialVersionUID = -8168079468992950249L;

    private String lingua;

    private Map<String, String> linguas;

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
    };

    @Override
    protected void limparDados() {
    }

    @Override
    protected void carregarDados() {
        lingua = getViewRoot().getLocale().toString();
        linguas = new HashMap<String, String>();
        // TODO PEDRO LANGUANGUES PROPS
        linguas.put(getMessage("portugues"), "pt_BR");
        linguas.put(getMessage("ingles"), "en");

        alterarIdioma();
    }

    public void alterarIdioma() {

        PartialViewContext partialViewContext = getPartialViewContext();
        partialViewContext.getRenderIds().addAll(partialViewContext.getExecuteIds());
        getViewRoot().setLocale(new Locale(lingua));

        salvarConfiguracoes();
    }

    public String salvarConfiguracoes() {

        // TODO PEDRO SAVE CONFIG TABLE

        limparDados();
        adicionarMensagemInfo("");
        return PAINEL_CONTROLE;
    }

    public String getLingua() {
        return lingua;
    }

    public void setLingua(String lingua) {
        this.lingua = lingua;
    }

    public Map<String, String> getLinguas() {
        return linguas;
    }

    public void setLinguas(Map<String, String> linguas) {
        this.linguas = linguas;
    }
}
package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.servico.IdiomaService;

@ViewScoped
@ManagedBean(name = "idiomaMB")
public class IdiomaMB extends AbstractMB {

    private static final long serialVersionUID = -7215958678138311243L;

    @ManagedProperty("#{idiomaService}")
    private IdiomaService idiomaService;

    private Idioma idioma;

    @PostConstruct
    public void inicializar() {
        setIdioma(new Idioma());
    }

    public void salvar() {
    	
        if (getIdioma() != null) {
            Idioma idiomaExistente = getIdiomaService().buscarPorDescricao(getIdioma().getDescricao());

            if (idiomaExistente == null) {
                getIdiomaService().salvar(getIdioma());
                // TODO i18n
                adicionarMensagemInfo("Idioma " + idioma.getDescricao() + " cadastrado com sucesso!");
                idioma = new Idioma();
            } else {
                adicionarMensagemAlerta("Já existe um idioma com a descrição: " + idioma.getDescricao());
            }

        }
    }

    public IdiomaService getIdiomaService() {
        return idiomaService;
    }

    public void setIdiomaService(IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }
}

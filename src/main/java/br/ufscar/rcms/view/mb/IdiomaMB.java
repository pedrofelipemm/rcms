package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.servico.IdiomaService;
import br.ufscar.rcms.servico.exception.IdiomaEmUsoException;
import br.ufscar.rcms.servico.exception.IdiomaNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;

@ViewScoped
@ManagedBean(name = "idiomaMB")
public class IdiomaMB extends AbstractMB {

    private static final long serialVersionUID = -7215958678138311243L;

    private transient DataModel<Idioma> idiomas;

    @ManagedProperty("#{idiomaService}")
    private IdiomaService idiomaService;

    private Idioma idioma;

    @PostConstruct
    public void inicializar() {

        limparDados();
        carregarDados();
    }

    @Override
    protected void limparDados() {
        idioma = new Idioma();
    }

    @Override
    protected void carregarDados() {

        Idioma idiomaEdicao = (Idioma) getFlashObject(FLASH_KEY_IDIOMA);
        if (idiomaEdicao != null) {
            idioma = idiomaEdicao;
        }
        idiomas = new ListDataModel<Idioma>(idiomaService.buscarTodos());
    }

    public DataModel<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(DataModel<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public String salvar() {

        try {
            idiomaService.saveOrUpdate(idioma);
            adicionarMensagemInfoByKey("idioma.salvo.sucesso", idioma.getDescricao());

            limparDados();
            keepMessagesOnRedirect();
            return CONSULTA_IDIOMAS;
        } catch (RCMSException e) {
            adicionarMensagemErro(e.getMessage());
            return PERMANECER_PAGINA;
        }
    }

    public String excluirIdioma(Idioma idioma) {

        try {
            idiomaService.remover(idioma);
            adicionarMensagemInfoByKey("idioma.removido.sucesso", idioma.getDescricao());

            limparDados();
            keepMessagesOnRedirect();
            return CONSULTA_IDIOMAS;
        } catch (RCMSException exception) {
            if (exception instanceof IdiomaNaoEncontradoException) {
                adicionarMensagemErroByKey("idioma.nao.econtrado", idioma.getDescricao());
            } else if (exception instanceof IdiomaEmUsoException) {
                adicionarMensagemErro(exception.getMessage());
            }
            return PERMANECER_PAGINA;
        }
    }

    public String editarIdioma(Idioma idioma) {

        setFlashObject(FLASH_KEY_IDIOMA, idioma);

        return CADASTRO_IDIOMAS;
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

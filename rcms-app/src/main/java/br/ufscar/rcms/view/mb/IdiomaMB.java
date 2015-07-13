package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufscar.rcms.comparator.IdiomaComparator;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.servico.IdiomaService;
import br.ufscar.rcms.servico.exception.IdiomaEmUsoException;
import br.ufscar.rcms.servico.exception.IdiomaNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;
import br.ufscar.rcms.view.model.SortableDataModel;

import static br.ufscar.rcms.util.MiscellanyUtil.isEmpty;

@ViewScoped
@ManagedBean(name = "idiomaMB")
public class IdiomaMB extends AbstractMB {

    private static final long serialVersionUID = -7215958678138311243L;

    private static final Logger LOGGER = LoggerFactory.getLogger(IdiomaMB.class);

    @ManagedProperty("#{idiomaService}")
    private IdiomaService idiomaService;

    private DataModel<Idioma> idiomas;

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
        if (!isEmpty(idiomaEdicao)) {
            idioma = idiomaEdicao;
        }
        idiomas = new SortableDataModel<Idioma>(new ListDataModel<Idioma>(idiomaService.buscarTodos()));
        ((SortableDataModel<Idioma>) idiomas).sortBy(new IdiomaComparator());
    }

    public String salvar() {

        try {
            idiomaService.saveOrUpdate(idioma);
            adicionarMensagemInfoByKey("idioma.salvo.sucesso", idioma.getDescricao());

            limparDados();
            keepMessagesOnRedirect();
            return CONSULTA_IDIOMAS;
        } catch (RCMSException e) {
            LOGGER.error("Erro ao salvar idioma", e);
            adicionarMensagemErro(e.getMessage());
            return PAGINA_ATUAL;
        }
    }

    public String excluirIdioma(final Idioma idioma) {

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
            return PAGINA_ATUAL;
        }
    }

    public String editarIdioma(final Idioma idioma) {

        setFlashObject(FLASH_KEY_IDIOMA, idioma);

        return CADASTRO_IDIOMAS;
    }

    public IdiomaService getIdiomaService() {
        return idiomaService;
    }

    public void setIdiomaService(final IdiomaService idiomaService) {
        this.idiomaService = idiomaService;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(final Idioma idioma) {
        this.idioma = idioma;
    }

    public DataModel<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(final DataModel<Idioma> idiomas) {
        this.idiomas = idiomas;
    }
}
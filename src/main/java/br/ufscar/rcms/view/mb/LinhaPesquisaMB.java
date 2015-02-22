package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufscar.rcms.modelo.entidades.LinhaPesquisa;
import br.ufscar.rcms.servico.LinhaPesquisaService;

@ViewScoped
@ManagedBean(name = "linhaPesquisaMB")
public class LinhaPesquisaMB extends AbstractMB {

    private static final long serialVersionUID = 2911399543376254607L;

    @ManagedProperty("#{linhaPesquisaService}")
    private LinhaPesquisaService linhaPesquisaService;

    private LinhaPesquisa linhaPesquisa;

    private transient DataModel<LinhaPesquisa> linhasDePesquisa;

    @PostConstruct
    public void inicializar() {

        limparDados();
        carregarDados();
    }

    protected void carregarDados() {

        linhasDePesquisa = new ListDataModel<LinhaPesquisa>(linhaPesquisaService.buscarTodos());

        LinhaPesquisa linhaPesquisaEdicao = (LinhaPesquisa) getFlashObject(FLASH_KEY_LINHA_PESQUISA);
        if (linhaPesquisaEdicao != null) {
            linhaPesquisa = linhaPesquisaEdicao;

        }
    }

    protected void limparDados() {

        linhaPesquisa = new LinhaPesquisa();
    }

    public String salvar() {

        linhaPesquisaService.salvarOuAtualizar(linhaPesquisa);

        adicionarMensagemInfoByKey("linha.de.pesquisa.salva.sucesso", linhaPesquisa.getDescricao());

        limparDados();

        return CONSULTA_LINHAS_PESQUISA;
    }

    public String editar(LinhaPesquisa linhaPesquisa) {

        setFlashObject(FLASH_KEY_LINHA_PESQUISA, linhaPesquisa);

        return CADASTRO_LINHA_PESQUISA;
    }

    public String excluir(LinhaPesquisa linhaPesquisa) {

        linhaPesquisaService.remover(linhaPesquisa);

        return CONSULTA_LINHAS_PESQUISA;
    }

    public LinhaPesquisaService getLinhaPesquisaService() {

        return linhaPesquisaService;
    }

    public void setLinhaPesquisaService(LinhaPesquisaService linhaPesquisaService) {

        this.linhaPesquisaService = linhaPesquisaService;
    }

    public LinhaPesquisa getLinhaPesquisa() {

        return linhaPesquisa;
    }

    public void setLinhaPesquisa(LinhaPesquisa linhaPesquisa) {

        this.linhaPesquisa = linhaPesquisa;
    }

    public DataModel<LinhaPesquisa> getLinhasDePesquisa() {
        return linhasDePesquisa;
    }

    public void setLinhasDePesquisa(DataModel<LinhaPesquisa> linhasDePesquisa) {
        this.linhasDePesquisa = linhasDePesquisa;
    }
}

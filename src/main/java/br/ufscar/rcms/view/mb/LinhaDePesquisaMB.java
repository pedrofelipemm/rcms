package br.ufscar.rcms.view.mb;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;

import br.ufscar.rcms.modelo.entidades.LinhaDePesquisa;
import br.ufscar.rcms.servico.LinhaDePesquisaService;

@ViewScoped
@ManagedBean(name = "linhaDePesquisaMB")
public class LinhaDePesquisaMB extends AbstractMB {

    private static final long serialVersionUID = 2911399543376254607L;

    @ManagedProperty("#{linhaDePesquisaService}")
    private LinhaDePesquisaService linhaDePesquisaService;

    private LinhaDePesquisa linhaDePesquisa;

    private transient DataModel<LinhaDePesquisa> linhasDePesquisa;

    @PostConstruct
    public void inicializar() {

        limparDados();
        carregarDados();
    }

    protected void carregarDados() {

        linhasDePesquisa = new ListDataModel<LinhaDePesquisa>(linhaDePesquisaService.buscarTodas());

        LinhaDePesquisa linhaDePesquisaEdicao = (LinhaDePesquisa) getFlashObject(FLASH_KEY_LINHA_PESQUISA);
        if (linhaDePesquisaEdicao != null) {
            linhaDePesquisa = linhaDePesquisaEdicao;

        }
    }

    protected void limparDados() {

        linhaDePesquisa = new LinhaDePesquisa();
    }

    public String salvar() {

        linhaDePesquisaService.salvarOuAtualizar(linhaDePesquisa);

        adicionarMensagemInfoByKey("linha.de.pesquisa.salva.sucesso", linhaDePesquisa.getDescricao());

        limparDados();

        return CONSULTA_LINHAS_PESQUISA;
    }

    public String editar(LinhaDePesquisa linhaDePesquisa) {

        setFlashObject(FLASH_KEY_LINHA_PESQUISA, linhaDePesquisa);

        return CADASTRO_LINHA_PESQUISA;
    }

    public String excluir(LinhaDePesquisa linhaDePesquisa) {

        linhaDePesquisaService.remover(linhaDePesquisa);

        return CONSULTA_LINHAS_PESQUISA;
    }

    public LinhaDePesquisaService getLinhaPesquisaService() {

        return linhaDePesquisaService;
    }

    public void setLinhaDePesquisaService(LinhaDePesquisaService linhaDePesquisaService) {

        this.linhaDePesquisaService = linhaDePesquisaService;
    }

    public LinhaDePesquisa getLinhaDePesquisa() {

        return linhaDePesquisa;
    }

    public void setLinhaDePesquisa(LinhaDePesquisa linhaDePesquisa) {

        this.linhaDePesquisa = linhaDePesquisa;
    }

    public DataModel<LinhaDePesquisa> getLinhasDePesquisa() {
        return linhasDePesquisa;
    }

    public void setLinhasDePesquisa(DataModel<LinhaDePesquisa> linhasDePesquisa) {
        this.linhasDePesquisa = linhasDePesquisa;
    }
}

package br.ufscar.rcms.view.mb;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ufscar.rcms.factory.ProducaoFactory;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;
import br.ufscar.rcms.modelo.entidades.OutraProducaoBibliografica;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.ProducaoService;

@ViewScoped
@ManagedBean(name = "producaoMB")
public class ProducaoMB extends AbstractMB {

    @ManagedProperty("#{pesquisadorService}")
    private PesquisadorService pesquisadorService;
    @ManagedProperty("#{producaoService}")
    private ProducaoService producaoService;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducaoMB.class);

    private static final long serialVersionUID = -3678684230889264324L;
    private List<Pesquisador> todosPesquisadores;
    private List<CitacaoBibliografica> todasCitacoesDoPesquisador;
    private CitacaoBibliografica nomeCitacao;
    private Producao producao;
    private Pesquisador pesquisadorSelecionado;
    private String tipoProducao;
    private List<Producao> producoes;

    @PostConstruct
    public void inicializar() {
        limparDados();
        carregarDados();
    }

    public void adicionarNomeCitacao() {
        if (nomeCitacao != null) {
            producao.getCitacaoBibliograficas().add(nomeCitacao);
        }
    }

    public void excluirNomeCitacao(CitacaoBibliografica citacao) {
        producao.getCitacaoBibliograficas().remove(citacao);
    }

    public String salvar() {
        try {

            if (producao != null) {
                Producao producaoTipada = ProducaoFactory.CastProducaoByTipo(tipoProducao, producao);

                getProducaoService().saveOrUpdate(producaoTipada);

                adicionarMensagemInfoByKey("publicacao.salva.sucesso", producao.getTitulo());

                limparDados();

                keepMessagesOnRedirect();
            }

        } catch (Exception exception) {
            adicionarMensagemErroByKey("erro.salvar.publicacao", producao.getTitulo());
            LOGGER.error(exception.getMessage(), exception);
        }

        return CONSULTA_PRODUCAO;
    }

    public String editar(Producao producao) {
        setFlashObject(FLASH_KEY_PRODUCAO, producao);

        return CADASTRO_PRODUCAO;
    }

    public String excluir(Producao producao) {
        producaoService.remove(producao);
        limparDados();
        return CONSULTA_PRODUCAO;
    }

    public void changePesquisador() {
        if (pesquisadorSelecionado != null) {
            pesquisadorSelecionado = pesquisadorService.buscarTodosDados(pesquisadorSelecionado.getIdUsuario());
            todasCitacoesDoPesquisador = pesquisadorSelecionado.getCitacaoBibliograficas();
        } else {
            todasCitacoesDoPesquisador.clear();
        }
    }

    @Override
    protected void limparDados() {
        todosPesquisadores = new ArrayList<Pesquisador>();
        todasCitacoesDoPesquisador = new ArrayList<CitacaoBibliografica>();
        nomeCitacao = new CitacaoBibliografica();
        producao = new OutraProducaoBibliografica();
        pesquisadorSelecionado = null;
        tipoProducao = "";
        producoes = new ArrayList<Producao>();
    }

    @Override
    protected void carregarDados() {
        todosPesquisadores = pesquisadorService.buscarTodos();
        producoes = producaoService.buscarTodas();

        Producao producaoEditando = (Producao) getFlashObject(FLASH_KEY_PRODUCAO);
        if (producaoEditando != null) {
            this.producao = producaoService.buscarPorId(producaoEditando.getIdProducao());
        }
    }

    public CitacaoBibliografica getNomeCitacao() {
        return nomeCitacao;
    }

    public void setNomeCitacao(CitacaoBibliografica nomeCitacao) {
        this.nomeCitacao = nomeCitacao;
    }

    public Producao getProducao() {
        return producao;
    }

    public void setProducao(Producao producao) {
        this.producao = producao;
    }

    public List<Pesquisador> getTodosPesquisadores() {
        return todosPesquisadores;
    }

    public void setTodosPesquisadores(List<Pesquisador> todosPesquisadores) {
        this.todosPesquisadores = todosPesquisadores;
    }

    public PesquisadorService getPesquisadorService() {
        return pesquisadorService;
    }

    public void setPesquisadorService(PesquisadorService pesquisadorService) {
        this.pesquisadorService = pesquisadorService;
    }

    public Pesquisador getPesquisadorSelecionado() {
        return pesquisadorSelecionado;
    }

    public void setPesquisadorSelecionado(Pesquisador pesquisadorSelecionado) {
        this.pesquisadorSelecionado = pesquisadorSelecionado;
    }

    public List<CitacaoBibliografica> getTodasCitacoesDoPesquisador() {
        return todasCitacoesDoPesquisador;
    }

    public void setTodasCitacoesDoPesquisador(List<CitacaoBibliografica> todasCitacoesDoPesquisador) {
        this.todasCitacoesDoPesquisador = todasCitacoesDoPesquisador;
    }

    public String getTipoProducao() {
        return tipoProducao;
    }

    public void setTipoProducao(String tipoProducao) {
        this.tipoProducao = tipoProducao;
    }

    public ProducaoService getProducaoService() {
        return producaoService;
    }

    public void setProducaoService(ProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    public List<Producao> getProducoes() {
        return producoes;
    }

    public void setProducoes(List<Producao> producoes) {
        this.producoes = producoes;
    }
}

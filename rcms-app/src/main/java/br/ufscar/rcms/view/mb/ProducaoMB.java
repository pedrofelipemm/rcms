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
import br.ufscar.rcms.modelo.entidades.ApresentacaoTrabalho;
import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.CapituloLivro;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;
import br.ufscar.rcms.modelo.entidades.LivroPublicado;
import br.ufscar.rcms.modelo.entidades.OutraProducaoBibliografica;
import br.ufscar.rcms.modelo.entidades.OutraProducaoTecnica;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.ProcessoOuTecnica;
import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.modelo.entidades.ProdutoTecnologico;
import br.ufscar.rcms.modelo.entidades.ResumoCongresso;
import br.ufscar.rcms.modelo.entidades.ResumoExpandidoCongresso;
import br.ufscar.rcms.modelo.entidades.TextoEmJornal;
import br.ufscar.rcms.modelo.entidades.TrabalhoCompletoCongresso;
import br.ufscar.rcms.modelo.entidades.TrabalhoTecnico;
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

    private transient List<ArtigoEmPeriodico> listaArtigosEmPeriodicos;
    private transient List<LivroPublicado> listaLivrosPublicados;
    private transient List<CapituloLivro> listaCapitulosDeLivros;
    private transient List<TextoEmJornal> listaTextosEmJornais;
    private transient List<TrabalhoCompletoCongresso> listaTrabalhosCompletosCongressos;
    private transient List<ResumoExpandidoCongresso> listaResumosExpandidosCongressos;
    private transient List<ResumoCongresso> listaResumosCongressos;
    private transient List<ApresentacaoTrabalho> listaApresentacoesTrabalhos;
    private transient List<OutraProducaoBibliografica> listaOutrasProducoesBibligraficas;
    private transient List<ProdutoTecnologico> listaProdutosTecnologicos;
    private transient List<ProcessoOuTecnica> listaProcessosOuTecnicas;
    private transient List<TrabalhoTecnico> listaTrabalhosTecnicos;
    private transient List<OutraProducaoTecnica> listaOutrasProducoesTecnicas;

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

                adicionarMensagemInfoByKey("producao.salva.sucesso", producao.getTitulo());

                limparDados();

                keepMessagesOnRedirect();
            }

        } catch (Exception exception) {
            adicionarMensagemErroByKey("erro.salvar.producao", producao.getTitulo());
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

        this.setListaArtigosEmPeriodicos(this.producaoService.buscarProducoes(ArtigoEmPeriodico.class));
        this.setListaLivrosPublicados(this.producaoService.buscarProducoes(LivroPublicado.class));
        this.setListaCapitulosDeLivros(this.producaoService.buscarProducoes(CapituloLivro.class));
        this.setListaTextosEmJornais(this.producaoService.buscarProducoes(TextoEmJornal.class));
        this.setListaTrabalhosCompletosCongressos(this.producaoService.buscarProducoes(TrabalhoCompletoCongresso.class));
        this.setListaResumosExpandidosCongressos(this.producaoService.buscarProducoes(ResumoExpandidoCongresso.class));
        this.setListaResumosCongressos(this.producaoService.buscarProducoes(ResumoCongresso.class));
        this.setListaApresentacoesTrabalhos(this.producaoService.buscarProducoes(ApresentacaoTrabalho.class));
        this.setListaOutrasProducoesBibligraficas(this.producaoService
                .buscarProducoes(OutraProducaoBibliografica.class));
        this.setListaProdutosTecnologicos(this.producaoService.buscarProducoes(ProdutoTecnologico.class));
        this.setListaProcessosOuTecnicas(this.producaoService.buscarProducoes(ProcessoOuTecnica.class));
        this.setListaTrabalhosTecnicos(this.producaoService.buscarProducoes(TrabalhoTecnico.class));
        this.setListaOutrasProducoesTecnicas(this.producaoService.buscarProducoes(OutraProducaoTecnica.class));
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

    public List<CitacaoBibliografica> listarCitacoes(Producao producao) {

        return producaoService.buscarPorId(producao.getIdProducao()).getCitacaoBibliograficas();
    }

    public List<ArtigoEmPeriodico> getListaArtigosEmPeriodicos() {
        return listaArtigosEmPeriodicos;
    }

    public void setListaArtigosEmPeriodicos(List<ArtigoEmPeriodico> listaArtigosEmPeriodicos) {
        this.listaArtigosEmPeriodicos = listaArtigosEmPeriodicos;
    }

    public List<LivroPublicado> getListaLivrosPublicados() {
        return listaLivrosPublicados;
    }

    public void setListaLivrosPublicados(List<LivroPublicado> listaLivrosPublicados) {
        this.listaLivrosPublicados = listaLivrosPublicados;
    }

    public List<CapituloLivro> getListaCapitulosDeLivros() {
        return listaCapitulosDeLivros;
    }

    public void setListaCapitulosDeLivros(List<CapituloLivro> listaCapitulosDeLivros) {
        this.listaCapitulosDeLivros = listaCapitulosDeLivros;
    }

    public List<TextoEmJornal> getListaTextosEmJornais() {
        return listaTextosEmJornais;
    }

    public void setListaTextosEmJornais(List<TextoEmJornal> listaTextosEmJornais) {
        this.listaTextosEmJornais = listaTextosEmJornais;
    }

    public List<TrabalhoCompletoCongresso> getListaTrabalhosCompletosCongressos() {
        return listaTrabalhosCompletosCongressos;
    }

    public void setListaTrabalhosCompletosCongressos(List<TrabalhoCompletoCongresso> listaTrabalhosCompletosCongressos) {
        this.listaTrabalhosCompletosCongressos = listaTrabalhosCompletosCongressos;
    }

    public List<ResumoExpandidoCongresso> getListaResumosExpandidosCongressos() {
        return listaResumosExpandidosCongressos;
    }

    public void setListaResumosExpandidosCongressos(List<ResumoExpandidoCongresso> listaResumosExpandidosCongressos) {
        this.listaResumosExpandidosCongressos = listaResumosExpandidosCongressos;
    }

    public List<ResumoCongresso> getListaResumosCongressos() {
        return listaResumosCongressos;
    }

    public void setListaResumosCongressos(List<ResumoCongresso> listaResumosCongressos) {
        this.listaResumosCongressos = listaResumosCongressos;
    }

    public List<ApresentacaoTrabalho> getListaApresentacoesTrabalhos() {
        return listaApresentacoesTrabalhos;
    }

    public void setListaApresentacoesTrabalhos(List<ApresentacaoTrabalho> listaApresentacoesTrabalhos) {
        this.listaApresentacoesTrabalhos = listaApresentacoesTrabalhos;
    }

    public List<OutraProducaoBibliografica> getListaOutrasProducoesBibligraficas() {
        return listaOutrasProducoesBibligraficas;
    }

    public void setListaOutrasProducoesBibligraficas(List<OutraProducaoBibliografica> listaOutrasProducoesBibligraficas) {
        this.listaOutrasProducoesBibligraficas = listaOutrasProducoesBibligraficas;
    }

    public List<ProdutoTecnologico> getListaProdutosTecnologicos() {
        return listaProdutosTecnologicos;
    }

    public void setListaProdutosTecnologicos(List<ProdutoTecnologico> listaProdutosTecnologicos) {
        this.listaProdutosTecnologicos = listaProdutosTecnologicos;
    }

    public List<ProcessoOuTecnica> getListaProcessosOuTecnicas() {
        return listaProcessosOuTecnicas;
    }

    public void setListaProcessosOuTecnicas(List<ProcessoOuTecnica> listaProcessosOuTecnicas) {
        this.listaProcessosOuTecnicas = listaProcessosOuTecnicas;
    }

    public List<TrabalhoTecnico> getListaTrabalhosTecnicos() {
        return listaTrabalhosTecnicos;
    }

    public void setListaTrabalhosTecnicos(List<TrabalhoTecnico> listaTrabalhosTecnicos) {
        this.listaTrabalhosTecnicos = listaTrabalhosTecnicos;
    }

    public List<OutraProducaoTecnica> getListaOutrasProducoesTecnicas() {
        return listaOutrasProducoesTecnicas;
    }

    public void setListaOutrasProducoesTecnicas(List<OutraProducaoTecnica> listaOutrasProducoesTecnicas) {
        this.listaOutrasProducoesTecnicas = listaOutrasProducoesTecnicas;
    }
}

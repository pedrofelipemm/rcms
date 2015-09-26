package br.ufscar.rcms.view.mb;

import static br.ufscar.rcms.commons.util.FileUtils.extractFileExtension;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.Part;

import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.StreamedContent;
import org.primefaces.model.UploadedFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import br.ufscar.rcms.factory.ProducaoFactory;
import br.ufscar.rcms.modelo.entidades.ApresentacaoTrabalho;
import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.AutorProducao;
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

    @Value("${pasta.arquivos.pdf.producao}")
    private String pastaArquivos;

    private static final Logger LOGGER = LoggerFactory.getLogger(ProducaoMB.class);

    private static final long serialVersionUID = -3678684230889264324L;
    private List<Pesquisador> todosPesquisadores;
    private List<CitacaoBibliografica> todasCitacoesDoPesquisador;
    private CitacaoBibliografica citacaoBibliografica;

    private AutorProducao autor;
    private Producao producao;
    private Pesquisador pesquisadorSelecionado;
    private String tipoProducao;
    private List<Producao> producoes;
    private UploadedFile pdf;
    private StreamedContent file;

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

    public void adicionarAutor() {
        autor = new AutorProducao();
        autor.setProducao(producao);
        autor.setCitacaoBibliografica(citacaoBibliografica);
        autor.setOrdemAutoria(producao.getAutores().size() + 1);
        if (autor != null) {
            producao.getAutores().add(autor);
        }
    }

    public void excluirAutor(AutorProducao autor) {
        producao.getAutores().remove(autor);
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

    public String exibir(Producao producao) {

        producao = producaoService.buscarPorId(producao.getIdProducao());

        setFlashObject(FLASH_KEY_PRODUCAO, producao);

        return EXIBE_PRODUCAO;
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
            if((todasCitacoesDoPesquisador != null) && (!todasCitacoesDoPesquisador.isEmpty()))
            	citacaoBibliografica = pesquisadorSelecionado.getCitacaoBibliograficas().get(0);
        } else {
            todasCitacoesDoPesquisador.clear();
        }
    }

    @Override
    protected void limparDados() {
        todosPesquisadores = new ArrayList<Pesquisador>();
        todasCitacoesDoPesquisador = new ArrayList<CitacaoBibliografica>();
        autor = new AutorProducao();
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

            file = producaoService.loadPDF(producaoEditando);
            /*
             * String caminhoWebInf = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/WEB-INF/");
             * InputStream stream; try { stream = new FileInputStream("/home/andre/RCMS/producao/" +
             * producao.getIdProducao() + ".pdf"); file = new DefaultStreamedContent(stream, "application/pdf",
             * "producao_" + producao.getIdProducao().toString() + ".pdf"); } catch (FileNotFoundException e) {
             * System.out.println("erro"); e.printStackTrace(); } /* InputStream stream = ((ServletContext)
             * FacesContext.getCurrentInstance().getExternalContext().getContext())
             * .getResourceAsStream("/home/andre/RCMS/producao/" + producao.getIdProducao() + ".pdf"); file = new
             * DefaultStreamedContent(stream, "application/pdf", "producao_" + producao.getIdProducao().toString() +
             * ".pdf");
             */
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

    public void doUpload(FileUploadEvent fileUploadEvent) {

        pdf = fileUploadEvent.getFile();

        try {
            producao.getArquivoPdf().setFile(IOUtils.toByteArray(pdf.getInputstream()));
            producao.getArquivoPdf().setFileExtension(extractFileExtension(pdf.getFileName()));
            producao.setNomePdf(pdf.getFileName());
        } catch (final IOException ioException) {
            LOGGER.error(String.format("Erro ao realizar upload do pdf para produção: %s", producao.getTitulo()),
                    ioException);
            adicionarMensagemErroByKey("falha.enviar.arquivo");
        }
    }

    public void validateFile(FacesContext ctx, UIComponent comp, Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part) value;

        if (!"application/pdf".equals(file.getContentType())) {
            adicionarMensagemErroByKey("arquivo.invalido.pdf");
        }

        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    public AutorProducao getAutor() {
        return autor;
    }

    public void setAutor(AutorProducao autor) {
        this.autor = autor;
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

    public List<AutorProducao> listarAutores(Producao producao) {

        return producaoService.buscarPorId(producao.getIdProducao()).getAutores();
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

    public CitacaoBibliografica getCitacaoBibliografica() {
        return citacaoBibliografica;
    }

    public void setCitacaoBibliografica(CitacaoBibliografica citacaoBibliografica) {
        this.citacaoBibliografica = citacaoBibliografica;
    }

    public UploadedFile getPdf() {
        return pdf;
    }

    public void setPdf(UploadedFile pdf) {
        this.pdf = pdf;
    }

    public StreamedContent getFile() {
        return file;
    }

    public StreamedContent downloadFile(Long idProducao) {
        Producao producao = producaoService.buscarPorId(idProducao);
        return producaoService.loadPDF(producao);
    }
}

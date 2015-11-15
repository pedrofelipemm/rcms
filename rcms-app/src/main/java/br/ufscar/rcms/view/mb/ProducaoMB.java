package br.ufscar.rcms.view.mb;

import static br.ufscar.rcms.commons.util.FileUtils.extractFileExtension;

import java.io.ByteArrayInputStream;
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
import org.primefaces.model.DefaultStreamedContent;
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
import br.ufscar.rcms.modelo.entidades.TransientFile;
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

    private List<ArtigoEmPeriodico> listaArtigosEmPeriodicos = new ArrayList<>();
    private List<LivroPublicado> listaLivrosPublicados = new ArrayList<>();
    private List<CapituloLivro> listaCapitulosDeLivros = new ArrayList<>();
    private List<TextoEmJornal> listaTextosEmJornais = new ArrayList<>();
    private List<TrabalhoCompletoCongresso> listaTrabalhosCompletosCongressos = new ArrayList<>();
    private List<ResumoExpandidoCongresso> listaResumosExpandidosCongressos = new ArrayList<>();
    private List<ResumoCongresso> listaResumosCongressos = new ArrayList<>();
    private List<ApresentacaoTrabalho> listaApresentacoesTrabalhos = new ArrayList<>();
    private List<OutraProducaoBibliografica> listaOutrasProducoesBibligraficas = new ArrayList<>();
    private List<ProdutoTecnologico> listaProdutosTecnologicos = new ArrayList<>();
    private List<ProcessoOuTecnica> listaProcessosOuTecnicas = new ArrayList<>();
    private List<TrabalhoTecnico> listaTrabalhosTecnicos = new ArrayList<>();
    private List<OutraProducaoTecnica> listaOutrasProducoesTecnicas = new ArrayList<>();

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

    public void excluirAutor(final AutorProducao autor) {
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

    public String editar(final Producao producao) {
        setFlashObject(FLASH_KEY_PRODUCAO, producao);

        return CADASTRO_PRODUCAO;
    }

    public String excluir(final Producao producao) {
        producaoService.remove(producao);
        limparDados();
        return CONSULTA_PRODUCAO;
    }

    public void changePesquisador() {
        if (pesquisadorSelecionado != null) {
            pesquisadorSelecionado = pesquisadorService.buscarTodosDados(pesquisadorSelecionado.getIdUsuario());
            todasCitacoesDoPesquisador = pesquisadorSelecionado.getCitacaoBibliograficas();
            if((todasCitacoesDoPesquisador != null) && (!todasCitacoesDoPesquisador.isEmpty())) {
                citacaoBibliografica = pesquisadorSelecionado.getCitacaoBibliograficas().get(0);
            }
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
        producoes = producaoService.buscarTodasComPdf();

        Producao producaoEditando = (Producao) getFlashObject(FLASH_KEY_PRODUCAO);
        if (producaoEditando != null) {
            this.producao = producaoService.buscarPorId(producaoEditando.getIdProducao());
            file = producaoService.loadPDF(producaoEditando);
        }

        carregarListasProducao();
    }

    public void doUpload(final FileUploadEvent fileUploadEvent) {

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

    public void validateFile(final FacesContext ctx, final UIComponent comp, final Object value) {
        List<FacesMessage> msgs = new ArrayList<FacesMessage>();
        Part file = (Part) value;

        if (!"application/pdf".equals(file.getContentType())) {
            adicionarMensagemErroByKey("arquivo.invalido.pdf");
        }

        if (!msgs.isEmpty()) {
            throw new ValidatorException(msgs);
        }
    }

    private void carregarListasProducao() {
        for (Producao prod : producoes) {
            if (prod instanceof ArtigoEmPeriodico) {
                listaArtigosEmPeriodicos.add((ArtigoEmPeriodico) prod);
            } else if (prod instanceof LivroPublicado) {
                listaLivrosPublicados.add((LivroPublicado) prod);
            } else if (prod instanceof CapituloLivro) {
                listaCapitulosDeLivros.add((CapituloLivro) prod);
            } else if (prod instanceof TextoEmJornal) {
                listaTextosEmJornais.add((TextoEmJornal) prod);
            } else if (prod instanceof TrabalhoCompletoCongresso) {
                listaTrabalhosCompletosCongressos.add((TrabalhoCompletoCongresso) prod);
            } else if (prod instanceof ResumoExpandidoCongresso) {
                listaResumosExpandidosCongressos.add((ResumoExpandidoCongresso) prod);
            } else if (prod instanceof ResumoCongresso) {
                listaResumosCongressos.add((ResumoCongresso) prod);
            } else if (prod instanceof ApresentacaoTrabalho) {
                listaApresentacoesTrabalhos.add((ApresentacaoTrabalho) prod);
            } else if (prod instanceof OutraProducaoBibliografica) {
                listaOutrasProducoesBibligraficas.add((OutraProducaoBibliografica) prod);
            } else if (prod instanceof OutraProducaoTecnica) {
                listaOutrasProducoesTecnicas.add((OutraProducaoTecnica) prod);
            } else if (prod instanceof ProdutoTecnologico) {
                listaProdutosTecnologicos.add((ProdutoTecnologico) prod);
            } else if (prod instanceof ProcessoOuTecnica) {
                listaProcessosOuTecnicas.add((ProcessoOuTecnica) prod);
            } else if (prod instanceof TrabalhoTecnico) {
                listaTrabalhosTecnicos.add((TrabalhoTecnico) prod);
            }
        }
    }

    public AutorProducao getAutor() {
        return autor;
    }

    public void setAutor(final AutorProducao autor) {
        this.autor = autor;
    }

    public Producao getProducao() {
        return producao;
    }

    public void setProducao(final Producao producao) {
        this.producao = producao;
    }

    public List<Pesquisador> getTodosPesquisadores() {
        return todosPesquisadores;
    }

    public void setTodosPesquisadores(final List<Pesquisador> todosPesquisadores) {
        this.todosPesquisadores = todosPesquisadores;
    }

    public PesquisadorService getPesquisadorService() {
        return pesquisadorService;
    }

    public void setPesquisadorService(final PesquisadorService pesquisadorService) {
        this.pesquisadorService = pesquisadorService;
    }

    public Pesquisador getPesquisadorSelecionado() {
        return pesquisadorSelecionado;
    }

    public void setPesquisadorSelecionado(final Pesquisador pesquisadorSelecionado) {
        this.pesquisadorSelecionado = pesquisadorSelecionado;
    }

    public List<CitacaoBibliografica> getTodasCitacoesDoPesquisador() {
        return todasCitacoesDoPesquisador;
    }

    public void setTodasCitacoesDoPesquisador(final List<CitacaoBibliografica> todasCitacoesDoPesquisador) {
        this.todasCitacoesDoPesquisador = todasCitacoesDoPesquisador;
    }

    public String getTipoProducao() {
        return tipoProducao;
    }

    public void setTipoProducao(final String tipoProducao) {
        this.tipoProducao = tipoProducao;
    }

    public ProducaoService getProducaoService() {
        return producaoService;
    }

    public void setProducaoService(final ProducaoService producaoService) {
        this.producaoService = producaoService;
    }

    public List<Producao> getProducoes() {
        return producoes;
    }

    public void setProducoes(final List<Producao> producoes) {
        this.producoes = producoes;
    }

    public List<ArtigoEmPeriodico> getListaArtigosEmPeriodicos() {
        return listaArtigosEmPeriodicos;
    }

    public void setListaArtigosEmPeriodicos(final List<ArtigoEmPeriodico> listaArtigosEmPeriodicos) {
        this.listaArtigosEmPeriodicos = listaArtigosEmPeriodicos;
    }

    public List<LivroPublicado> getListaLivrosPublicados() {
        return listaLivrosPublicados;
    }

    public void setListaLivrosPublicados(final List<LivroPublicado> listaLivrosPublicados) {
        this.listaLivrosPublicados = listaLivrosPublicados;
    }

    public List<CapituloLivro> getListaCapitulosDeLivros() {
        return listaCapitulosDeLivros;
    }

    public void setListaCapitulosDeLivros(final List<CapituloLivro> listaCapitulosDeLivros) {
        this.listaCapitulosDeLivros = listaCapitulosDeLivros;
    }

    public List<TextoEmJornal> getListaTextosEmJornais() {
        return listaTextosEmJornais;
    }

    public void setListaTextosEmJornais(final List<TextoEmJornal> listaTextosEmJornais) {
        this.listaTextosEmJornais = listaTextosEmJornais;
    }

    public List<TrabalhoCompletoCongresso> getListaTrabalhosCompletosCongressos() {
        return listaTrabalhosCompletosCongressos;
    }

    public void setListaTrabalhosCompletosCongressos(final List<TrabalhoCompletoCongresso> listaTrabalhosCompletosCongressos) {
        this.listaTrabalhosCompletosCongressos = listaTrabalhosCompletosCongressos;
    }

    public List<ResumoExpandidoCongresso> getListaResumosExpandidosCongressos() {
        return listaResumosExpandidosCongressos;
    }

    public void setListaResumosExpandidosCongressos(final List<ResumoExpandidoCongresso> listaResumosExpandidosCongressos) {
        this.listaResumosExpandidosCongressos = listaResumosExpandidosCongressos;
    }

    public List<ResumoCongresso> getListaResumosCongressos() {
        return listaResumosCongressos;
    }

    public void setListaResumosCongressos(final List<ResumoCongresso> listaResumosCongressos) {
        this.listaResumosCongressos = listaResumosCongressos;
    }

    public List<ApresentacaoTrabalho> getListaApresentacoesTrabalhos() {
        return listaApresentacoesTrabalhos;
    }

    public void setListaApresentacoesTrabalhos(final List<ApresentacaoTrabalho> listaApresentacoesTrabalhos) {
        this.listaApresentacoesTrabalhos = listaApresentacoesTrabalhos;
    }

    public List<OutraProducaoBibliografica> getListaOutrasProducoesBibligraficas() {
        return listaOutrasProducoesBibligraficas;
    }

    public void setListaOutrasProducoesBibligraficas(final List<OutraProducaoBibliografica> listaOutrasProducoesBibligraficas) {
        this.listaOutrasProducoesBibligraficas = listaOutrasProducoesBibligraficas;
    }

    public List<ProdutoTecnologico> getListaProdutosTecnologicos() {
        return listaProdutosTecnologicos;
    }

    public void setListaProdutosTecnologicos(final List<ProdutoTecnologico> listaProdutosTecnologicos) {
        this.listaProdutosTecnologicos = listaProdutosTecnologicos;
    }

    public List<ProcessoOuTecnica> getListaProcessosOuTecnicas() {
        return listaProcessosOuTecnicas;
    }

    public void setListaProcessosOuTecnicas(final List<ProcessoOuTecnica> listaProcessosOuTecnicas) {
        this.listaProcessosOuTecnicas = listaProcessosOuTecnicas;
    }

    public List<TrabalhoTecnico> getListaTrabalhosTecnicos() {
        return listaTrabalhosTecnicos;
    }

    public void setListaTrabalhosTecnicos(final List<TrabalhoTecnico> listaTrabalhosTecnicos) {
        this.listaTrabalhosTecnicos = listaTrabalhosTecnicos;
    }

    public List<OutraProducaoTecnica> getListaOutrasProducoesTecnicas() {
        return listaOutrasProducoesTecnicas;
    }

    public void setListaOutrasProducoesTecnicas(final List<OutraProducaoTecnica> listaOutrasProducoesTecnicas) {
        this.listaOutrasProducoesTecnicas = listaOutrasProducoesTecnicas;
    }

    public CitacaoBibliografica getCitacaoBibliografica() {
        return citacaoBibliografica;
    }

    public void setCitacaoBibliografica(final CitacaoBibliografica citacaoBibliografica) {
        this.citacaoBibliografica = citacaoBibliografica;
    }

    public UploadedFile getPdf() {
        return pdf;
    }

    public void setPdf(final UploadedFile pdf) {
        this.pdf = pdf;
    }

    public StreamedContent getFile() {
        return file;
    }

    public StreamedContent downloadFile(final Producao producao) {
        if (!TransientFile.isEmpty(producao.getArquivoPdf())) {
            return new DefaultStreamedContent(new ByteArrayInputStream(producao.getArquivoPdf().getFile()),
                    "application/pdf", "producao_" + producao.getIdProducao().toString() + ".pdf");
        }
        return null;
    }
}

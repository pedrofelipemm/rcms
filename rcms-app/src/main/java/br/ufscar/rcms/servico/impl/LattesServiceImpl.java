package br.ufscar.rcms.servico.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.builder.PesquisadorBuilder;
import br.ufscar.rcms.commons.util.XMLUtils;
import br.ufscar.rcms.modelo.entidades.ApresentacaoTrabalho;
import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.AtuacaoPesquisador;
import br.ufscar.rcms.modelo.entidades.AutorProducao;
import br.ufscar.rcms.modelo.entidades.CapituloLivro;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;
import br.ufscar.rcms.modelo.entidades.CompreensaoIdioma;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.modelo.entidades.LivroPublicado;
import br.ufscar.rcms.modelo.entidades.OutraProducaoBibliografica;
import br.ufscar.rcms.modelo.entidades.OutraProducaoTecnica;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.ProcessoOuTecnica;
import br.ufscar.rcms.modelo.entidades.Producao;
import br.ufscar.rcms.modelo.entidades.ProdutoTecnologico;
import br.ufscar.rcms.modelo.entidades.ProjetoPesquisa;
import br.ufscar.rcms.modelo.entidades.ResumoCongresso;
import br.ufscar.rcms.modelo.entidades.ResumoExpandidoCongresso;
import br.ufscar.rcms.modelo.entidades.TextoEmJornal;
import br.ufscar.rcms.modelo.entidades.TrabalhoCompletoCongresso;
import br.ufscar.rcms.modelo.entidades.TrabalhoTecnico;
import br.ufscar.rcms.modelo.entidades.pk.CompreensaoIdiomaPK;
import br.ufscar.rcms.modelo.lattes.ArtigoLattes;
import br.ufscar.rcms.modelo.lattes.CapituloLattes;
import br.ufscar.rcms.modelo.lattes.CurriculoLattes;
import br.ufscar.rcms.modelo.lattes.LivroLattes;
import br.ufscar.rcms.modelo.lattes.OutraProducaoLattes;
import br.ufscar.rcms.modelo.lattes.OutraProducaoTecnicaLattes;
import br.ufscar.rcms.modelo.lattes.PesquisadorLattes;
import br.ufscar.rcms.modelo.lattes.ProdutoLattes;
import br.ufscar.rcms.modelo.lattes.ProdutoProcessoTecnicaLattes;
import br.ufscar.rcms.modelo.lattes.ProjetoLattes;
import br.ufscar.rcms.modelo.lattes.ResumoExpandidoLattes;
import br.ufscar.rcms.modelo.lattes.ResumoLattes;
import br.ufscar.rcms.modelo.lattes.TextoLattes;
import br.ufscar.rcms.modelo.lattes.TrabalhoApresentadoLattes;
import br.ufscar.rcms.modelo.lattes.TrabalhoCompletoLattes;
import br.ufscar.rcms.modelo.lattes.TrabalhoLattes;
import br.ufscar.rcms.servico.CitacaoBibliograficaService;
import br.ufscar.rcms.servico.GrandeAreaAtuacaoService;
import br.ufscar.rcms.servico.IdiomaService;
import br.ufscar.rcms.servico.LattesService;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.ProducaoService;
import br.ufscar.rcms.servico.ProjetoPesquisaService;
import br.ufscar.rcms.servico.exception.ArquivoNaoEncontradoException;
import br.ufscar.rcms.servico.exception.CurriculoLattesNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;


@Service("lattesService")
@Transactional
public class LattesServiceImpl implements LattesService {

    private static final long serialVersionUID = 4593268685421323315L;
    private static final Logger LOGGER = LoggerFactory.getLogger(LattesServiceImpl.class);
    private static final String SUFFIX_XML = "-database.xml";

    @Autowired
    private PesquisadorService pesquisadorService;

    @Autowired
    private ProjetoPesquisaService projetoPesquisaService;

    @Autowired
    private IdiomaService idiomaService;

    @Autowired
    private GrandeAreaAtuacaoService grandeAreaAtuacaoService;

    @Autowired
    private CitacaoBibliograficaService citacaoBibliograficaService;

    @Autowired
    private ProducaoService producaoService;

    @Value("${pasta.script.lattes}")
    private String pastaScriptLates;

    @Value("${arquivo.config.lattes}")
    private String arquivoConfig;

    @Value("${arquivo.curriculos.lattes}")
    private String arquivoCurriculoLattes;

    @Override
    public PesquisadorLattes carregarCurriculoLattes(final String codigoLattes) throws RCMSException {

        final CurriculoLattes curriculos = carregarCurriculosLattes(codigoLattes);
        for (final PesquisadorLattes pesquisador : curriculos.getPesquisadores()) {
            if (pesquisador.getCodigoLattes().equals(codigoLattes)) {
                return pesquisador;
            }
        }

        throw new CurriculoLattesNaoEncontradoException(codigoLattes);
    }

    @Override
    public synchronized Pesquisador salvarDadosLattes(final Pesquisador pesquisador) throws RCMSException {

        PesquisadorLattes pesquisadorLattes = null;

        executarComandoLattes(pesquisador);
        pesquisadorLattes = carregarCurriculoLattes(pesquisador.getCodigoLattes());
        cleanUpFiles(pesquisador.getCodigoLattes());

        Pesquisador novoPesquisador = pesquisadorService.buscarTodosDados(pesquisador.getIdUsuario());

        novoPesquisador = new PesquisadorBuilder(pesquisadorLattes, novoPesquisador)
                .endereco(pesquisadorLattes.getEndereco()).formacaoAcademica(pesquisadorLattes.getFormacoes())
                .premios(pesquisadorLattes.getPremios())
                .participacaoEventos(pesquisadorLattes.getParticipacaoEvento())
                .organizacaoEventos(pesquisadorLattes.getOrganizacaoEvento()).orientacoes(pesquisadorLattes)
                .compreensaoIdiomas(pesquisadorLattes.getIdiomas()).areaAtuacoes(pesquisadorLattes.getAreaAtuacao())
                .build();

        normalizarIdiomas(novoPesquisador.getCompreensaoIdiomas());
        salvarHierarquiaGrandeArea(novoPesquisador.getAreaAtuacoes());

        Pesquisador pesquisadorSalvo = pesquisadorService.salvarOuAtualizar(novoPesquisador);

        salvarProjetosPesquisa(pesquisadorLattes, novoPesquisador);
        salvarCitacaoBibliografica(pesquisadorLattes, novoPesquisador);
        salvarProducoes(pesquisadorLattes);

        return pesquisadorSalvo;
    }

    private void executarComandoLattes(final Pesquisador pesquisador) throws RCMSException {

        final String hash = pesquisador.getCodigoLattes();
        final String nome = pesquisador.getNome();

        criarArquivo(nome, hash, getConteudo(hash));
        runScript(hash);
    }

    private void cleanUpFiles(final String codigoLattes) {
        final ArrayList<String> commands = new ArrayList<String>();
        commands.add("rm");
        commands.add("-R");
        commands.add(codigoLattes);

        commands.add("rm");
        commands.add(codigoLattes + ".list");

        commands.add("rm");
        commands.add(codigoLattes + ".config");

        try {
            final Process process = new ProcessBuilder(commands).directory(new File(pastaScriptLates)).inheritIO().start();

            final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;

            while ((line = reader.readLine()) != null) {
                LOGGER.info(line);
            }

            reader.close();

            process.waitFor();
            process.destroy();
        } catch (final Exception e) {
            LOGGER.error(String.format("Erro ao limpar arquivos gerado pelo script lattes, codigo lattes: %s", codigoLattes), e);
        }
    }

    private void runScript(final String hash) {

        try {
            final ArrayList<String> commands = new ArrayList<String>();
            commands.add("/bin/bash");
            commands.add(pastaScriptLates + "runScriptLattes.sh");
            commands.add(hash + ".config");

            LOGGER.info("BEGIN runScript()");
            final Process process = new ProcessBuilder(commands).directory(new File(pastaScriptLates)).inheritIO().start();

            final BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line = null;

            while ((line = reader.readLine()) != null) {
                LOGGER.info(line);
            }

            reader.close();
            process.waitFor();
            process.destroy();

        } catch (final Exception e) {
            LOGGER.error(String.format("Erro ao executar script lattes, codigo lattes: %s", hash), e);
        }
        LOGGER.info("END runScript()");
    }

    private CurriculoLattes carregarCurriculosLattes(final String codigoLattes) throws RCMSException {
        CurriculoLattes curriculoLattes = new CurriculoLattes();
        try {
            final String fileName = pastaScriptLates + File.separator + codigoLattes + File.separator + codigoLattes + SUFFIX_XML;
            final InputStream file = new FileInputStream(fileName);
            final String CurriculoAsString = IOUtils.toString(file, "UTF-8");

            try {
                curriculoLattes = XMLUtils.xmlToObject(CurriculoLattes.class, XMLUtils.stripSpecialChars(CurriculoAsString));
            } catch (final JAXBException e) {
                String msg = String.format("Erro ao converter XML, código lattes: %s", codigoLattes);
                LOGGER.error(msg, e);
                throw new RCMSException(msg, e);
            }
        } catch (final IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ArquivoNaoEncontradoException(pastaScriptLates + arquivoCurriculoLattes);
        }
        return curriculoLattes;
    }

    private void criarArquivo(final String nome, final String path, final String conteudo) throws RCMSException {

        try {
            final File list = new File(pastaScriptLates + path + ".list");

            FileWriter fw = new FileWriter(list.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(path + ", " + nome);
            bw.flush();
            bw.close();

            final File file = new File(pastaScriptLates + path + ".config");

            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(conteudo);

            bw.flush();
            bw.close();
        } catch (final IOException exception) {
            String msg = String.format("Erro ao criar arquivo lattes, nome: %s path: %s ", nome, path);
            LOGGER.error(msg, exception);
            throw new RCMSException(msg, exception);
        }
    }

    private String getConteudo(final String hash) throws ArquivoNaoEncontradoException {

        final ClassLoader classLoader = getClass().getClassLoader();
        File file;
        try {
            file = new File(classLoader.getResource(arquivoConfig).getPath());
            return FileUtils.readFileToString(file).replaceAll("\\{codigoLattes\\}", hash);
        } catch (final FileNotFoundException fileNotFoundException) {
            LOGGER.error(fileNotFoundException.getMessage(), fileNotFoundException);
        } catch (final IOException ioException) {
            LOGGER.error(ioException.getMessage(), ioException);
        }
        throw new ArquivoNaoEncontradoException(arquivoConfig);
    }

    private void salvarHierarquiaGrandeArea(final List<AtuacaoPesquisador> areaAtuacoes) {
        for (final AtuacaoPesquisador atuacaoPesquisador : areaAtuacoes) {
            try {
                final GrandeAreaAtuacao grandeAreaAtuacao = atuacaoPesquisador.getEspecializacao().getSubAreaAtuacao().getAreaAtuacao().getGrandeAreaAtuacao();
                grandeAreaAtuacaoService.saveOrUpdate(grandeAreaAtuacao);
            } catch (final Exception e) {
                LOGGER.error(String.format("Erro ao salvar Hierarquia de área de atuação, especialização: %s ", atuacaoPesquisador.getEspecializacao().getDescricao()), e);
            }
        }
    }

    private void normalizarIdiomas(final List<CompreensaoIdioma> compreensaoIdiomas) {
        for (final CompreensaoIdioma compreensaoIdioma : compreensaoIdiomas) {
            final Idioma idioma = compreensaoIdioma.getCompreensaoIdiomaPK().getIdioma();
            final Idioma idiomaExistente = idiomaService.buscarPorDescricao(idioma.getDescricao());
            if (idiomaExistente != null) {
                final Pesquisador pesquisador = compreensaoIdioma.getCompreensaoIdiomaPK().getPesquisador();
                final CompreensaoIdiomaPK compreensaoIdiomaPK = new CompreensaoIdiomaPK(idiomaExistente, pesquisador);
                compreensaoIdioma.setCompreensaoIdiomaPK(compreensaoIdiomaPK);
            }
        }
    }

    private void salvarProducoes(final PesquisadorLattes pesquisadorLattes) {

        // Artigos em Periódicos
        if (pesquisadorLattes.getArtigosPeriodicos() != null) {
            addArtigosEmPeriodicos(pesquisadorLattes.getArtigosPeriodicos().getArtigos());
        }

        // Livros publicados
        if (pesquisadorLattes.getLivrosPublicados() != null) {
            addLivrosPublicado(pesquisadorLattes.getLivrosPublicados().getLivros());
        }

        // Capítulos de Livros
        if (pesquisadorLattes.getCapitulosLivros() != null) {
            addCapitulosDeLivro(pesquisadorLattes.getCapitulosLivros().getCapitulos());
        }

        // Texto em jornal
        if (pesquisadorLattes.getTextoJornal() != null) {
            addTextosEmJornais(pesquisadorLattes.getTextoJornal().getTextos());
        }

        // Trabalho completo em congresso
        if (pesquisadorLattes.getTrabalhoCompleto() != null) {
            addTrabalhosCompleto(pesquisadorLattes.getTrabalhoCompleto().getTrabalhosCompleto());
        }

        // Resumo expandido em congresso
        if (pesquisadorLattes.getResumoExpandido() != null) {
            addResumosExpandido(pesquisadorLattes.getResumoExpandido().getResumos());
        }

        // Resumo em congresso
        if (pesquisadorLattes.getResumoCongresso() != null) {
            addResumosEmCongressos(pesquisadorLattes.getResumoCongresso().getResumos());
        }

        // Apresentação de Trabalhos
        if (pesquisadorLattes.getApresentacaoTrabalho() != null) {
            addApresentacoesDeTrabalhos(pesquisadorLattes.getApresentacaoTrabalho().getTrabalhos());
        }

        // Outra Produção
        if (pesquisadorLattes.getProducaoBibliografica() != null) {
            addProducoesBibliograficas(pesquisadorLattes.getProducaoBibliografica().getProducoes());
        }

        // Produtos Tecnológicos
        if (pesquisadorLattes.getProdutosTecnologicos() != null) {
            addProdutosTecnologicos(pesquisadorLattes.getProdutosTecnologicos().getProdutos());
        }

        // Processos ou técnicas
        if (pesquisadorLattes.getProcessosOuTecnicas() != null) {
            addProcessosTecnicas(pesquisadorLattes.getProcessosOuTecnicas().getProdutos());
        }

        // Trabalhos técnicos
        if (pesquisadorLattes.getTrabalhosTecnico() != null) {
            addTrabalhosTecnico(pesquisadorLattes.getTrabalhosTecnico().getTrabalhos());
        }

        // Produções Técnicas
        if (pesquisadorLattes.getProducoesTecnica() != null) {
            addOutrasProducoesTecnica(pesquisadorLattes.getProducoesTecnica().getProducoes());
        }
    }

    public void addArtigosEmPeriodicos(final List<ArtigoLattes> artigosPeriodicosLattes) {

        for (ArtigoLattes producao : artigosPeriodicosLattes) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                ArtigoEmPeriodico artigo = new ArtigoEmPeriodico(producao.getTitulo(), producao.getAno(),
                        producao.getVolume(),
                        producao.getPaginas(), producao.getDoi(), producao.getRevista(), producao.getNumero());
                artigo.setAutores(getListaAutores(artigo, producao.getAutores()));
                producaoService.saveOrUpdate(artigo);
            }
        }
    }

    public void addLivrosPublicado(final List<LivroLattes> livrosPublicadosLattes) {

        for (LivroLattes producao : livrosPublicadosLattes) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                LivroPublicado livro = new LivroPublicado(producao.getTitulo(), producao.getAno(),
                        producao.getEdicao(),
                        producao.getVolume(), producao.getPaginas());
                livro.setAutores(getListaAutores(livro, producao.getAutores()));
                producaoService.saveOrUpdate(livro);
            }
        }
    }

    public void addCapitulosDeLivro(final List<CapituloLattes> capitulosLivrosLattes) {

        for (CapituloLattes producao : capitulosLivrosLattes) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                CapituloLivro capitulo = new CapituloLivro(producao.getTitulo(), producao.getAno(),
                        producao.getLivro(),
                        producao.getEdicao(), producao.getEditora(), producao.getVolume(), producao.getPaginas());
                capitulo.setAutores(getListaAutores(capitulo, producao.getAutores()));
                producaoService.saveOrUpdate(capitulo);
            }
        }
    }

    private void addTextosEmJornais(final List<TextoLattes> textos) {

        for (TextoLattes producao : textos) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                TextoEmJornal texto = new TextoEmJornal(producao.getTitulo(), producao.getAno(),
                        producao.getNomeJornal(), producao.getData(), producao.getVolume(), producao.getPaginas());
                texto.setAutores(getListaAutores(texto, producao.getAutores()));
                producaoService.saveOrUpdate(texto);
            }
        }
    }

    private void addTrabalhosCompleto(final List<TrabalhoCompletoLattes> trabalhosCompleto) {

        for (TrabalhoCompletoLattes producao : trabalhosCompleto) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                TrabalhoCompletoCongresso trabalho = new TrabalhoCompletoCongresso(producao.getTitulo(),
                        producao.getAno(), producao.getDoi(),
                        producao.getNomeEvento(), producao.getVolume(), producao.getPaginas());
                trabalho.setAutores(getListaAutores(trabalho, producao.getAutores()));
                producaoService.saveOrUpdate(trabalho);
            }
        }
    }

    private void addResumosExpandido(final List<ResumoExpandidoLattes> resumos) {

        for (ResumoExpandidoLattes producao : resumos) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                ResumoExpandidoCongresso resumo = new ResumoExpandidoCongresso(producao.getTitulo(), producao.getAno(),
                        producao.getDoi(),
                        producao.getNomeEvento(), producao.getVolume(), producao.getPaginas());
                resumo.setAutores(getListaAutores(resumo, producao.getAutores()));
                producaoService.saveOrUpdate(resumo);
            }
        }
    }

    private void addResumosEmCongressos(final List<ResumoLattes> resumos) {

        for (ResumoLattes producao : resumos) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                ResumoCongresso resumo = new ResumoCongresso(producao.getTitulo(), producao.getAno(),
                        producao.getDoi(),
                        producao.getNomeEvento(), producao.getVolume(), producao.getPaginas(), producao.getNumero());
                resumo.setAutores(getListaAutores(resumo, producao.getAutores()));
                producaoService.saveOrUpdate(resumo);
            }
        }
    }

    public void addApresentacoesDeTrabalhos(final List<TrabalhoApresentadoLattes> apresentacaoTrabalhoLattes) {

        for (TrabalhoApresentadoLattes producao : apresentacaoTrabalhoLattes) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                ApresentacaoTrabalho trabalho = new ApresentacaoTrabalho(producao.getTitulo(), producao.getAno(),
                        producao.getNatureza());
                trabalho.setAutores(getListaAutores(trabalho, producao.getAutores()));
                producaoService.saveOrUpdate(trabalho);
            }
        }
    }

    private void addProducoesBibliograficas(final List<OutraProducaoLattes> producoes) {

        for (OutraProducaoLattes producao : producoes) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                OutraProducaoBibliografica producaoBibliografica = new OutraProducaoBibliografica(producao.getTitulo(),
                        producao.getAno(), producao.getNatureza());
                producaoBibliografica.setAutores(getListaAutores(producaoBibliografica, producao.getAutores()));
                producaoService.saveOrUpdate(producaoBibliografica);
            }
        }
    }

    private void addProdutosTecnologicos(final List<ProdutoLattes> trabalhos) {

        for (ProdutoLattes producao : trabalhos) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                ProdutoTecnologico produto = new ProdutoTecnologico(producao.getTitulo(), producao.getAno());
                produto.setAutores(getListaAutores(produto, producao.getAutores()));
                producaoService.saveOrUpdate(produto);
            }
        }
    }

    private void addTrabalhosTecnico(final List<TrabalhoLattes> trabalhos) {

        for (TrabalhoLattes producao : trabalhos) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                TrabalhoTecnico trabalho = new TrabalhoTecnico(producao.getTitulo(), producao.getAno());
                trabalho.setAutores(getListaAutores(trabalho, producao.getAutores()));
                producaoService.saveOrUpdate(trabalho);
            }
        }
    }

    private void addOutrasProducoesTecnica(final List<OutraProducaoTecnicaLattes> producoes) {

        for (OutraProducaoTecnicaLattes producao : producoes) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                OutraProducaoTecnica producaoTecnica = new OutraProducaoTecnica(producao.getTitulo(),
                        producao.getAno(), producao.getNatureza());
                producaoTecnica.setAutores(getListaAutores(producaoTecnica, producao.getAutores()));
            producaoService.saveOrUpdate(producaoTecnica);
            }
        }
    }

    private void addProcessosTecnicas(final List<ProdutoProcessoTecnicaLattes> producoes) {

        for (ProdutoProcessoTecnicaLattes producao : producoes) {
            if (!isProducao(producao.getTitulo(), producao.getAno())) {
                ProcessoOuTecnica processoTecnica = new ProcessoOuTecnica(producao.getTitulo(), producao.getAno(),
                        producao.getNatureza());
                processoTecnica.setAutores(getListaAutores(processoTecnica, producao.getAutores()));
                producaoService.saveOrUpdate(processoTecnica);
            }
        }
    }

    public Boolean isProducao(final String titulo, final Integer ano) {

        return producaoService.exists(titulo, ano);
    }

    public List<AutorProducao> getListaAutores(final Producao producao, final String autores) {

        List<AutorProducao> listaCitacoes = new ArrayList<AutorProducao>();

        String[] citacoes = autores.split(";");
        for (int i=0; i<citacoes.length; i++) {
            if (!citacaoBibliograficaService.exists(citacoes[i].trim())) {
                CitacaoBibliografica novaCitacao = new CitacaoBibliografica(null, citacoes[i].trim());
                citacaoBibliograficaService.salvar(novaCitacao);
            }

            listaCitacoes.add(new AutorProducao(producao, citacaoBibliograficaService.buscarPorNomeCitacao(citacoes[i]
                    .trim()), i + 1));
        }

        return listaCitacoes;
    }

    public void salvarCitacaoBibliografica(final PesquisadorLattes pesquisadorLattes, final Pesquisador pesquisador) {

        String[] citacoes = pesquisadorLattes.getIdentificacao().getNomeCitacaoBibliografica().split(";");
        for (int i = 0; i < citacoes.length; i++) {
            if (!citacaoBibliograficaService.exists(citacoes[i].trim())) {
                CitacaoBibliografica novaCitacao = new CitacaoBibliografica(pesquisador, citacoes[i].trim());
                citacaoBibliograficaService.salvar(novaCitacao);
            }
        }
    }

    public void salvarProjetosPesquisa(final PesquisadorLattes pesquisadorLattes, final Pesquisador pesquisador) {

        if (pesquisadorLattes.getProjetosPesquisa() != null) {
            for (ProjetoLattes projetoLattes : pesquisadorLattes.getProjetosPesquisa().getProjetos()) {
                if (!isProjetoPesquisa(projetoLattes.getNome())) {
                    ProjetoPesquisa projetoPesquisa = new ProjetoPesquisa(projetoLattes.getNome(),
                            projetoLattes.getDescricao(), projetoLattes.getAnoInicio(), projetoLattes.getAnoConclusao());
                    projetoPesquisa.adicionarPesquisador(pesquisador);
                    projetoPesquisaService.salvarOuAtualizar(projetoPesquisa);
                }
            }
        }
    }

    public Boolean isProjetoPesquisa(final String nome) {

        return projetoPesquisaService.exists(nome);
    }
}
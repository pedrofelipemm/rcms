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
import br.ufscar.rcms.modelo.entidades.ApresentacaoTrabalho;
import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.AtuacaoPesquisador;
import br.ufscar.rcms.modelo.entidades.CapituloLivro;
import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;
import br.ufscar.rcms.modelo.entidades.CompreensaoIdioma;
import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.modelo.entidades.OutraProducaoBibliografica;
import br.ufscar.rcms.modelo.entidades.OutraProducaoTecnica;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.ResumoCongresso;
import br.ufscar.rcms.modelo.entidades.ResumoExpandidoCongresso;
import br.ufscar.rcms.modelo.entidades.TextoEmJornal;
import br.ufscar.rcms.modelo.entidades.TrabalhoCompletoCongresso;
import br.ufscar.rcms.modelo.entidades.TrabalhoTecnico;
import br.ufscar.rcms.modelo.entidades.pk.CompreensaoIdiomaPK;
import br.ufscar.rcms.modelo.lattes.ArtigoLattes;
import br.ufscar.rcms.modelo.lattes.CapituloLattes;
import br.ufscar.rcms.modelo.lattes.CurriculoLattes;
import br.ufscar.rcms.modelo.lattes.OutraProducaoLattes;
import br.ufscar.rcms.modelo.lattes.OutraProducaoTecnicaLattes;
import br.ufscar.rcms.modelo.lattes.PesquisadorLattes;
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
import br.ufscar.rcms.servico.exception.ArquivoNaoEncontradoException;
import br.ufscar.rcms.servico.exception.CurriculoLattesNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;
import br.ufscar.rcms.util.XMLUtils;


@Service("lattesService")
@Transactional
public class LattesServiceImpl implements LattesService {

    private static final long serialVersionUID = 4593268685421323315L;
    private static final Logger LOGGER = LoggerFactory.getLogger(LattesServiceImpl.class);
    private static final String SUFFIX_XML = "-database.xml";

    @Autowired
    private PesquisadorService pesquisadorService;

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
    public PesquisadorLattes carregarCurriculoLattes(final String codigoLattes) throws CurriculoLattesNaoEncontradoException,
            ArquivoNaoEncontradoException {

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
                .citacaoBibliografica(pesquisadorLattes.getIdentificacao()).premios(pesquisadorLattes.getPremios())
                .participacaoEventos(pesquisadorLattes.getParticipacaoEvento())
                .organizacaoEventos(pesquisadorLattes.getOrganizacaoEvento())
                .projetosPesquisa(pesquisadorLattes.getProjetosPesquisa()).orientacoes(pesquisadorLattes)
                .compreensaoIdiomas(pesquisadorLattes.getIdiomas()).areaAtuacoes(pesquisadorLattes.getAreaAtuacao())
                .build();

        normalizarIdiomas(novoPesquisador.getCompreensaoIdiomas());
        salvarHierarquiaGrandeArea(novoPesquisador.getAreaAtuacoes());

        Pesquisador pesquisadorSalvo = pesquisadorService.salvarOuAtualizar(novoPesquisador);

        salvarProducoes(pesquisadorLattes);

        return pesquisadorSalvo;
    }

    @Override
    public void executarComandoLattes(final Pesquisador pesquisador) {

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

    private CurriculoLattes carregarCurriculosLattes(final String codigoLattes) throws ArquivoNaoEncontradoException {
        CurriculoLattes curriculoLattes = new CurriculoLattes();
        try {
            final String fileName = pastaScriptLates + File.separator + codigoLattes + File.separator + codigoLattes + SUFFIX_XML;
            final InputStream file = new FileInputStream(fileName);
            final String CurriculoAsString = IOUtils.toString(file, "UTF-8");

            try {
                curriculoLattes = XMLUtils.xmlToObject(CurriculoLattes.class, XMLUtils.stripSpecialChars(CurriculoAsString));
            } catch (final JAXBException e) {
                LOGGER.error("Erro ao converter XML, código lattes: %s", codigoLattes, e);
                // TODO PEDRO
                throw new RuntimeException(e);
            }
        } catch (final IOException e) {
            LOGGER.error(e.getMessage(), e);
            throw new ArquivoNaoEncontradoException(pastaScriptLates + arquivoCurriculoLattes);
        }
        return curriculoLattes;
    }

    private void criarArquivo(final String nome, final String path, final String conteudo) {

        try {
            final File list = new File(pastaScriptLates + path + ".list");

            // if file doesnt exists, then create it
            if (!list.exists()) {
                list.createNewFile();
            }

            FileWriter fw = new FileWriter(list.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(path + ", " + nome);
            bw.flush();
            bw.close();

            final File file = new File(pastaScriptLates + path + ".config");

            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(conteudo);

            bw.flush();
            bw.close();
        } catch (final IOException exception) {
            LOGGER.error(String.format("Erro ao criar arquivo lattes, nome: %s path: %s ", nome, path), exception);
            throw new RuntimeException(exception);
        }
    }

    private String getConteudo(final String hash) {

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
        // TODO PEDRO
        return null;
    }

    // TODO PEDRO - DUPLICANDO ITENS
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
        if (pesquisadorLattes.getArtigosPeriodicos() != null)
            addArtigoEmPeriodico(pesquisadorLattes.getArtigosPeriodicos().getArtigos());

        // Capítulos de Livros
        if (pesquisadorLattes.getCapitulosLivros() != null)
            addCapituloLivro(pesquisadorLattes.getCapitulosLivros().getCapitulos());

        // Texto em jornal
        if (pesquisadorLattes.getTextoJornal() != null)
            addTextoJornal(pesquisadorLattes.getTextoJornal().getTextos());

        // Trabalho completo em congresso
        if (pesquisadorLattes.getTrabalhoCompleto() != null)
            addTrabalhoCompleto(pesquisadorLattes.getTrabalhoCompleto().getTrabalhosCompleto());

        // Resumo expandido em congresso
        if (pesquisadorLattes.getResumoExpandido() != null)
            addResumoExpandido(pesquisadorLattes.getResumoExpandido().getResumos());

        // Resumo em congresso
        if (pesquisadorLattes.getResumoCongresso() != null)
            addResumoCongresso(pesquisadorLattes.getResumoCongresso().getResumos());

        // Resumo em congresso
        if (pesquisadorLattes.getResumoCongresso() != null)
            addResumoCongresso(pesquisadorLattes.getResumoCongresso().getResumos());

        // Apresentação de Trabalhos
        if (pesquisadorLattes.getApresentacaoTrabalho() != null)
            addApresentacaoTrabalho(pesquisadorLattes.getApresentacaoTrabalho().getTrabalhos());

        // Outra Produção
        if (pesquisadorLattes.getProducaoBibliografica() != null)
            addProducaoBibliografica(pesquisadorLattes.getProducaoBibliografica().getProducoes());

        // Trabalhos técnicos
        if (pesquisadorLattes.getTrabalhosTecnico() != null)
            addTrabalhosTecnico(pesquisadorLattes.getTrabalhosTecnico().getTrabalhos());

        // Produções Técnicas
        if (pesquisadorLattes.getProducoesTecnica() != null)
            addOutrasProducoesTecnica(pesquisadorLattes.getProducoesTecnica().getProducoes());
    }

    public void addArtigoEmPeriodico(final List<ArtigoLattes> artigosPeriodicosLattes) {

        for (ArtigoLattes producao : artigosPeriodicosLattes) {
            ArtigoEmPeriodico artigo = new ArtigoEmPeriodico(producao.getTitulo(),
                    getListaCitacoesBibliografica(producao
                    .getAutores()), producao.getAno(), producao.getVolume(), producao.getPaginas(), producao.getDoi(),
                    producao.getRevista(), producao.getNumero());
            producaoService.saveOrUpdate(artigo);
        }
    }

    public void addCapituloLivro(final List<CapituloLattes> capitulosLivrosLattes) {

        for (CapituloLattes producao : capitulosLivrosLattes) {
            CapituloLivro artigo = new CapituloLivro(producao.getTitulo(),
                    getListaCitacoesBibliografica(producao.getAutores()), producao.getAno(), producao.getLivro(),
                    producao.getEdicao(), producao.getEditora());
            producaoService.saveOrUpdate(artigo);
        }
    }

    private void addTextoJornal(List<TextoLattes> textos) {

        for (TextoLattes producao : textos) {
            TextoEmJornal texto = new TextoEmJornal(producao.getTitulo(),
                    getListaCitacoesBibliografica(producao.getAutores()), producao.getAno(), producao.getNomeJornal(),
                    producao.getData(), producao.getVolume(), producao.getPaginas());
            producaoService.saveOrUpdate(texto);
        }
    }

    private void addTrabalhoCompleto(List<TrabalhoCompletoLattes> trabalhosCompleto) {

        for (TrabalhoCompletoLattes producao : trabalhosCompleto) {
            TrabalhoCompletoCongresso trabalho = new TrabalhoCompletoCongresso(producao.getTitulo(),
                    getListaCitacoesBibliografica(producao.getAutores()), producao.getAno(), producao.getDoi(),
                    producao.getNomeEvento(), producao.getVolume(), producao.getPaginas());
            producaoService.saveOrUpdate(trabalho);
        }
    }

    private void addResumoExpandido(List<ResumoExpandidoLattes> resumos) {

        for (ResumoExpandidoLattes producao : resumos) {
            ResumoExpandidoCongresso resumo = new ResumoExpandidoCongresso(producao.getTitulo(),
                    getListaCitacoesBibliografica(producao.getAutores()), producao.getAno(), producao.getDoi(),
                    producao.getNomeEvento(), producao.getVolume(), producao.getPaginas());
            producaoService.saveOrUpdate(resumo);
        }
    }

    private void addResumoCongresso(List<ResumoLattes> resumos) {

        for (ResumoLattes producao : resumos) {
            ResumoCongresso resumo = new ResumoCongresso(producao.getTitulo(),
                    getListaCitacoesBibliografica(producao.getAutores()), producao.getAno(), producao.getDoi(),
                    producao.getNomeEvento(), producao.getVolume(), producao.getPaginas(), producao.getNumero());
            producaoService.saveOrUpdate(resumo);
        }
    }

    public void addApresentacaoTrabalho(final List<TrabalhoApresentadoLattes> apresentacaoTrabalhoLattes) {

        for (TrabalhoApresentadoLattes producao : apresentacaoTrabalhoLattes) {
            ApresentacaoTrabalho trabalho = new ApresentacaoTrabalho(producao.getTitulo(),
                    getListaCitacoesBibliografica(producao.getAutores()), producao.getAno(), producao.getNatureza());
            producaoService.saveOrUpdate(trabalho);
        }
    }

    private void addProducaoBibliografica(List<OutraProducaoLattes> producoes) {

        for (OutraProducaoLattes producao : producoes) {
            OutraProducaoBibliografica producaoBibliografica = new OutraProducaoBibliografica(producao.getTitulo(),
                    getListaCitacoesBibliografica(producao.getAutores()), producao.getAno(), producao.getNatureza());
            producaoService.saveOrUpdate(producaoBibliografica);
        }
    }

    private void addTrabalhosTecnico(List<TrabalhoLattes> trabalhos) {

        for (TrabalhoLattes producao : trabalhos) {
            TrabalhoTecnico trabalho = new TrabalhoTecnico(producao.getTitulo(),
                    getListaCitacoesBibliografica(producao.getAutores()), producao.getAno());
            producaoService.saveOrUpdate(trabalho);
        }
    }

    private void addOutrasProducoesTecnica(List<OutraProducaoTecnicaLattes> producoes) {

        for (OutraProducaoTecnicaLattes producao : producoes) {
            OutraProducaoTecnica producaoTecnica = new OutraProducaoTecnica(producao.getTitulo(),
                    getListaCitacoesBibliografica(producao.getAutores()), producao.getAno(), producao.getNatureza());
            producaoService.saveOrUpdate(producaoTecnica);
        }
    }

    public List<CitacaoBibliografica> getListaCitacoesBibliografica(final String autores) {

        List<CitacaoBibliografica> listaCitacoes = new ArrayList<CitacaoBibliografica>();

        String[] citacoes = autores.split(";");
        for (String nomeCitacao : citacoes) {
        	List<CitacaoBibliografica> citacoesExistentes = citacaoBibliograficaService
                    .buscarPorNomeCitacao(nomeCitacao.trim());
            if (citacoesExistentes != null) {
                listaCitacoes.addAll(citacoesExistentes);
            } else {
                CitacaoBibliografica novaCitacao = new CitacaoBibliografica(null, nomeCitacao.trim());
                citacaoBibliograficaService.salvar(novaCitacao);
                listaCitacoes.add(novaCitacao);
            }
        }

        return listaCitacoes;
    }

}
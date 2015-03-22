package br.ufscar.rcms.servico.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.rcms.builder.PesquisadorBuilder;
import br.ufscar.rcms.modelo.entidades.AtuacaoPesquisador;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.lattes.CurriculoLattes;
import br.ufscar.rcms.modelo.lattes.PesquisadorLattes;
import br.ufscar.rcms.servico.GrandeAreaAtuacaoService;
import br.ufscar.rcms.servico.LattesService;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.exception.CurriculoLattesNaoEncontradoException;
import br.ufscar.rcms.util.XMLUtils;

@Service("lattesService")
@Transactional
@PropertySource("classpath:application.properties")
public class LattesServiceImpl implements LattesService {

    private static final long serialVersionUID = 4593268685421323315L;
    private static final Logger LOGGER = LoggerFactory.getLogger(LattesServiceImpl.class);

    @Autowired
    private PesquisadorService pesquisadorService;

    @Autowired
    private GrandeAreaAtuacaoService grandeAreaAtuacaoService;

    @Value("${pasta.script.lattes}")
    private String pastaScriptLates;

    @Value("${arquivo.config.lattes}")
    private String arquivoConfig;

    @Value("${arquivo.curriculos.lattes}")
    private String arquivoCurriculoLattes;

    @Override
    public PesquisadorLattes carregarCurriculoLattes(String codigoLattes) throws CurriculoLattesNaoEncontradoException {

        CurriculoLattes curriculos = carregarCurriculosLattes();
        for (PesquisadorLattes pesquisador : curriculos.getPesquisadores()) {
            if (pesquisador.getCodigoLattes().equals(codigoLattes)) {
                return pesquisador;
            }
        }

        throw new CurriculoLattesNaoEncontradoException(codigoLattes);
    }

    @Override
    public Pesquisador salvarDadosLattes(Pesquisador pesquisador) throws CurriculoLattesNaoEncontradoException {

        PesquisadorLattes pesquisadorLattes = null;

        pesquisadorLattes = carregarCurriculoLattes(pesquisador.getCodigoLattes());
        Pesquisador novoPesquisador = pesquisadorService.buscarTodosDados(pesquisador.getIdUsuario());

        novoPesquisador = new PesquisadorBuilder(pesquisadorLattes, novoPesquisador)
                .endereco(pesquisadorLattes.getEndereco()).formacaoAcademica(pesquisadorLattes.getFormacoes())
                .citacaoBibliografica(pesquisadorLattes.getIdentificacao()).premios(pesquisadorLattes.getPremios())
                .participacaoEventos(pesquisadorLattes.getParticipacaoEvento())
                .organizacaoEventos(pesquisadorLattes.getOrganizacaoEvento())
                .projetosPesquisa(pesquisadorLattes.getProjetosPesquisa()).orientacoes(pesquisadorLattes)
                .compreensaoIdiomas(pesquisadorLattes.getIdiomas()).areaAtuacoes(pesquisadorLattes.getAreaAtuacao())
                .build();

        salvarHierarquiaGrandeArea(novoPesquisador.getAreaAtuacoes());

        return pesquisadorService.salvarOuAtualizar(novoPesquisador);
    }

    @Override
    public void executarComandoLattes(Pesquisador pesquisador) throws IOException {
        String hash = pesquisador.getCodigoLattes();
        String nome = pesquisador.getNome();
        criarArquivo(nome, hash, getConteudo(hash));

        try {
            new Thread(new RunScriptLattes(hash, pastaScriptLates)).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CurriculoLattes carregarCurriculosLattes() {
        CurriculoLattes curriculoLattes = new CurriculoLattes();
        try {
            InputStream file = new FileInputStream(pastaScriptLates + arquivoCurriculoLattes);
            try {
                curriculoLattes = XMLUtils.xmlToObject(CurriculoLattes.class, IOUtils.toString(file, "UTF-8"));
            } catch (JAXBException e) {
                LOGGER.error(e.getMessage(), e);
            }
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return curriculoLattes;
    }

    private void criarArquivo(String nome, String path, String conteudo) throws IOException {
        File list = new File(pastaScriptLates + path + ".list");

        // if file doesnt exists, then create it
        if (!list.exists()) {
            list.createNewFile();
        }

        FileWriter fw = new FileWriter(list.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(path + ", " + nome);
        bw.flush();
        bw.close();

        File file = new File(pastaScriptLates + path + ".config");

        // if file doesnt exists, then create it
        if (!file.exists()) {
            file.createNewFile();
        }

        fw = new FileWriter(file.getAbsoluteFile());
        bw = new BufferedWriter(fw);
        bw.write(conteudo);
        bw.flush();
        bw.close();
    }

    private String getConteudo(String hash) {

        StringBuilder result = new StringBuilder("");
        result.append("global-arquivo_de_entrada  = " + hash + ".list\n");
        result.append("global-diretorio_de_saida  = " + hash + "/\n");

        // Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file;
        try {
            file = new File(classLoader.getResource(arquivoConfig).getPath());
            result.append(FileUtils.readFileToString(file));
        } catch (FileNotFoundException fileNotFoundException) {
            LOGGER.error(fileNotFoundException.getMessage(), fileNotFoundException);
        } catch (IOException ioException) {
            LOGGER.error(ioException.getMessage(), ioException);
        }

        return result.toString();
    }

    // TODO PEDRO - DUPLICANDO ITENS
    private void salvarHierarquiaGrandeArea(List<AtuacaoPesquisador> areaAtuacoes) {
        for (AtuacaoPesquisador atuacaoPesquisador : areaAtuacoes) {
            grandeAreaAtuacaoService.salvar(atuacaoPesquisador.getEspecializacao().getSubAreaAtuacao().getAreaAtuacao().getGrandeAreaAtuacao());
        }
    }
}
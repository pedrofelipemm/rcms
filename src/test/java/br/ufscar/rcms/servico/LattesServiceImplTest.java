package br.ufscar.rcms.servico;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.modelo.lattes.ApresentacaoTrabalhoLattes;
import br.ufscar.rcms.modelo.lattes.AreaAtuacaoLattes;
import br.ufscar.rcms.modelo.lattes.ArtigoLattes;
import br.ufscar.rcms.modelo.lattes.ArtigosPeriodicosLattes;
import br.ufscar.rcms.modelo.lattes.CapituloLattes;
import br.ufscar.rcms.modelo.lattes.CapitulosLivrosLattes;
import br.ufscar.rcms.modelo.lattes.ColaboradoresLattes;
import br.ufscar.rcms.modelo.lattes.DissertacaoLattes;
import br.ufscar.rcms.modelo.lattes.EnderecoLattes;
import br.ufscar.rcms.modelo.lattes.EventoLatttes;
import br.ufscar.rcms.modelo.lattes.FormacaoLattes;
import br.ufscar.rcms.modelo.lattes.FormacoesAcademicaLattes;
import br.ufscar.rcms.modelo.lattes.IdentificacaoLattes;
import br.ufscar.rcms.modelo.lattes.IdiomaLattes;
import br.ufscar.rcms.modelo.lattes.IdiomasLattes;
import br.ufscar.rcms.modelo.lattes.IniciacaoCientificaLattes;
import br.ufscar.rcms.modelo.lattes.OrganizacaoEventoLattes;
import br.ufscar.rcms.modelo.lattes.OrientacaoDoutoradoLattes;
import br.ufscar.rcms.modelo.lattes.OrientacaoIniciacaoCientificaLattes;
import br.ufscar.rcms.modelo.lattes.OrientacaoMestradoLattes;
import br.ufscar.rcms.modelo.lattes.OrientacaoOutraLattes;
import br.ufscar.rcms.modelo.lattes.OrientacaoOutrosTiposConcluidoLattes;
import br.ufscar.rcms.modelo.lattes.OrientacaoTCCLattes;
import br.ufscar.rcms.modelo.lattes.ParticipacaoEventoLattes;
import br.ufscar.rcms.modelo.lattes.PesquisadorLattes;
import br.ufscar.rcms.modelo.lattes.ProducaoBibliograficaLattes;
import br.ufscar.rcms.modelo.lattes.ProducaoLattes;
import br.ufscar.rcms.modelo.lattes.ProjetetosPesquisaLattes;
import br.ufscar.rcms.modelo.lattes.ProjetoLattes;
import br.ufscar.rcms.modelo.lattes.ResumoCongressoLattes;
import br.ufscar.rcms.modelo.lattes.ResumoExpandidoCongressoLattes;
import br.ufscar.rcms.modelo.lattes.ResumoExpandidoLattes;
import br.ufscar.rcms.modelo.lattes.ResumoLattes;
import br.ufscar.rcms.modelo.lattes.TCCLattes;
import br.ufscar.rcms.modelo.lattes.TeseLattes;
import br.ufscar.rcms.modelo.lattes.TextoJornalLattes;
import br.ufscar.rcms.modelo.lattes.TextoLattes;
import br.ufscar.rcms.modelo.lattes.TrabalhoApresentadoLattes;
import br.ufscar.rcms.modelo.lattes.TrabalhoCompletoCongressoLattes;
import br.ufscar.rcms.modelo.lattes.TrabalhoCompletoLattes;
import br.ufscar.rcms.modelo.lattes.TrabalhoLattes;
import br.ufscar.rcms.modelo.lattes.TrabalhosTecnicoLattes;

public class LattesServiceImplTest extends AbstractServiceTestBase {

    @Autowired
    private LattesService lattesService;

    @Test
    public void carregarCurriculoLattesTest() throws Exception {

        String codigoLattesPrado = "2668568143800755";
        PesquisadorLattes pesquisadorLattes = lattesService.carregarCurriculoLattes(codigoLattesPrado);

        assertPesquisador(pesquisadorLattes);
    }

    // private void assertCurriloLattes(CurriculoLattes curriculoLattes) {
    // assertPesquisadores(curriculoLattes.getPesquisadores());
    // }
    //
    // private void assertPesquisadores(List<PesquisadorLattes> pesquisadores) {
    //
    // assertEquals(1, pesquisadores.size());
    // assertPesquisador(pesquisadores.get(0));
    // }

    private void assertPesquisador(PesquisadorLattes pesquisador) {

        assertEquals("2668568143800755", pesquisador.getCodigoLattes());
        assertIdentificacao(pesquisador.getIdentificacao());
        assertEndereco(pesquisador.getEndereco());
        assertFormacoes(pesquisador.getFormacoes());
        assertIdiomas(pesquisador.getIdiomas());
        assertProjetosPesquisa(pesquisador.getProjetosPesquisa());
        assertAreaAtuacao(pesquisador.getAreaAtuacao());
        assertColaboradores(pesquisador.getColaboradores());
        assertArtigosPeriodicos(pesquisador.getArtigosPeriodicos());
        assertCapitulosLivros(pesquisador.getCapitulosLivros());
        assertTextoJornal(pesquisador.getTextoJornal());
        assertTrabalhoCompleto(pesquisador.getTrabalhoCompleto());
        assertResumoExpandido(pesquisador.getResumoExpandido());
        assertResumoCongresso(pesquisador.getResumoCongresso());
        assertProducaoBibliografica(pesquisador.getProducaoBibliografica());
        assertTrabalhoTecnico(pesquisador.getTrabalhosTecnico());
        assertApresentacaoTrabalho(pesquisador.getApresentacaoTrabalho());
        assertProducoesTecnica(pesquisador.getProducoesTecnica());
        assertOrientacaoDoutoradoAndamento(pesquisador.getOrientacaoDoutoradoAndamento());
        assertOrientacaoDoutoradoConcluido(pesquisador.getOrientacaoDoutoradoConcluido());
        assertOrientacaoMestradoAndamento(pesquisador.getOrientacaoMestradoAndamento());
        assertOrientacaoMestradoConcluido(pesquisador.getOrientacaoMestradoConcluido());
        assertOrientacaoTCCConcluido(pesquisador.getOrientacaoTCCConcluido());
        assertOrientacaoIniciacaoCientifica(pesquisador.getOrientacaoIniciacaoCientificaConcluido());
        assertOrientacaoOutrosTipoConcluido(pesquisador.getOrientacaoOutrosTipoConcluido());
        assertParticipacaoEvento(pesquisador.getParticipacaoEvento());
        assertOrganizacaoEvento(pesquisador.getOrganizacaoEvento());
    }

    private void assertOrganizacaoEvento(OrganizacaoEventoLattes organizacaoEvento) {

        assertEquals(2, organizacaoEvento.getEventos().size());
        assertEventoOrg(organizacaoEvento.getEventos().get(0));
    }

    private void assertEventoOrg(EventoLatttes eventoLatttes) {

        assertEquals(Integer.valueOf(2007), eventoLatttes.getAno());
        assertEquals("Congresso", eventoLatttes.getNatureza());
        assertEquals("Sessão de Ferramentas do Simpósio Brasileiro de Componentes, Arquiteturas e Reutilização de Software (SBCARS 2007)",
                eventoLatttes.getTitulo());
    }

    private void assertParticipacaoEvento(ParticipacaoEventoLattes participacaoEvento) {

        assertEquals(29, participacaoEvento.getEventos().size());
        assertEvento(participacaoEvento.getEventos().get(0));
    }

    private void assertEvento(EventoLatttes eventoLatttes) {

        assertEquals(Integer.valueOf(2013), eventoLatttes.getAno());
        assertEquals("XXVII Congresso Brasileiro de Software: Teoria e Prática. 2013. (Congresso).", eventoLatttes.getTitulo());
    }

    private void assertOrientacaoOutrosTipoConcluido(OrientacaoOutrosTiposConcluidoLattes orientacaoOutrosTipoConcluido) {

        assertEquals(46, orientacaoOutrosTipoConcluido.getOrientacaoOutra().size());
        assertOrientacaoOutroTipo(orientacaoOutrosTipoConcluido.getOrientacaoOutra().get(0));
    }

    private void assertOrientacaoOutroTipo(OrientacaoOutraLattes orientacaoOutraLattes) {

        assertEquals(Integer.valueOf(2008), orientacaoOutraLattes.getAno());
        assertEquals("", orientacaoOutraLattes.getAgenciaFomento());
        assertEquals("(Engenharia de Computação) - Universidade Federal de São Carlos", orientacaoOutraLattes.getInstituicao());
        assertEquals("Fábio Calixto Mattar", orientacaoOutraLattes.getNomeAluno());
        assertEquals("Orientador", orientacaoOutraLattes.getTipoOrientacao());
        assertEquals("Framework da BMF", orientacaoOutraLattes.getTituloTrabalho());
    }

    private void assertOrientacaoIniciacaoCientifica( OrientacaoIniciacaoCientificaLattes orientacaoIniciacaoCientificaConcluido) {

        assertEquals(11, orientacaoIniciacaoCientificaConcluido.getIniciacaoCientifica().size());
        assertOrientacaoCientifica(orientacaoIniciacaoCientificaConcluido.getIniciacaoCientifica().get(0));
    }

    private void assertOrientacaoCientifica(IniciacaoCientificaLattes iniciacaoCientificaLattes) {

        assertEquals(Integer.valueOf(2010), iniciacaoCientificaLattes.getAno());
        assertEquals("Conselho Nacional de Desenvolvimento Científico e Tecnológico", iniciacaoCientificaLattes.getAgenciaFomento());
        assertEquals("(Graduando em Bacharelado em Ciência da Computação) - Universidade Federal de São Carlos", iniciacaoCientificaLattes.getInstituicao());
        assertEquals("Carolina Ribeiro Nhoque", iniciacaoCientificaLattes.getNomeAluno());
        assertEquals("Orientador", iniciacaoCientificaLattes.getTipoOrientacao());
        assertEquals("Construção de uma Ferramenta CASE para Modelagem de Aplicações Baseadas em Padrões de Software e Frameworks",
                iniciacaoCientificaLattes.getTituloTrabalho());
    }

    private void assertOrientacaoTCCConcluido(OrientacaoTCCLattes orientacaoTCCConcluido) {

        assertEquals(3, orientacaoTCCConcluido.getTccs().size());
        assertTCCConcluido(orientacaoTCCConcluido.getTccs().get(0));
    }

    private void assertTCCConcluido(TCCLattes tccLattes) {

        assertEquals(Integer.valueOf(2004), tccLattes.getAno());
        assertEquals("", tccLattes.getAgenciaFomento());
        assertEquals("(Graduação em Bacharelado em Ciência da Computação) - Universidade Federal de São Carlos", tccLattes.getInstituicao());
        assertEquals("Gustavo Rodrigues Paludetto", tccLattes.getNomeAluno());
        assertEquals("Orientador", tccLattes.getTipoOrientacao());
        assertEquals("Desenvolvimento de um componente para domínio de formulários Web, usando PHP", tccLattes.getTituloTrabalho());
    }

    private void assertOrientacaoMestradoConcluido(OrientacaoMestradoLattes orientacaoMestrado) {

        assertEquals(34, orientacaoMestrado.getDissertacoes().size());
        assertDissertacaoConcluido(orientacaoMestrado.getDissertacoes().get(0));
    }

    private void assertDissertacaoConcluido(DissertacaoLattes dissertacaoLattes) {

        assertEquals(Integer.valueOf(2013), dissertacaoLattes.getAno());
        assertEquals("Coordenação de Aperfeiçoamento de Pessoal de Nível Superior", dissertacaoLattes.getAgenciaFomento());
        assertEquals("Dissertação (Mestrado em Programa de Pós-Graduação em Ciência da Computação) - Universidade Federal de São Carlos",
                dissertacaoLattes.getInstituicao());
        assertEquals("Luiz Pacini", dissertacaoLattes.getNomeAluno());
        assertEquals("Orientador", dissertacaoLattes.getTipoOrientacao());
        assertEquals("Uma abordagem para extração de processos de negócio de sistemas legado descritos em BPMN",
                dissertacaoLattes.getTituloTrabalho());
    }

    private void assertOrientacaoMestradoAndamento(OrientacaoMestradoLattes orientacaoMestrado) {

        assertEquals(2, orientacaoMestrado.getDissertacoes().size());
        assertDissertacao(orientacaoMestrado.getDissertacoes().get(0));
    }

    private void assertDissertacao(DissertacaoLattes dissertacaoLattes) {

        assertEquals(Integer.valueOf(2013), dissertacaoLattes.getAno());
        assertEquals("Coordenação de Aperfeiçoamento de Pessoal de Nível Superior", dissertacaoLattes.getAgenciaFomento());
        assertEquals("Dissertação (Mestrado em Programa de Pós-Graduação em Ciência da Computação) - Universidade Federal de São Carlos",
                dissertacaoLattes.getInstituicao());
        assertEquals("Augusto Rodrigues Ferreira Chaves", dissertacaoLattes.getNomeAluno());
        assertEquals("Orientador", dissertacaoLattes.getTipoOrientacao());
        assertEquals("Em definição", dissertacaoLattes.getTituloTrabalho());
    }

    private void assertOrientacaoDoutoradoConcluido(OrientacaoDoutoradoLattes orientacaoDoutorado) {

        assertEquals(2, orientacaoDoutorado.getTeses().size());
        assertTeseConcluido(orientacaoDoutorado.getTeses().get(0));
    }

    private void assertTeseConcluido(TeseLattes teseLattes) {

        assertEquals(Integer.valueOf(2012), teseLattes.getAno());
        assertEquals("", teseLattes.getAgenciaFomento());
        assertEquals("Tese (Doutorado em Ciência da Computação) - Universidade Federal de São Carlos", teseLattes.getInstituicao());
        assertEquals("Ricardo Aparecido Perez de Almeida", teseLattes.getNomeAluno());
        assertEquals("Orientador", teseLattes.getTipoOrientacao());
        assertEquals("Uma Arquitetura de Fornecimento de Serviços para a Computação Móvel", teseLattes.getTituloTrabalho());
    }

    private void assertOrientacaoDoutoradoAndamento(OrientacaoDoutoradoLattes orientacaoDoutorado) {

        assertEquals(2, orientacaoDoutorado.getTeses().size());
        assertTese(orientacaoDoutorado.getTeses().get(0));
    }

    private void assertTese(TeseLattes teseLattes) {

        assertEquals(Integer.valueOf(2011), teseLattes.getAno());
        assertEquals("Coordenação de Aperfeiçoamento de Pessoal de Nível Superior", teseLattes.getAgenciaFomento());
        assertEquals("Tese (Doutorado em Programa de Pós-Graduação em Ciência da Computação) - Centro de Ciências Exatas e de Tecnologia",
                teseLattes.getInstituicao());
        assertEquals("Carlos Eduardo Cirilo", teseLattes.getNomeAluno());
        assertEquals("Orientador", teseLattes.getTipoOrientacao());
        assertEquals("Adaptação Dinâmica de Serviços Sensível ao Contexto", teseLattes.getTituloTrabalho());
    }

    private void assertProducoesTecnica(ProducaoBibliograficaLattes producoesTecnica) {

        assertEquals(8, producoesTecnica.getProducoes().size());
        assertProducaoTecnica(producoesTecnica.getProducoes().get(0));
    }

    private void assertProducaoTecnica(ProducaoLattes producaoLattes) {

        assertEquals(Integer.valueOf(2009), producaoLattes.getAno());
        assertEquals("PRADO, A. F. ; ALMEIDA, R. A. P.", producaoLattes.getAutores());
        assertEquals("Curso de curta duração ministrado/Outra", producaoLattes.getNatureza());
        assertEquals("Paradigmas de Programação", producaoLattes.getTitulo());
    }

    private void assertApresentacaoTrabalho(ApresentacaoTrabalhoLattes apresentacaoTrabalho) {

        assertEquals(6, apresentacaoTrabalho.getTrabalhos().size());
        assertTrabalhoApresentado(apresentacaoTrabalho.getTrabalhos().get(0));
    }

    private void assertTrabalhoApresentado(TrabalhoApresentadoLattes trabalhoApresentadoLattes) {

        assertEquals(Integer.valueOf(2010), trabalhoApresentadoLattes.getAno());
        assertEquals("CIRILO, C. E. ; BELLINI, A. ; PRADO, A. F. ; ZAINA, L. A. M.", trabalhoApresentadoLattes.getAutores());
        assertEquals("Apresentação de Trabalho/Outra", trabalhoApresentadoLattes.getNatureza());
        assertEquals("Desenvolvimento de Sistemas Sensíveis ao Contexto usando Web Services", trabalhoApresentadoLattes.getTitulo());
    }

    private void assertTrabalhoTecnico(TrabalhosTecnicoLattes trabalhosTecnico) {

        assertEquals(12, trabalhosTecnico.getTrabalhos().size());
        assertTrabalho(trabalhosTecnico.getTrabalhos().get(0));
    }

    private void assertTrabalho(TrabalhoLattes trabalhoLattes) {

        assertEquals(Integer.valueOf(2011), trabalhoLattes.getAno());
        assertEquals("PRADO, A. F.", trabalhoLattes.getAutores());
        assertEquals("Participação no Comitê de Avaliação de Artigos do XXV Simpósio Brasileiro de Engenharia de Software",
                trabalhoLattes.getTitulo());
    }

    private void assertProducaoBibliografica(ProducaoBibliograficaLattes producaoBibliografica) {

        assertEquals(1, producaoBibliografica.getProducoes().size());
        assertProducao(producaoBibliografica.getProducoes().get(0));
    }

    private void assertProducao(ProducaoLattes producaoLattes) {

        assertEquals(Integer.valueOf(2000), producaoLattes.getAno());
        assertEquals("PRADO, A. F.", producaoLattes.getAutores());
        assertEquals("Apostila", producaoLattes.getNatureza());
        assertEquals("Unified Modeling Language. São Carlos: Universidade Federal de São Carlos - Departamento de Computação", producaoLattes.getTitulo());
    }

    private void assertResumoCongresso(ResumoCongressoLattes resumoCongresso) {

        assertEquals(15, resumoCongresso.getResumos().size());
        assertResumo(resumoCongresso.getResumos().get(0));
    }

    private void assertResumo(ResumoLattes resumoLattes) {

        assertEquals(Integer.valueOf(2012), resumoLattes.getAno());
        assertEquals("CIRILO, C. E. ; PRADO, A. F. ; SOUZA, Wanderley Lopes de ; ZAINA, L. A. M.", resumoLattes.getAutores());
        assertEquals("", resumoLattes.getDoi());
        assertEquals("XV Congresso Ibero-Americano em Engenharia de Software (CIbSE), 2012, Buenos Aires, Argentina. Anais do XV Congresso Ibero-Americano em Engenharia de Software",
                resumoLattes.getNome_evento());
        assertEquals("264-269", resumoLattes.getPaginas());
        assertEquals("Estudo Experimental do Processo Model Driven RichUbi: Análise Qualitativa", resumoLattes.getTitulo());
        assertEquals(Integer.valueOf(0), resumoLattes.getVolume());
    }

    private void assertResumoExpandido(ResumoExpandidoCongressoLattes resumoExpandido) {

        assertEquals(5, resumoExpandido.getResumos().size());
        assertResumo(resumoExpandido.getResumos().get(0));
    }

    private void assertResumo(ResumoExpandidoLattes resumoExpandidoLattes) {

        assertEquals(Integer.valueOf(2009), resumoExpandidoLattes.getAno());
        assertEquals("SANTANA, E. F. Z. ; PRADO, A. F. ; SOUZA, Wanderley Lopes de", resumoExpandidoLattes.getAutores());
        assertEquals("", resumoExpandidoLattes.getDoi());
        assertEquals("Simpósio Brasileiro de Sistemas Multimídia e Web (WEBMEDIA), 2009, Fortaleza. Anais do Simpósio Brasileiro de Sistemas Multimídia e Web",
                resumoExpandidoLattes.getNome_evento());
        assertEquals("39-42", resumoExpandidoLattes.getPaginas());
        assertEquals("P2PMobileLearning: Uma aplicação P2P com adaptação de conteúdo em dispositivos móveis", resumoExpandidoLattes.getTitulo());
        assertEquals(Integer.valueOf(2), resumoExpandidoLattes.getVolume());
    }

    private void assertTrabalhoCompleto(TrabalhoCompletoCongressoLattes trabalhoCompleto) {

        assertEquals(147, trabalhoCompleto.getTrabalhosCompleto().size());
        assertTrabalho(trabalhoCompleto.getTrabalhosCompleto().get(0));
    }

    private void assertTrabalho(TrabalhoCompletoLattes trabalhoCompletoLattes) {

        assertEquals(Integer.valueOf(2013), trabalhoCompletoLattes.getAno());
        assertEquals("da Silva, V. G. ; CIRILO, C. E. ; PRADO, A. F. ; SOUZA, Wanderley Lopes de ; PEREIRA, V.", trabalhoCompletoLattes.getAutores());
        assertEquals("", trabalhoCompletoLattes.getDoi());
        assertEquals("ICIW 2013 : The Eighth International Conference on Internet and Web Applications and Services", trabalhoCompletoLattes.getNome_evento());
        assertEquals("83-89", trabalhoCompletoLattes.getPaginas());
        assertEquals("An Approach to Dynamic Discovery of Context-Sensitive Web Services", trabalhoCompletoLattes.getTitulo());
        assertEquals(Integer.valueOf(0), trabalhoCompletoLattes.getVolume());
    }

    private void assertTextoJornal(TextoJornalLattes textoJornal) {

        assertEquals(2, textoJornal.getTextos().size());
        assertTexto(textoJornal.getTextos().get(0));
    }

    private void assertTexto(TextoLattes textoLattes) {

        assertEquals(Integer.valueOf(2004), textoLattes.getAno());
        assertEquals("CUNHA, João Ronaldo Del Ducca ; PRADO, A. F. ; SANTOS, Antonio Carlos Do", textoLattes.getAutores());
        assertEquals("12 nov. 2004", textoLattes.getData());
        assertEquals("Revista Eletrônica de Sistemas de Informação (RESI), digital, ", textoLattes.getNome_jornal());
        assertEquals("1-10", textoLattes.getPaginas());
        assertEquals("Uma Abordagem para o Processo de Gerenciamento de Configuração de Software", textoLattes.getTitulo());
        assertEquals(Integer.valueOf(1), textoLattes.getVolume());
    }

    private void assertCapitulosLivros(CapitulosLivrosLattes capitulosLivros) {

        assertEquals(5, capitulosLivros.getCapitulos().size());
        assertCapitulo(capitulosLivros.getCapitulos().get(0));
    }

    private void assertCapitulo(CapituloLattes capituloLattes) {

        assertEquals(Integer.valueOf(2012), capituloLattes.getAno());
        assertEquals("CIRILO, C. E. ; PRADO, A. F. ; SOUZA, Wanderley Lopes de ; ZAINA, L. A. M.",
                capituloLattes.getAutores());
        assertEquals("", capituloLattes.getEdicao());
        assertEquals("InTech - Open Access Publisher", capituloLattes.getEditora());
        assertEquals("Ioannis Deliyannis. (Org.). Interactive Multimedia. Rijeka", capituloLattes.getLivro());
        assertEquals("191-218", capituloLattes.getPaginas());
        assertEquals("Building Adaptive Rich Interfaces for Interactive Ubiquitous Applications",
                capituloLattes.getTitulo());
        assertEquals("", capituloLattes.getVolume());
    }

    private void assertArtigosPeriodicos(ArtigosPeriodicosLattes artigosPeriodicos) {

        assertEquals(9, artigosPeriodicos.getArtigos().size());
        assertArtigoPeriodico(artigosPeriodicos.getArtigos().get(1));
    }

    private void assertArtigoPeriodico(ArtigoLattes artigoLattes) {

        assertEquals(Integer.valueOf(2013), artigoLattes.getAno());
        assertEquals("VIANA, MATHEUS C. ; PENTEADO, ROSÂNGELA A.D. ; DO PRADO, ANTÔNIO F.", artigoLattes.getAutores());
        assertEquals("http://dx.doi.org/10.1016/j.jss.2013.07.030", artigoLattes.getDoi());
        assertEquals(Integer.valueOf(0), artigoLattes.getNumero());
        assertEquals("3123-3139", artigoLattes.getPaginas());
        assertEquals("The Journal of Systems and Software", artigoLattes.getRevista());
        assertEquals("Domain-Specific Modeling Languages to improve framework instantiation", artigoLattes.getTitulo());
        assertEquals("The Journal of Systems and Software", artigoLattes.getVolume());
    }

    private void assertColaboradores(ColaboradoresLattes colaboradores) {

        List<String> idsColaboradores = colaboradores.getIdColaborador();
        List<String> idsColaboradoresTest = loadIdsColaboradoresTest();

        assertEquals(67, idsColaboradores.size());
        for (int index = 0; index < idsColaboradores.size(); index++) {
            assertEquals(idsColaboradoresTest.get(index).trim(), idsColaboradores.get(index).trim());
        }
    }

    private void assertAreaAtuacao(AreaAtuacaoLattes areaAtuacao) {

        List<String> descricoes = areaAtuacao.getDescricao();
        List<String> descricoesTest = loaddDescricoesTest();

        assertEquals(6, descricoes.size());
        for (int index = 0; index < descricoes.size(); index++) {
            assertEquals(descricoesTest.get(index).trim(), descricoes.get(index).trim());
        }
    }

    private void assertProjetosPesquisa(ProjetetosPesquisaLattes projetosPesquisa) {

        assertEquals(7, projetosPesquisa.getProjetos().size());
        assertProjeto(projetosPesquisa.getProjetos().get(0));
    }

    private void assertProjeto(ProjetoLattes projetoLattes) {

        assertEquals(Integer.valueOf(2012), projetoLattes.getAnoConclusao());
        assertEquals(Integer.valueOf(2011), projetoLattes.getAnoInicio());
        assertEquals(
                "Descrição: A presente proposta tem como objetivo a elaboração de material didático interdisciplinar que atenda às etapas de modelagem e implementação de sistemas de software. Pretende-se elaborar material didático que envolva a integração de disciplinas de programação, desenvolvimento de Software e Banco de Dados ministradas nos módulos do curso Sistemas de Informação. Essas disciplinas nortearão o desenvolvimento de um material didático que apoiará os professores e alunos ao longo do curso.. Situação: Em andamento; Natureza: Pesquisa. Alunos envolvidos: Graduação: (4) / Mestrado acadêmico: (2) / Doutorado: (1) . Integrantes: Antonio Francisco do Prado - Coordenador / Marilde T P Santos - Integrante / Carlos Eduardo Cirilo - Integrante / Carlos Roberto Silveira Júnior - Integrante / Bruno Luigi Borgo - Integrante / Graziele Aparecida de Lima Rocco - Integrante / Paulo Eduardo Papotti - Integrante / Eurico Rogério Corte - Integrante.",
                projetoLattes.getDescricao());
        assertEquals(
                "Elaboração de Material Didático Interdisciplinar de Apoio ao Processo de Desenvolvimento de Software nas Etapas de Modelagem e Implementação",
                projetoLattes.getNome());
    }

    private void assertIdentificacao(IdentificacaoLattes identificacao) {

        assertEquals("", identificacao.getIdentificador());
        assertEquals(
                "PRADO, A. F.;DO PRADO, A. F.;PRADO, A. F. do;PRADO, ANTONIO F.;DO PRADO, ANTONIO F.;DO PRADO, ANTÔNIO F.;FRANCISCO DO PRADO, ANTONIO",
                identificacao.getNomeCitacaoBibliografica());
        assertEquals("Antonio Francisco do Prado", identificacao.getNomeCompleto());
        assertEquals("Antonio Francisco do Prado", identificacao.getNomeInicial());
        assertEquals("Masculino", identificacao.getSexo());
    }

    private void assertEndereco(EnderecoLattes endereco) {

        assertEquals(
                "Universidade Federal de São Carlos, Centro de Ciências Exatas e de Tecnologia, Departamento de Computação. Rodovia Washington Luiz, Km 235 - Jardim Guanabara - Prédio DC1 - Sala 13 Jardim Guanabara 13565905 - Sao Carlos, SP - Brasil - Caixa-postal: 676 Telefone: (16) 33518598 Fax: (16) 33518233 URL da Homepage: http://www.dc.ufscar.br/~prado",
                endereco.getEnderecoProfissional());
        assertEquals(Double.valueOf(-21.9814744), endereco.getEnderecoProfissionalLatitude());
        assertEquals(Double.valueOf(-47.8809028), endereco.getEnderecoProfissionalLongitude());
    }

    private void assertIdiomas(IdiomasLattes idiomas) {

        assertEquals(2, idiomas.getIdiomas().size());
        assertIdioma(idiomas.getIdiomas().get(0));
    }

    private void assertIdioma(IdiomaLattes idioma) {

        assertEquals("Inglês", idioma.getNome());
        assertEquals("Compreende Bem, Fala Razoavelmente, Lê Bem, Escreve Razoavelmente.", idioma.getProficiencia());
    }

    private void assertFormacoes(FormacoesAcademicaLattes formacoes) {

        assertEquals(8, formacoes.getFormacoes().size());
        assertFormacao(formacoes.getFormacoes().get(0));
    }

    private void assertFormacao(FormacaoLattes formacao) {

        assertEquals(Integer.valueOf(1992), formacao.getAnoConclusao());
        assertEquals(Integer.valueOf(1990), formacao.getAnoInicio());
        assertEquals(
                "Título: Estratégia de Reengenharia de Software Orientada a Domínios, Ano de obtenção: 1992. Orientador: Julio Cesar Sampaio do Prado Leite. Palavras-chave: Reengenharia de Software; Domínio; Sistema de Transformação Draco; Engenharia Reversa; Manutenção de Software. Grande área: Ciências Exatas e da Terra / Área: Ciência da Computação / Subárea: Metodologia e Técnicas da Computação / Especialidade: Engenharia de Software. Grande Área: Ciências Exatas e da Terra / Área: Ciência da Computação / Subárea: Metodologia e Técnicas da Computação / Especialidade: Reengenharia de Software. Grande Área: Ciências Exatas e da Terra / Área: Ciência da Computação / Subárea: Metodologia e Técnicas da Computação / Especialidade: Linguagens de Programação. Setores de atividade: Informática.",
                formacao.getDescricao());
        assertEquals("Pontifícia Universidade Católica do Rio de Janeiro, PUC-Rio, Brasil",
                formacao.getNomeInstituicao());
        assertEquals("Doutorado em Informática", formacao.getTipo());
    }

    private List<String> loaddDescricoesTest() {

        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("areasAtuacoesDescricoesTest.txt");
        List<String> result = null;

        try {
            result = Arrays.asList(IOUtils.toString(resourceAsStream, "UTF-8").split("\n"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    private List<String> loadIdsColaboradoresTest() {

        InputStream resourceAsStream = getClass().getClassLoader().getResourceAsStream("idsColaboradoresTest.txt");
        List<String> result = null;

        try {
            result = Arrays.asList(IOUtils.toString(resourceAsStream, "UTF-8").split("\n"));
        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return result;
    }
}
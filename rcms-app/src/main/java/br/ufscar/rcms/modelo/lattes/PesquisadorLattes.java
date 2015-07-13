package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pesquisador")
public class PesquisadorLattes extends CurriculoLattes {

    private static final long serialVersionUID = 7991964474837211965L;

    @XmlAttribute(name = "id")
    private String codigoLattes;

    @XmlElement
    private IdentificacaoLattes identificacao;

    @XmlElement
    private IdiomasLattes idiomas;

    @XmlElement
    private EnderecoLattes endereco;

    @XmlElement(name = "formacao_academica")
    private FormacoesAcademicaLattes formacoes;

    @XmlElement(name = "projetos_pesquisa")
    private ProjetetosPesquisaLattes projetosPesquisa;

    @XmlElement(name = "area_atuacao")
    private AreaAtuacaoLattes areaAtuacao;

    @XmlElement(name = "premios_titulos")
    private PremiosLattes premios;

    @XmlElement
    private ColaboradoresLattes colaboradores;

    @XmlElement(name = "artigos_em_periodicos")
    private ArtigosPeriodicosLattes artigosPeriodicos;

    @XmlElement(name = "capitulos_livros")
    private CapitulosLivrosLattes capitulosLivros;

    @XmlElement(name = "texto_em_jornal")
    private TextoJornalLattes textoJornal;

    @XmlElement(name = "trabalho_completo_congresso")
    private TrabalhoCompletoCongressoLattes trabalhoCompleto;

    @XmlElement(name = "resumo_expandido_congresso")
    private ResumoExpandidoCongressoLattes resumoExpandido;

    @XmlElement(name = "resumo_congresso")
    private ResumoCongressoLattes resumoCongresso;

    @XmlElement(name = "apresentacao_trabalho")
    private ApresentacaoTrabalhoLattes apresentacaoTrabalho;

    @XmlElement(name = "producao_bibliografica")
    private ProducaoBibliograficaLattes producaoBibliografica;

    @XmlElement(name = "trabalhos_tecnicos")
    private TrabalhosTecnicoLattes trabalhosTecnico;

    @XmlElement(name = "producao_tecnica")
    private OutrasProducoesTecnicaLattes producoesTecnica;

    @XmlElement(name = "orientacao_doutorado_em_andamento")
    private OrientacaoDoutoradoLattes orientacaoDoutoradoAndamento = new OrientacaoDoutoradoLattes();

    @XmlElement(name = "orientacao_mestrado_em_andamento")
    private OrientacaoMestradoLattes orientacaoMestradoAndamento = new OrientacaoMestradoLattes();

    @XmlElement(name = "orientacao_doutorado_concluido")
    private OrientacaoDoutoradoLattes orientacaoDoutoradoConcluido = new OrientacaoDoutoradoLattes();

    @XmlElement(name = "orientacao_mestrado_concluido")
    private OrientacaoMestradoLattes orientacaoMestradoConcluido = new OrientacaoMestradoLattes();

    @XmlElement(name = "orientacao_tcc_concluido")
    private OrientacaoTCCLattes orientacaoTCCConcluido = new OrientacaoTCCLattes();

    @XmlElement(name = "orientacao_iniciacao_cientifica_concluido")
    private OrientacaoIniciacaoCientificaLattes orientacaoIniciacaoCientificaConcluido = new OrientacaoIniciacaoCientificaLattes();

    @XmlElement(name = "orientacao_outros_tipos_concluido")
    private OrientacaoOutrosTiposConcluidoLattes orientacaoOutrosTipoConcluido = new OrientacaoOutrosTiposConcluidoLattes();

    @XmlElement(name = "participacao_evento")
    private ParticipacaoEventoLattes participacaoEvento;

    @XmlElement(name = "organizacao_evento")
    private OrganizacaoEventoLattes organizacaoEvento;

    public String getCodigoLattes() {
        return codigoLattes;
    }

    public IdentificacaoLattes getIdentificacao() {
        return identificacao;
    }

    public IdiomasLattes getIdiomas() {
        return idiomas;
    }

    public EnderecoLattes getEndereco() {
        return endereco;
    }

    public FormacoesAcademicaLattes getFormacoes() {
        return formacoes;
    }

    public ProjetetosPesquisaLattes getProjetosPesquisa() {
        return projetosPesquisa;
    }

    public AreaAtuacaoLattes getAreaAtuacao() {
        return areaAtuacao;
    }

    public PremiosLattes getPremios() {
        return premios;
    }

    public ColaboradoresLattes getColaboradores() {
        return colaboradores;
    }

    public ArtigosPeriodicosLattes getArtigosPeriodicos() {
        return artigosPeriodicos;
    }

    public CapitulosLivrosLattes getCapitulosLivros() {
        return capitulosLivros;
    }

    public TextoJornalLattes getTextoJornal() {
        return textoJornal;
    }

    public TrabalhoCompletoCongressoLattes getTrabalhoCompleto() {
        return trabalhoCompleto;
    }

    public ResumoExpandidoCongressoLattes getResumoExpandido() {
        return resumoExpandido;
    }

    public ResumoCongressoLattes getResumoCongresso() {
        return resumoCongresso;
    }

    public ApresentacaoTrabalhoLattes getApresentacaoTrabalho() {
        return apresentacaoTrabalho;
    }

    public ProducaoBibliograficaLattes getProducaoBibliografica() {
        return producaoBibliografica;
    }

    public TrabalhosTecnicoLattes getTrabalhosTecnico() {
        return trabalhosTecnico;
    }

    public OutrasProducoesTecnicaLattes getProducoesTecnica() {
        return producoesTecnica;
    }

    public OrientacaoDoutoradoLattes getOrientacaoDoutoradoAndamento() {
        return orientacaoDoutoradoAndamento;
    }

    public OrientacaoMestradoLattes getOrientacaoMestradoAndamento() {
        return orientacaoMestradoAndamento;
    }

    public OrientacaoDoutoradoLattes getOrientacaoDoutoradoConcluido() {
        return orientacaoDoutoradoConcluido;
    }

    public OrientacaoMestradoLattes getOrientacaoMestradoConcluido() {
        return orientacaoMestradoConcluido;
    }

    public OrientacaoTCCLattes getOrientacaoTCCConcluido() {
        return orientacaoTCCConcluido;
    }

    public OrientacaoIniciacaoCientificaLattes getOrientacaoIniciacaoCientificaConcluido() {
        return orientacaoIniciacaoCientificaConcluido;
    }

    public OrientacaoOutrosTiposConcluidoLattes getOrientacaoOutrosTipoConcluido() {
        return orientacaoOutrosTipoConcluido;
    }

    public ParticipacaoEventoLattes getParticipacaoEvento() {
        return participacaoEvento;
    }

    public OrganizacaoEventoLattes getOrganizacaoEvento() {
        return organizacaoEvento;
    }
}
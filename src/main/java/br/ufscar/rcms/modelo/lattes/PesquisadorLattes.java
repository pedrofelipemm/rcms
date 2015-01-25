package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pesquisador")
public class PesquisadorLattes extends BaseLattes {

    @XmlAttribute
    private String id;

    @XmlElement
    private IdentificacaoLattes identificacao;

    @XmlElement
    private IdiomasLattes idiomas;

    @XmlElement
    private EnderecoLattes endereco;

    @XmlElement(name = "formacao_academica")
    private FormacoesAcademicaLattes formacoes;
}
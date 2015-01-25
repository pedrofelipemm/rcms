package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "formacao")
public class FormacaoLattes extends BaseLattes {

    @XmlElement(name = "ano_inicio")
    private Integer anoInicio;

    @XmlElement(name = "ano_conclusao")
    private Integer anoConclusao;

    @XmlElement
    private String tipo;

    @XmlElement(name = "nome_instituicao")
    private String nomeInstituicao;

    @XmlElement
    private String descricao;
}
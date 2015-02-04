package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "formacao_academica")
public class FormacoesAcademicaLattes extends BaseLattes {

    private static final long serialVersionUID = -5213388715450461880L;

    @XmlElement(name = "formacao")
    private List<FormacaoLattes> formacoes;

    public List<FormacaoLattes> getFormacoes() {
        return formacoes;
    }
}
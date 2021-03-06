package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "formacao_academica")
public class FormacoesAcademicaLattes {

    @XmlElement(name = "formacao")
    private List<FormacaoLattes> formacoes;

    public List<FormacaoLattes> getFormacoes() {
        return formacoes;
    }
}
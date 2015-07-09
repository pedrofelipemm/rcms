package br.ufscar.rcms.modelo.lattes;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@SuppressWarnings("serial")
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class OrientacaoLattes implements Serializable {

    @XmlElement(name = "nome_aluno")
    private String nomeAluno;

    @XmlElement(name = "titulo_trabalho")
    private String tituloTrabalho;

    @XmlElement
    private Integer ano;

    @XmlElement
    private String instituicao;

    @XmlElement(name = "agencia_de_fomento")
    private String agenciaFomento;

    @XmlElement(name = "tipo_de_orientacao")
    private String tipoOrientacao;

    public String getNomeAluno() {
        return nomeAluno;
    }

    public String getTituloTrabalho() {
        return tituloTrabalho;
    }

    public Integer getAno() {
        return ano;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public String getAgenciaFomento() {
        return agenciaFomento;
    }

    public String getTipoOrientacao() {
        return tipoOrientacao;
    }
}
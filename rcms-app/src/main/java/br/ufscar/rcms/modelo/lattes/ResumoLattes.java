package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resumo")
public class ResumoLattes {

    @XmlElement
    private String doi;

    @XmlElement
    private String autores;

    @XmlElement
    private String titulo;

    @XmlElement
    private String nome_evento;

    @XmlElement
    private Integer ano;

    @XmlElement
    private String volume;

    @XmlElement
    private String paginas;

    @XmlElement
    private Integer numero;

    public String getDoi() {
        return doi;
    }

    public String getAutores() {
        return autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNomeEvento() {
        return nome_evento;
    }

    public Integer getAno() {
        return ano;
    }

    public String getVolume() {
        return volume;
    }

    public String getPaginas() {
        return paginas;
    }

    public Integer getNumero() {
        return numero;
    }
}
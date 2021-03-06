package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "artigo")
public class ArtigoLattes {

    @XmlElement
    private String doi;

    @XmlElement
    private String titulo;

    @XmlElement
    private String autores;

    @XmlElement
    private String revista;

    @XmlElement
    private String volume;

    @XmlElement
    private String paginas;

    @XmlElement
    private Integer numero;

    @XmlElement
    private Integer ano;

    public String getDoi() {
        return doi;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutores() {
        return autores;
    }

    public String getRevista() {
        return revista;
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

    public Integer getAno() {
        return ano;
    }
}
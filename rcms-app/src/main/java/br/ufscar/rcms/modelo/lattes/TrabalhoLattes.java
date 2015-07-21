package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="trabalho")
public class TrabalhoLattes {

    private static final long serialVersionUID = 4338553761272447406L;

    @XmlElement
    private String titulo;

    @XmlElement
    private String autores;

    @XmlElement
    private Integer ano;

    public String getTitulo() {
        return titulo;
    }

    public String getAutores() {
        return autores;
    }

    public Integer getAno() {
        return ano;
    }
}
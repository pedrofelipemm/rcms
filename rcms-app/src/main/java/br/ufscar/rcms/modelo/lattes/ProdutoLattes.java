package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="produto")
public class ProdutoLattes {

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

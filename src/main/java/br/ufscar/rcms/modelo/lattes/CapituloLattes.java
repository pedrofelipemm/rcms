package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="capitulo")
public class CapituloLattes extends ProducaoLattes {

    private static final long serialVersionUID = -8435622607913227170L;

    @XmlElement
    private String livro;

    @XmlElement
    private String edicao;

    @XmlElement
    private String editora;

    @XmlElement
    private String volume;

    @XmlElement
    private String paginas;

    public String getLivro() {
        return livro;
    }

    public String getEdicao() {
        return edicao;
    }

    public String getEditora() {
        return editora;
    }

    public String getVolume() {
        return volume;
    }

    public String getPaginas() {
        return paginas;
    }
}
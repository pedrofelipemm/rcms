package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="capitulo")
public class CapituloLattes extends BaseLattes{

    private static final long serialVersionUID = -8435622607913227170L;

    @XmlElement
    private String livro;

    @XmlElement
    private String titulo;

    @XmlElement
    private String autores;

    @XmlElement
    private String edicao;

    @XmlElement
    private String editora;

    @XmlElement
    private String volume;

    @XmlElement
    private String paginas;

    @XmlElement
    private Integer ano;

    public String getLivro() {
        return livro;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutores() {
        return autores;
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

    public Integer getAno() {
        return ano;
    }
}
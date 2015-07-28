package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="livro")
public class LivroLattes {

    @XmlElement
    private String autores;

    @XmlElement
    private String titulo;

    @XmlElement
    private String edicao;

    @XmlElement
    private String volume;

    @XmlElement
    private String paginas;

    @XmlElement
    private Integer ano;

    public String getAutores() {
        return autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getEdicao() {
        return edicao;
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

package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="texto")
public class TextoLattes  extends BaseLattes{

    private static final long serialVersionUID = -3426687605867207262L;

    @XmlElement
    private Integer ano;

    @XmlElement
    private String autores;

    @XmlElement
    private String titulo;

    @XmlElement
    private String nome_jornal;

    @XmlElement
    private String data;

    @XmlElement
    private Integer volume;

    @XmlElement
    private String paginas;

    public Integer getAno() {
        return ano;
    }

    public String getAutores() {
        return autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNome_jornal() {
        return nome_jornal;
    }

    public String getData() {
        return data;
    }

    public Integer getVolume() {
        return volume;
    }

    public String getPaginas() {
        return paginas;
    }
}
package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resumo")
public class ResumoLattes extends BaseLattes{

    private static final long serialVersionUID = 7561464996320402258L;

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
    private Integer volume;

    @XmlElement
    private String paginas;

    public String getDoi() {
        return doi;
    }

    public String getAutores() {
        return autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getNome_evento() {
        return nome_evento;
    }

    public Integer getAno() {
        return ano;
    }

    public Integer getVolume() {
        return volume;
    }

    public String getPaginas() {
        return paginas;
    }
}
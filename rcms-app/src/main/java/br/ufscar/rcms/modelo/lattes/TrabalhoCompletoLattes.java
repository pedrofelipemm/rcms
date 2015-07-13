package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="trabalho_completo")
public class TrabalhoCompletoLattes extends BaseLattes {

    private static final long serialVersionUID = 3104340761284938487L;

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

    public String getNomeEvento() {
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
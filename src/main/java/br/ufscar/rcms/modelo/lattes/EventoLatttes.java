package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="evento")
public class EventoLatttes extends BaseLattes{

    private static final long serialVersionUID = 3890538362455721482L;

    @XmlElement
    private String titulo;

    @XmlElement
    private Integer ano;

    @XmlElement
    private String natureza;

    public String getTitulo() {
        return titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public String getNatureza() {
        return natureza;
    }
}
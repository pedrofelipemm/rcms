package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "premio_titulo")
public class PremioLattes {

    @XmlElement
    private Integer ano;

    @XmlElement
    private String descricao;

    public Integer getAno() {
        return ano;
    }

    public String getDescricao() {
        return descricao;
    }

}
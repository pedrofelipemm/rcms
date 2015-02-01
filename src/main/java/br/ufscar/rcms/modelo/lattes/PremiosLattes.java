package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "premios_titulos")
public class PremiosLattes extends BaseLattes {

    private static final long serialVersionUID = 3333389981085695399L;

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
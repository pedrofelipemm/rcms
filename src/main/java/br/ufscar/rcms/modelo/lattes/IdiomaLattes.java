package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "idioma")
public class IdiomaLattes extends BaseLattes {

    @XmlElement
    private String nome;

    @XmlElement
    private String proficiencia;
}

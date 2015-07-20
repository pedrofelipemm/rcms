package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "premios_titulos")
public class PremiosLattes {

    @XmlElement(name = "premio_titulo")
    private List<PremioLattes> premiosTitulos;

    public List<PremioLattes> getPremiosTitulos() {
        return premiosTitulos;
    }
}
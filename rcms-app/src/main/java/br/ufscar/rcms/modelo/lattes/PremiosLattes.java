package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "premios_titulos")
public class PremiosLattes extends BaseLattes {

    private static final long serialVersionUID = 3333389981085695399L;

    @XmlElement(name = "premio_titulo")
    private List<PremioLattes> premio;

    public List<PremioLattes> getPremio() {
        return premio;
    }

    public void setPremio(List<PremioLattes> premio) {
        this.premio = premio;
    }

}
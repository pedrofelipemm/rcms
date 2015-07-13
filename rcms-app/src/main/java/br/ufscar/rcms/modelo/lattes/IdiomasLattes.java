package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "idiomas")
public class IdiomasLattes extends BaseLattes {

    private static final long serialVersionUID = 3142453263659580699L;

    @XmlElement(name = "idioma")
    private List<IdiomaLattes> idiomas;

    public List<IdiomaLattes> getIdiomas() {
        return idiomas;
    }
}
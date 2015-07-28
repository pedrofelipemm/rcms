package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="capitulos_livros")
public class CapitulosLivrosLattes {

    @XmlElement(name = "capitulo")
    private List<CapituloLattes> capitulos;

    public List<CapituloLattes> getCapitulos() {
        return capitulos;
    }
}
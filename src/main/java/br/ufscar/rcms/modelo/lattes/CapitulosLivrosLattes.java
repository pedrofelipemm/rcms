package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="capitulos_livros")
public class CapitulosLivrosLattes extends BaseLattes{

    private static final long serialVersionUID = 2162088517947266013L;

    @XmlElement(name = "capitulo")
    private List<CapituloLattes> capitulos;

    public List<CapituloLattes> getCapitulos() {
        return capitulos;
    }
}
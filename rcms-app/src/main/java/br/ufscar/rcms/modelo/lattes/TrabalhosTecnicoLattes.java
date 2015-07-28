package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="trabalhos_tecnicos")
public class TrabalhosTecnicoLattes {

    private static final long serialVersionUID = -646682544138678510L;

    @XmlElement(name = "trabalho")
    private List<TrabalhoLattes> trabalhos;

    public List<TrabalhoLattes> getTrabalhos() {
        return trabalhos;
    }
}
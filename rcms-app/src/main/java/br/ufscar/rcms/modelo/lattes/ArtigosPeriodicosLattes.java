package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "artigos_em_periodicos")
public class ArtigosPeriodicosLattes {

    @XmlElement(name = "artigo")
    private List<ArtigoLattes> artigos;

    public List<ArtigoLattes> getArtigos() {
        return artigos;
    }
}
package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "artigos_em_periodicos")
public class ArtigosPeriodicosLattes extends BaseLattes{

    private static final long serialVersionUID = -7046711531516794327L;

    @XmlElement(name = "artigo")
    private List<ArtigoLattes> artigos;

    public List<ArtigoLattes> getArtigos() {
        return artigos;
    }
}
package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="producao_tecnica")
public class OutrasProducoesTecnicaLattes {

    @XmlElement(name = "producao")
    private List<OutraProducaoTecnicaLattes> producoes;

    public List<OutraProducaoTecnicaLattes> getProducoes() {
        return producoes;
    }
}

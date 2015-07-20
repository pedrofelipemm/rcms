package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="producao_bibliografica")
public class ProducaoBibliograficaLattes {

    @XmlElement(name = "producao")
    private List<OutraProducaoLattes> producoes;

    public List<OutraProducaoLattes> getProducoes() {
        return producoes;
    }
}
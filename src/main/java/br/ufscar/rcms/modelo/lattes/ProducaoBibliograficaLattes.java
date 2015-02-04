package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="producao_bibliografica")
public class ProducaoBibliograficaLattes extends BaseLattes{

    private static final long serialVersionUID = -8246733210837106085L;

    @XmlElement(name = "producao")
    private List<ProducaoLattes> producoes;

    public List<ProducaoLattes> getProducoes() {
        return producoes;
    }
}
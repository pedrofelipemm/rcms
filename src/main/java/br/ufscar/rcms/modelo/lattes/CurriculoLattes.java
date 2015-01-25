package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "curriculo_lattes")
public class CurriculoLattes extends BaseLattes {

    @XmlElement
    private List<PesquisadorLattes> pesquisador;
}

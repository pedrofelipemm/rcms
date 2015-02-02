package br.ufscar.rcms.modelo.lattes;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "curriculo_lattes")
public class CurriculoLattes extends BaseLattes {

    private static final long serialVersionUID = 7747209856911005854L;

    @XmlElement(name = "pesquisador")
    private List<PesquisadorLattes> pesquisadores = new ArrayList<PesquisadorLattes>();

    public List<PesquisadorLattes> getPesquisadores() {
        return pesquisadores;
    }
}
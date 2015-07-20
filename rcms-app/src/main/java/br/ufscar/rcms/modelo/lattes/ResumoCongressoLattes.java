package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resumo_congresso")
public class ResumoCongressoLattes {

    @XmlElement(name = "resumo")
    private List<ResumoLattes> resumos;

    public List<ResumoLattes> getResumos() {
        return resumos;
    }
}
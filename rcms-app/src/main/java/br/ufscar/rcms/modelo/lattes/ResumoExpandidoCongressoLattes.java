package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resumo_expandido_congresso")
public class ResumoExpandidoCongressoLattes {

    @XmlElement(name = "resumo_expandido")
    private List<ResumoExpandidoLattes> resumos;

    public List<ResumoExpandidoLattes> getResumos() {
        return resumos;
    }
}
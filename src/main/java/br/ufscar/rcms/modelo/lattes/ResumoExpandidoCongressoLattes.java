package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resumo_expandido_congresso")
public class ResumoExpandidoCongressoLattes extends BaseLattes{

    private static final long serialVersionUID = 6353263550182120326L;

    @XmlElement(name = "resumo_expandido")
    private List<ResumoExpandidoLattes> resumos;

    public List<ResumoExpandidoLattes> getResumos() {
        return resumos;
    }
}
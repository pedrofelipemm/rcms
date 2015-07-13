package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="resumo_congresso")
public class ResumoCongressoLattes extends BaseLattes{

    private static final long serialVersionUID = 4105330188969567612L;

    @XmlElement(name = "resumo")
    private List<ResumoLattes> resumos;

    public List<ResumoLattes> getResumos() {
        return resumos;
    }
}
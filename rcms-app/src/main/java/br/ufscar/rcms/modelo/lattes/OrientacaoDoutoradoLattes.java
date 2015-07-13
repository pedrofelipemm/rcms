package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="orientacao_doutorado_em_andamento")
public class OrientacaoDoutoradoLattes extends BaseLattes {

    private static final long serialVersionUID = -5533071564512898098L;

    @XmlElement(name = "tese")
    private List<TeseLattes> teses;

    public List<TeseLattes> getTeses() {
        return teses;
    }
}
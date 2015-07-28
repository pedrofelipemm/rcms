package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="orientacao_doutorado_em_andamento")
public class OrientacaoDoutoradoLattes {

    @XmlElement(name = "tese")
    private List<TeseLattes> teses;

    public List<TeseLattes> getTeses() {
        return teses;
    }
}
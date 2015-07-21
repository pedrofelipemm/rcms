package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orientacao_outros_tipos_concluido")
public class OrientacaoOutrosTiposConcluidoLattes {

    @XmlElement(name = "orientacao_outra")
    private List<OrientacaoOutraLattes> orientacaoOutra;

    public List<OrientacaoOutraLattes> getOrientacaoOutra() {
        return orientacaoOutra;
    }
}
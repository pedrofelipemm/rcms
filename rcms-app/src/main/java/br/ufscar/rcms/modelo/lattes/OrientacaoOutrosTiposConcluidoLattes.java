package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "orientacao_outros_tipos_concluido")
public class OrientacaoOutrosTiposConcluidoLattes extends BaseLattes {

    private static final long serialVersionUID = -5352517211480111399L;

    @XmlElement(name = "orientacao_outra")
    private List<OrientacaoOutraLattes> orientacaoOutra;

    public List<OrientacaoOutraLattes> getOrientacaoOutra() {
        return orientacaoOutra;
    }
}
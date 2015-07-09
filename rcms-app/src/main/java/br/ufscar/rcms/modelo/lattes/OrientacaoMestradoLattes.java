package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="orientacao_mestrado_em_andamento")
public class OrientacaoMestradoLattes extends BaseLattes{

    private static final long serialVersionUID = -4805693807821345841L;

    @XmlElement(name = "dissertacao")
    private List<DissertacaoLattes> dissertacoes;

    public List<DissertacaoLattes> getDissertacoes() {
        return dissertacoes;
    }
}
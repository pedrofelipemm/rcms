package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="orientacao_mestrado_em_andamento")
public class OrientacaoMestradoLattes {

    @XmlElement(name = "dissertacao")
    private List<DissertacaoLattes> dissertacoes;

    public List<DissertacaoLattes> getDissertacoes() {
        return dissertacoes;
    }
}
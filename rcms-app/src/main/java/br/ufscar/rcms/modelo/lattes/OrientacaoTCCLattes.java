package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="orientacao_tcc_concluido")
public class OrientacaoTCCLattes extends BaseLattes {

    private static final long serialVersionUID = 6150155539336469558L;

    @XmlElement(name = "tcc")
    private List<TCCLattes> tccs;

    public List<TCCLattes> getTccs() {
        return tccs;
    }
}
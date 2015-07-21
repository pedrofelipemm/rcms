package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="area_atuacao")
public class AreaAtuacaoLattes {

    @XmlElement
    private List<String> descricao;

    public List<String> getDescricao() {
        return descricao;
    }
}
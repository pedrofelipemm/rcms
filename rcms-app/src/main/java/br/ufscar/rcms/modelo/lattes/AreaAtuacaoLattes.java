package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="area_atuacao")
public class AreaAtuacaoLattes extends BaseLattes{

    private static final long serialVersionUID = -4806236255878565582L;

    @XmlElement
    private List<String> descricao;

    public List<String> getDescricao() {
        return descricao;
    }
}
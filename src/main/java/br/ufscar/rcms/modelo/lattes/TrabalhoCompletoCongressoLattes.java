package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="trabalho_completo_congresso")
public class TrabalhoCompletoCongressoLattes extends BaseLattes{

    private static final long serialVersionUID = 2270860624390349662L;

    @XmlElement(name = "trabalho_completo")
    private List<TrabalhoCompletoLattes> trabalhosCompleto;

    public List<TrabalhoCompletoLattes> getTrabalhosCompleto() {
        return trabalhosCompleto;
    }
}
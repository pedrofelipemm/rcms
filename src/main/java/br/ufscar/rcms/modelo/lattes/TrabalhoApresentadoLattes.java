package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="trabalho_apresentado")
public class TrabalhoApresentadoLattes extends ProducaoLattes {

    private static final long serialVersionUID = -3585193499012962503L;

    @XmlElement
    private String natureza;

    public String getNatureza() {
        return natureza;
    }
}
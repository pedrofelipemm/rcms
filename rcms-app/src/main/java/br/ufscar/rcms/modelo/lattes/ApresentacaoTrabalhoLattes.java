package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="trabalho_apresentado")
public class ApresentacaoTrabalhoLattes extends BaseLattes {

    private static final long serialVersionUID = 4036171303808796168L;

    @XmlElement(name = "trabalho_apresentado")
    private List<TrabalhoApresentadoLattes> trabalhos;

    public List<TrabalhoApresentadoLattes> getTrabalhos() {
        return trabalhos;
    }
}
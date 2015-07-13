package br.ufscar.rcms.modelo.lattes;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="texto_em_jornal")
public class TextoJornalLattes extends BaseLattes{

    private static final long serialVersionUID = -4206499889847615840L;

    @XmlElement(name = "texto")
    private List<TextoLattes> textos;

    public List<TextoLattes> getTextos() {
        return textos;
    }
}
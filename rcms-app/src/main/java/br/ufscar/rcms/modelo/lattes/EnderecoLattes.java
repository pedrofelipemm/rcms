package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "endereco")
public class EnderecoLattes {

    @XmlElement(name = "endereco_profissional")
    private String enderecoProfissional;

    @XmlElement(name = "endereco_profissional_lat")
    private Double enderecoProfissionalLatitude;

    @XmlElement(name = "endereco_profissional_long")
    private Double enderecoProfissionalLongitude;

    public String getEnderecoProfissional() {
        return enderecoProfissional;
    }

    public Double getEnderecoProfissionalLatitude() {
        return enderecoProfissionalLatitude;
    }

    public Double getEnderecoProfissionalLongitude() {
        return enderecoProfissionalLongitude;
    }
}
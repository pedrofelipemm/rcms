package br.ufscar.rcms.webservice.modelo;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "endereço")
public class EnderecoResponse extends Response {

    private static final long serialVersionUID = 1761582940789916976L;

    @XmlElement
    private Long idEndereco;

    @XmlElement
    private String enderecoProfissional;

    @XmlElement
    private Double enderecoProfissionalLatitude;

    @XmlElement
    private Double enderecoProfissionalLongitude;

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getEnderecoProfissional() {
        return enderecoProfissional;
    }

    public void setEnderecoProfissional(String enderecoProfissional) {
        this.enderecoProfissional = enderecoProfissional;
    }

    public Double getEnderecoProfissionalLatitude() {
        return enderecoProfissionalLatitude;
    }

    public void setEnderecoProfissionalLatitude(Double enderecoProfissionalLatitude) {
        this.enderecoProfissionalLatitude = enderecoProfissionalLatitude;
    }

    public Double getEnderecoProfissionalLongitude() {
        return enderecoProfissionalLongitude;
    }

    public void setEnderecoProfissionalLongitude(Double enderecoProfissionalLongitude) {
        this.enderecoProfissionalLongitude = enderecoProfissionalLongitude;
    }
}
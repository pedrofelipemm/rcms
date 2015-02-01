package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="trabalho_apresentado")
public class TrabalhoApresentadoLattes extends BaseLattes{

    private static final long serialVersionUID = -3585193499012962503L;

    @XmlElement
    private String titulo;

    @XmlElement
    private String autores;

    @XmlElement
    private Integer ano;

    @XmlElement
    private String natureza;

    public String getTitulo() {
        return titulo;
    }

    public String getAutores() {
        return autores;
    }

    public Integer getAno() {
        return ano;
    }

    public String getNatureza() {
        return natureza;
    }
}
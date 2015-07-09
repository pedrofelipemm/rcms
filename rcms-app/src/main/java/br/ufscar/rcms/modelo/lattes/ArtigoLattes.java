package br.ufscar.rcms.modelo.lattes;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "artigo")
public class ArtigoLattes extends ProducaoLattes {

    private static final long serialVersionUID = 3706515736811497699L;

    @XmlElement
    private String doi;

    @XmlElement
    private String revista;

    @XmlElement
    private String volume;

    @XmlElement
    private String paginas;

    @XmlElement
    private Integer numero;

    public String getDoi() {
        return doi;
    }

    public String getRevista() {
        return revista;
    }

    public String getVolume() {
        return volume;
    }

    public String getPaginas() {
        return paginas;
    }

    public Integer getNumero() {
        return numero;
    }
}
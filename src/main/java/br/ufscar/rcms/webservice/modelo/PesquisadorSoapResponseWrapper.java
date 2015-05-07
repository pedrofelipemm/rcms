package br.ufscar.rcms.webservice.modelo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlRootElement
@XmlSeeAlso(value=PesquisadorResponse.class)
public class PesquisadorSoapResponseWrapper extends Response implements Serializable {

	private static final long serialVersionUID = -7667129522078839507L;

	@XmlElement
    private PesquisadorResponse[] pesquisadores;

    @XmlElement
    private int totalRows;

    public PesquisadorSoapResponseWrapper() {
    }

    public PesquisadorSoapResponseWrapper(int totalRows, PesquisadorResponse[] pesquisadores) {
        this.totalRows = totalRows;
        this.pesquisadores = pesquisadores;
    }

    public int getTotalRows() {
        return totalRows;
    }

	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}

	public PesquisadorResponse[] getPesquisadores() {
		return pesquisadores;
	}

	public void setPesquisadores(PesquisadorResponse[] pesquisadores) {
		this.pesquisadores = pesquisadores;
	}

}
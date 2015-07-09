package br.ufscar.rcms.webservice.modelo;

import java.util.List;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class PesquisadorResponseWrapper extends ResponseWrapper {

    private static final long serialVersionUID = -6334447942095520684L;

    @XmlElement
    private List<PesquisadorResponse> pesquisadores;

    @XmlElement
    private int totalRows;

    public PesquisadorResponseWrapper() {
    }

    public PesquisadorResponseWrapper(Status status, String message) {
        super(status, message);
    }

    public PesquisadorResponseWrapper(Status status, int totalRows, List<PesquisadorResponse> pesquisadores) {
        super(status);
        this.totalRows = totalRows;
        this.pesquisadores = pesquisadores;
    }

    public List<PesquisadorResponse> getPesquisadores() {
        return pesquisadores;
    }

    public int getTotalRows() {
        return totalRows;
    }

}
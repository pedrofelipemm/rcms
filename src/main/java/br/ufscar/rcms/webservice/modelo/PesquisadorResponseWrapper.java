package br.ufscar.rcms.webservice.modelo;

import java.util.List;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "response")
public class PesquisadorResponseWrapper extends Response {

    private static final long serialVersionUID = -6334447942095520684L;

    @XmlElement
    private List<PesquisadorResponse> pesquisadores;

    public PesquisadorResponseWrapper() {}

    public PesquisadorResponseWrapper(Status status, List<PesquisadorResponse> pesquisadores) {
        super(status);
        this.pesquisadores = pesquisadores;
    }

    public List<PesquisadorResponse> getObjects() {
        return pesquisadores;
    }
}
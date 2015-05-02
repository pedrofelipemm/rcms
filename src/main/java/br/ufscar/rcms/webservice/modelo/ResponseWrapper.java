package br.ufscar.rcms.webservice.modelo;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@SuppressWarnings("serial")
public class ResponseWrapper extends Response {

    @XmlElement
    private Status status;

    @XmlElement
    private String message;

    public ResponseWrapper() {}

    public ResponseWrapper(Status status, String message) {
        this.status = status;
        this.message = message;
    }

    public ResponseWrapper(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
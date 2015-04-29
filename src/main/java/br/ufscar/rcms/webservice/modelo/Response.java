package br.ufscar.rcms.webservice.modelo;

import java.io.Serializable;

import javax.ws.rs.core.Response.Status;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@SuppressWarnings("serial")
public class Response implements Serializable {

    @XmlElement
    private Status status;

    public Response() {}

    public Response(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }
}
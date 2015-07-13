package br.ufscar.rcms.webservice.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.ufscar.rcms.webservice.modelo.ResponseWrapper;

public class ResourceNotFoundException extends WebApplicationException {

    private static final long serialVersionUID = -2566251914070096686L;

    public ResourceNotFoundException(String message) {
        super(Response.status(Status.NOT_FOUND.getStatusCode()).entity(new ResponseWrapper(Status.NOT_FOUND, message)).build());
    }

}
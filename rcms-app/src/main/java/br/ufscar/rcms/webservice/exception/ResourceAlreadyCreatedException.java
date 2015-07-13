package br.ufscar.rcms.webservice.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.ufscar.rcms.webservice.modelo.ResponseWrapper;

public class ResourceAlreadyCreatedException  extends WebApplicationException{

    private static final long serialVersionUID = -2712325108629280521L;

    public ResourceAlreadyCreatedException(String message) {
        super(Response.status(Status.CONFLICT.getStatusCode()).entity(new ResponseWrapper(Status.CONFLICT, message))
                .build());
    }

}
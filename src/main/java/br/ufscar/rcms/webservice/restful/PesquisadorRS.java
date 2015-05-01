package br.ufscar.rcms.webservice.restful;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import br.ufscar.rcms.converter.PesquisadorConverter;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.webservice.modelo.PesquisadorResponse;
import br.ufscar.rcms.webservice.modelo.PesquisadorResponseWrapper;

@Component
@Path("/pesquisadores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PesquisadorRS {

    @Autowired(required = true)
    private PesquisadorService pesquisadorService;

    @GET
    public PesquisadorResponseWrapper getPesquisador() {

        List<Pesquisador> pesquisadores = pesquisadorService.buscarTodos();
        if (CollectionUtils.isEmpty(pesquisadores)) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }

        List<PesquisadorResponse> objects = PesquisadorConverter.convert(pesquisadores);
        PesquisadorResponseWrapper response = new PesquisadorResponseWrapper(Status.OK, objects.size(), objects);

        return response;
    }

    @GET
    @Path("{id}")
    public PesquisadorResponseWrapper getPesquisador(@PathParam("id") Long id) {

        Pesquisador pesquisador = pesquisadorService.buscar(id);

        if (pesquisador == null) {
            throw new WebApplicationException(Status.NOT_FOUND);
        }

        List<PesquisadorResponse> objects = PesquisadorConverter.convert(Arrays.asList(pesquisador));
        PesquisadorResponseWrapper response = new PesquisadorResponseWrapper(Status.OK, objects.size(), objects);

        return response;
    }

    // @POST
    // @Path("{id}")
    // public PesquisadorResponseWrapper addPesquisador(@PathParam("id") Long id) {
    //
    // Pesquisador pesquisador = pesquisadorService.buscar(id);
    //
    // if (pesquisador == null) {
    // throw new WebApplicationException(Status.NOT_FOUND);
    // }
    //
    // List<PesquisadorResponse> objects = PesquisadorAdapter.adapt(pesquisador);
    //
    // PesquisadorResponseWrapper response = new PesquisadorResponseWrapper(Status.OK, objects.size(), objects);
    //
    // return response;
    // }
    //
    // @PUT
    // @Path("{id}")
    // public PesquisadorResponseWrapper updatePesquisador(@PathParam("id") Long id) {
    //
    // Pesquisador pesquisador = pesquisadorService.buscar(id);
    //
    // if (pesquisador == null) {
    // throw new WebApplicationException(Status.NOT_FOUND);
    // }
    //
    // List<PesquisadorResponse> objects = PesquisadorAdapter.adapt(pesquisador);
    //
    // PesquisadorResponseWrapper response = new PesquisadorResponseWrapper(Status.OK, objects.size(), objects);
    //
    // return response;
    // }
    //

    // TODO PADRÃO PARA DELETE
    @DELETE
    @Path("{id}")
    public void deletePesquisador(@PathParam("id") Long id) {

        // TODO TRATAR RUNTIME OU CRIAREXCEPTION
        pesquisadorService.remover(id);

        /*
         * if (pesquisador == null) { throw new WebApplicationException(Status.NOT_FOUND); }
         * 
         * List<PesquisadorResponse> objects = PesquisadorConverter.convert(Arrays.asList(pesquisador));
         * PesquisadorResponseWrapper response = new PesquisadorResponseWrapper(Status.OK, objects.size(), objects);
         * 
         * return response;
         */
    }
}
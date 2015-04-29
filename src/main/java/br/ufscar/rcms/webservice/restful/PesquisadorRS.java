package br.ufscar.rcms.webservice.restful;

import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.webservice.modelo.EnderecoResponse;
import br.ufscar.rcms.webservice.modelo.PesquisadorResponse;
import br.ufscar.rcms.webservice.modelo.PesquisadorResponseWrapper;

@Path("/pesquisadores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PesquisadorRS {

    @Autowired(required = true)
    private PesquisadorService pesquisadorService;

    @GET
    public PesquisadorResponseWrapper getPesquisador() {

        List<Pesquisador> pesquisadores = pesquisadorService.buscarTodos();
        //
        // if (CollectionUtils.isEmpty(pesquisadores)) {
        // throw new WebApplicationException(Status.NOT_FOUND);
        // }
        //
        // List<PesquisadorResponse> objects = PesquisadorAdapter.adapt(pesquisadores);
        PesquisadorResponse test = new PesquisadorResponse();
        test.setNome("Pedro");
        test.setEmail("pedro@gmail.com");
        EnderecoResponse endereco = new EnderecoResponse();
        endereco.setEnderecoProfissional("Rua número zero");
        test.setEndereco(endereco);

        List<PesquisadorResponse> objects = Arrays.asList(test);

        PesquisadorResponseWrapper response = new PesquisadorResponseWrapper(Status.OK, objects);

        return response;
    }

    // @GET
    // @Path("{id}")
    // public ResponseWrapper<PesquisadorResponse> getPesquisador(@PathParam("id") Long id) {
    //
    // // Pesquisador pesquisador = pesquisadorService.buscar(id);
    //
    // Pesquisador pesquisador = new Pesquisador();
    //
    // if (pesquisador == null) {
    // throw new WebApplicationException(Status.NOT_FOUND);
    // }
    //
    // List<PesquisadorResponse> objects = PesquisadorAdapter.adapt(Arrays.asList(pesquisador));
    //
    // ResponseWrapper<PesquisadorResponse> response = new ResponseWrapper<PesquisadorResponse>(Status.OK, objects);
    //
    // return response;
    // }

    // @POST
    // @Path("{id}")
    // public ResponseWrapper<PesquisadorResponse> addPesquisador(@PathParam("id") Long id) {
    //
    // Pesquisador pesquisador = pesquisadorService.buscar(id);
    //
    // if (pesquisador == null) {
    // throw new WebApplicationException(Status.NOT_FOUND);
    // }
    //
    // List<PesquisadorResponse> objects = PesquisadorAdapter.adapt(pesquisador);
    //
    // ResponseWrapper<PesquisadorResponse> response = new ResponseWrapper<PesquisadorResponse>(objects);
    //
    // return response;
    // }
    //
    // @PUT
    // @Path("{id}")
    // public ResponseWrapper<PesquisadorResponse> updatePesquisador(@PathParam("id") Long id) {
    //
    // Pesquisador pesquisador = pesquisadorService.buscar(id);
    //
    // if (pesquisador == null) {
    // throw new WebApplicationException(Status.NOT_FOUND);
    // }
    //
    // List<PesquisadorResponse> objects = PesquisadorAdapter.adapt(pesquisador);
    //
    // ResponseWrapper<PesquisadorResponse> response = new ResponseWrapper<PesquisadorResponse>(objects);
    //
    // return response;
    // }
    //
    // @DELETE
    // @Path("{id}")
    // public ResponseWrapper<PesquisadorResponse> deletePesquisador(@PathParam("id") Long id) {
    //
    // Pesquisador pesquisador = pesquisadorService.buscar(id);
    //
    // if (pesquisador == null) {
    // throw new WebApplicationException(Status.NOT_FOUND);
    // }
    //
    // List<PesquisadorResponse> objects = PesquisadorAdapter.adapt(pesquisador);
    //
    // ResponseWrapper<PesquisadorResponse> response = new ResponseWrapper<PesquisadorResponse>(objects);
    //
    // return response;
    // }
}
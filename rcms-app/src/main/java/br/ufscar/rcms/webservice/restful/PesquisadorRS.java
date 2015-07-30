package br.ufscar.rcms.webservice.restful;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import br.ufscar.rcms.converter.PesquisadorConverter;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.exception.PesquisadorNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;
import br.ufscar.rcms.webservice.exception.ResourceAlreadyCreatedException;
import br.ufscar.rcms.webservice.exception.ResourceNotFoundException;
import br.ufscar.rcms.webservice.modelo.PesquisadorResponse;
import br.ufscar.rcms.webservice.modelo.PesquisadorResponseWrapper;

@Component
@Path("/pesquisadores")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PesquisadorRS {

    private static final Logger LOGGER = LoggerFactory.getLogger(PesquisadorRS.class);

    @Autowired
    private PesquisadorService pesquisadorService;

    @GET
    public Response getPesquisador() {

        List<Pesquisador> pesquisadores = pesquisadorService.buscarTodosOrderByNome();

        if (CollectionUtils.isEmpty(pesquisadores)) {
            throw new ResourceNotFoundException("Nenhum pesquisador encontrado!");
        }

        List<PesquisadorResponse> objects = PesquisadorConverter.convert(pesquisadores);
        PesquisadorResponseWrapper response = new PesquisadorResponseWrapper(Status.OK, objects.size(), objects);

        return Response.status(Status.OK.getStatusCode()).entity(response).build();
    }

    @GET
    @Path("{id}")
    public Response getPesquisador(@PathParam("id") final Long id) {

        Pesquisador pesquisador = pesquisadorService.buscar(id);

        if (isEmpty(pesquisador)) {
            String message = MessageFormat.format("Pesquisador com o código {0} não encontrado!", id);
            throw new ResourceNotFoundException(message);
        }

        List<PesquisadorResponse> objects = PesquisadorConverter.convert(Arrays.asList(pesquisador));
        PesquisadorResponseWrapper response = new PesquisadorResponseWrapper(Status.OK, objects.size(), objects);

        return Response.status(Status.OK.getStatusCode()).entity(response).build();
    }

    @POST
    public Response addPesquisador(final PesquisadorResponse pesquisadorResponse) {

        if (!isEmpty(pesquisadorResponse.getIdUsuario())) {
            Pesquisador pesquisador = pesquisadorService.buscar(pesquisadorResponse.getIdUsuario());
            if (!isEmpty(pesquisador)) {
                throw new ResourceAlreadyCreatedException(MessageFormat.format("Pesquisador {0} já existente!", pesquisador.getNome()));
            }
        }

        PesquisadorResponse result = new PesquisadorResponse();
        try {
            Pesquisador pesquisador = pesquisadorService.salvarOuAtualizar(PesquisadorConverter.convert(pesquisadorResponse));
            result = PesquisadorConverter.convert(pesquisador);
        } catch (RCMSException exception) {
            LOGGER.debug(exception.getMessage(), exception);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

        return Response.status(Status.CREATED.getStatusCode()).entity(result).build();
    }

    @PUT
    public Response updatePesquisador(final PesquisadorResponse pesquisadorResponse) {

        Pesquisador pesquisador = pesquisadorService.buscar(pesquisadorResponse.getIdUsuario());
        if (isEmpty(pesquisador)) {
            String message = MessageFormat.format("Pesquisador com o código {0} não encontrado!", pesquisadorResponse.getIdUsuario());
            throw new ResourceNotFoundException(message);
        }

        try {
            pesquisador = pesquisadorService.salvarOuAtualizar(PesquisadorConverter.convert(pesquisadorResponse));
        } catch (RCMSException exception) {
            LOGGER.debug(exception.getMessage(), exception);
            return Response.status(Status.INTERNAL_SERVER_ERROR).build();
        }

        PesquisadorResponse result = PesquisadorConverter.convert(pesquisador);

        return Response.status(Status.OK.getStatusCode()).entity(result).build();
    }

    @DELETE
    @Path("{id}")
    public Response deletePesquisador(@PathParam("id") final Long id) {

        try {
            pesquisadorService.remover(id);
        } catch (PesquisadorNaoEncontradoException e) {
            String message = MessageFormat.format("Pesquisador com o código {0} não encontrado!", id);
            throw new ResourceNotFoundException(message);
        }

        return Response.status(Status.OK.getStatusCode()).build();
    }
}
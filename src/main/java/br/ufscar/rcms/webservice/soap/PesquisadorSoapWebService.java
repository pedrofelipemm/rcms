package br.ufscar.rcms.webservice.soap;

import java.util.Arrays;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.ufscar.rcms.converter.PesquisadorConverter;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.webservice.modelo.PesquisadorResponse;
import br.ufscar.rcms.webservice.modelo.PesquisadorResponseWrapper;

@Component
@WebService
@Produces(MediaType.APPLICATION_XML)
public class PesquisadorSoapWebService extends SpringBeanAutowiringSupport {
	
	@Autowired(required = true)
	private PesquisadorService pesquisadorService;
	
	@WebMethod
	public Response getPesquisadorPorId(Long pesquisadorId){
		Pesquisador pesquisador = getPesquisadorService().buscar(pesquisadorId);
		
		List<PesquisadorResponse> objects = PesquisadorConverter.convert(Arrays.asList(pesquisador));
        PesquisadorResponseWrapper response = new PesquisadorResponseWrapper(Status.OK, objects.size(), objects);

        return Response.status(Status.OK.getStatusCode()).entity(response).build();
	}

	public PesquisadorService getPesquisadorService() {
		return pesquisadorService;
	}

	public void setPesquisadorService(PesquisadorService pesquisadorService) {
		this.pesquisadorService = pesquisadorService;
	}

}

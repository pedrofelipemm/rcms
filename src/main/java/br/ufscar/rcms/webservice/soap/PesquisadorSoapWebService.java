package br.ufscar.rcms.webservice.soap;

import java.util.Arrays;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.ufscar.rcms.converter.PesquisadorConverter;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.webservice.modelo.PesquisadorResponse;
import br.ufscar.rcms.webservice.modelo.PesquisadorSoapResponseWrapper;

@Component
@WebService
public class PesquisadorSoapWebService extends SpringBeanAutowiringSupport {
	
	public PesquisadorSoapWebService() {
	}
	
	@Autowired(required = true)
	private PesquisadorService pesquisadorService;
	
	@WebMethod
	@Produces(MediaType.APPLICATION_XML)
	public PesquisadorSoapResponseWrapper getPesquisadorPorId(Long pesquisadorId){
		Pesquisador pesquisador = getPesquisadorService().buscar(pesquisadorId);
		
		List<PesquisadorResponse> objects = PesquisadorConverter.convert(Arrays.asList(pesquisador));
		PesquisadorSoapResponseWrapper response = new PesquisadorSoapResponseWrapper(objects.size(),objects.toArray(new PesquisadorResponse[]{}));

        return response;
	}

	public PesquisadorService getPesquisadorService() {
		return pesquisadorService;
	}

	public void setPesquisadorService(PesquisadorService pesquisadorService) {
		this.pesquisadorService = pesquisadorService;
	}

}

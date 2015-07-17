package br.ufscar.rcms.webservice.soap;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import br.ufscar.rcms.converter.PesquisadorConverter;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.servico.PesquisadorService;
import br.ufscar.rcms.servico.exception.PesquisadorNaoEncontradoException;
import br.ufscar.rcms.webservice.exception.ResourceNotFoundException;
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
	public PesquisadorSoapResponseWrapper getPesquisadorPorId(Long pesquisadorId){
		Pesquisador pesquisador = getPesquisadorService().buscar(pesquisadorId);
		
		if (pesquisador == null){
			return new PesquisadorSoapResponseWrapper();
		}
		List<PesquisadorResponse> objects = PesquisadorConverter.convert(Arrays.asList(pesquisador));
		PesquisadorSoapResponseWrapper response = new PesquisadorSoapResponseWrapper(objects.size(),objects.toArray(new PesquisadorResponse[]{}));

        return response;
	}
	
    @WebMethod
    public PesquisadorSoapResponseWrapper getPesquisadores() {

        List<Pesquisador> pesquisadores = pesquisadorService.buscarTodosOrderByNome();

        if (CollectionUtils.isEmpty(pesquisadores)) {
            throw new ResourceNotFoundException("Nenhum pesquisador encontrado!");
        }

        List<PesquisadorResponse> objects = PesquisadorConverter.convert(pesquisadores);
        PesquisadorSoapResponseWrapper response = new PesquisadorSoapResponseWrapper(objects.size(),objects.toArray(new PesquisadorResponse[]{}));

        return response;
    }

	
	@WebMethod
	public String deletePorId(Long pesquisadorId){
		try {
			getPesquisadorService().remover(pesquisadorId);

		} catch (PesquisadorNaoEncontradoException e) {
			 String message = MessageFormat.format("Pesquisador com o código {0} não encontrado!", pesquisadorId);
			 throw new ResourceNotFoundException(message);
		}
		return "Sucesso";
		
        
	}

	@WebMethod(exclude=true)
	public PesquisadorService getPesquisadorService() {
		return pesquisadorService;
	}

	@WebMethod(exclude=true)
	public void setPesquisadorService(PesquisadorService pesquisadorService) {
		this.pesquisadorService = pesquisadorService;
	}

	
}

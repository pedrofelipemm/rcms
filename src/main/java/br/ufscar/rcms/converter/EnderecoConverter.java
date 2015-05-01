package br.ufscar.rcms.converter;

import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.webservice.modelo.EnderecoResponse;

public class EnderecoConverter {

    public static EnderecoResponse convert(Endereco endereco) {

        EnderecoResponse response = new EnderecoResponse();
        response.setEnderecoProfissional(endereco.getEnderecoProfissional());
        response.setEnderecoProfissionalLatitude(endereco.getEnderecoProfissionalLongitude());
        response.setEnderecoProfissionalLongitude(endereco.getEnderecoProfissionalLongitude());
        response.setIdEndereco(endereco.getIdEndereco());

        return response;
    }

}

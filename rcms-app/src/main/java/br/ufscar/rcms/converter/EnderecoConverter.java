package br.ufscar.rcms.converter;

import br.ufscar.rcms.modelo.entidades.Endereco;
import br.ufscar.rcms.webservice.modelo.EnderecoResponse;

public abstract class EnderecoConverter {

    public static EnderecoResponse convert(final Endereco endereco) {

        EnderecoResponse response = new EnderecoResponse();
        response.setEnderecoProfissional(endereco.getEnderecoProfissional());
        response.setEnderecoProfissionalLatitude(endereco.getEnderecoProfissionalLongitude());
        response.setEnderecoProfissionalLongitude(endereco.getEnderecoProfissionalLongitude());
        response.setIdEndereco(endereco.getIdEndereco());

        return response;
    }

    public static Endereco convert(final EnderecoResponse enderecoResponse) {

        Endereco endereco = new Endereco();
        endereco.setEnderecoProfissional(enderecoResponse.getEnderecoProfissional());
        endereco.setEnderecoProfissionalLatitude(enderecoResponse.getEnderecoProfissionalLatitude());
        endereco.setEnderecoProfissionalLongitude(enderecoResponse.getEnderecoProfissionalLongitude());
        endereco.setIdEndereco(enderecoResponse.getIdEndereco());

        return endereco;
    }

}

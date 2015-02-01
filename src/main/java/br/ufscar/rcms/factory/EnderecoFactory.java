package br.ufscar.rcms.factory;

import br.ufscar.rcms.modelo.entidades.Endereco;

public abstract class EnderecoFactory {

    public static Endereco createEndereco(String enderecoProfissional, Double enderecoProfissionalLatitude,
            Double enderecoProfissionalLongitude) {

        Endereco endereco = new Endereco();
        endereco.setEnderecoProfissional(enderecoProfissional);
        endereco.setEnderecoProfissionalLatitude(enderecoProfissionalLatitude);
        endereco.setEnderecoProfissionalLongitude(enderecoProfissionalLongitude);

        return endereco;
    }
}
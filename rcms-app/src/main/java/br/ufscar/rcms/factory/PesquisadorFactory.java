package br.ufscar.rcms.factory;

import br.ufscar.rcms.modelo.entidades.Pesquisador;

public abstract class PesquisadorFactory {

    public static Pesquisador createPesquisadorAdmin() {
        Pesquisador admin = new Pesquisador();
        admin.setNome("Administrador");
        admin.setLogin("admin");
        admin.setSenha("admin");
        admin.setEmail("admin@ufscar.com");
        admin.setCodigoLattes("123456789");
        admin.setResumoProfissional("Administrator RCMS");
        admin.setEndereco(EnderecoFactory.createEndereco("Rua 0", 123.32, 142.21));

        return admin;
    }

}
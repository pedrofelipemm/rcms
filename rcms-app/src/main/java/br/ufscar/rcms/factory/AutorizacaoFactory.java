package br.ufscar.rcms.factory;

import br.ufscar.rcms.modelo.entidades.Autorizacao;

public abstract class AutorizacaoFactory {

    public static Autorizacao createAdmin() {
        Autorizacao admin = new Autorizacao();
        admin.setNomeAutorizacao("ROLE_ADMIN");
        admin.setDescricao("Administrador");

        return admin;
    }

    public static Autorizacao createuser() {
        Autorizacao user = new Autorizacao();
        user.setNomeAutorizacao("ROLE_USER");
        user.setDescricao("Pesquisador");

        return user;
    }
}
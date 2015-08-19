package br.ufscar.rcms.util;

import br.ufscar.rcms.modelo.entidades.Usuario;

public abstract class Fixture {

    public static Usuario newUsuario() {
        Usuario usuario = new Usuario();
        usuario.setNome("Nome");
        usuario.setLogin("Login");
        usuario.setSenha("senha");
        usuario.setEmail("email@email.com");
        return usuario;
    }
}
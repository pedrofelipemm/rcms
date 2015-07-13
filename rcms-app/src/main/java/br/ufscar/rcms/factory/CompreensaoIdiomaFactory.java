package br.ufscar.rcms.factory;

import br.ufscar.rcms.modelo.entidades.CompreensaoIdioma;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.entidades.pk.CompreensaoIdiomaPK;

public class CompreensaoIdiomaFactory {

    public static CompreensaoIdioma createCompreensaoIdioma(Idioma idioma, String proficiencia, Pesquisador pesquisador) {

        CompreensaoIdioma compreensaoIdioma = new CompreensaoIdioma();
        compreensaoIdioma.setProficiencia(proficiencia);
        compreensaoIdioma.setCompreensaoIdiomaPK(new CompreensaoIdiomaPK(idioma, pesquisador));

        return compreensaoIdioma;
    }
}
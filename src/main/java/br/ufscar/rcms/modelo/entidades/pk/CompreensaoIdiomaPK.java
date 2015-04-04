package br.ufscar.rcms.modelo.entidades.pk;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import br.ufscar.rcms.modelo.entidades.Entidade;
import br.ufscar.rcms.modelo.entidades.Idioma;
import br.ufscar.rcms.modelo.entidades.Pesquisador;

@Embeddable
public class CompreensaoIdiomaPK extends Entidade {

    private static final long serialVersionUID = 2780034034995542636L;

    @ManyToOne
    private Idioma idioma;

    @ManyToOne
    private Pesquisador pesquisador;

    public CompreensaoIdiomaPK() {
    }

    public CompreensaoIdiomaPK(Idioma idioma, Pesquisador pesquisador) {
        this.idioma = idioma;
        this.pesquisador = pesquisador;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idioma == null) ? 0 : idioma.hashCode());
        result = prime * result + ((pesquisador == null) ? 0 : pesquisador.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CompreensaoIdiomaPK)) {
            return false;
        }
        CompreensaoIdiomaPK other = (CompreensaoIdiomaPK) obj;
        if (idioma == null) {
            if (other.idioma != null) {
                return false;
            }
        } else if (!idioma.equals(other.idioma)) {
            return false;
        }
        if (pesquisador == null) {
            if (other.pesquisador != null) {
                return false;
            }
        } else if (!pesquisador.equals(other.pesquisador)) {
            return false;
        }
        return true;
    }
}
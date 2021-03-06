package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufscar.rcms.modelo.entidades.pk.CompreensaoIdiomaPK;

@Entity
@Table(name = "compreensao_idioma")
public class CompreensaoIdioma extends Entidade{

    private static final long serialVersionUID = 2333166344065581455L;

    @EmbeddedId
    private CompreensaoIdiomaPK compreensaoIdiomaPK;

    @Column(name = "proficiencia", nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String proficiencia;

    public CompreensaoIdiomaPK getCompreensaoIdiomaPK() {
        return compreensaoIdiomaPK;
    }

    public void setCompreensaoIdiomaPK(final CompreensaoIdiomaPK compreensaoIdiomaPK) {
        this.compreensaoIdiomaPK = compreensaoIdiomaPK;
    }

    public String getProficiencia() {
        return proficiencia;
    }

    public void setProficiencia(final String proficiencia) {
        this.proficiencia = proficiencia;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((compreensaoIdiomaPK == null) ? 0 : compreensaoIdiomaPK.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CompreensaoIdioma)) {
            return false;
        }
        CompreensaoIdioma other = (CompreensaoIdioma) obj;
        if (compreensaoIdiomaPK == null) {
            if (other.compreensaoIdiomaPK != null) {
                return false;
            }
        } else if (!compreensaoIdiomaPK.equals(other.compreensaoIdiomaPK)) {
            return false;
        }
        return true;
    }

}
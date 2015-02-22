package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"SOBRE_O_GRUPO\"")
public class SobreOGrupo extends Entidade {

    private static final long serialVersionUID = -4195521719596138259L;

    @Id
    @Column(nullable = false)
    private String idioma;

    @Column(nullable = false, length = 4000)
    private String descricao;

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricaoDoIdioma(String idioma) {

        String descricaoDoIdioma;

        switch (idioma) {
        case "en":
            descricaoDoIdioma = "Ing";
            break;
        default:
            descricaoDoIdioma = "Port";
            break;
        }

        return descricaoDoIdioma;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idioma == null) ? 0 : idioma.hashCode());
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
        if (!(obj instanceof SobreOGrupo)) {
            return false;
        }
        SobreOGrupo other = (SobreOGrupo) obj;
        if (idioma == null) {
            if (other.idioma != null) {
                return false;
            }
        } else if (!idioma.equals(other.idioma)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SobreOGrupo [Idioma=" + getIdioma() + ", Descricao=" + getDescricao() + "]";
    }

}

package br.ufscar.rcms.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"IDIOMA\"")
public class Idioma extends Entidade {

    private static final long serialVersionUID = 3667531830943589983L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_idioma")
    private Long idIdioma;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compreensaoIdiomaPK.idioma")
    private List<CompreensaoIdioma> compreensaoIdiomas = new ArrayList<CompreensaoIdioma>();

    public Idioma() {
    }

    public Idioma(String descricao) {
        this.descricao = descricao;
    }

    public List<CompreensaoIdioma> getCompreensaoIdiomas() {
        return compreensaoIdiomas;
    }

    public void setCompreensaoIdiomas(List<CompreensaoIdioma> compreensaoIdiomas) {
        this.compreensaoIdiomas = compreensaoIdiomas;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Long idIdioma) {
        this.idIdioma = idIdioma;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idIdioma == null) ? 0 : idIdioma.hashCode());
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
        if (!(obj instanceof Idioma)) {
            return false;
        }
        Idioma other = (Idioma) obj;
        if (idIdioma == null) {
            if (other.idIdioma != null) {
                return false;
            }
        } else if (!idIdioma.equals(other.idIdioma)) {
            return false;
        }
        return true;
    }

}
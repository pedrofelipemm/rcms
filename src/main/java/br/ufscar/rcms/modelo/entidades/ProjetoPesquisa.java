package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ProjetoPesquisa extends Entidade{

    private static final long serialVersionUID = -9149061665883584009L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProjetoPesquisa;

    public Integer getIdProjetoPesquisa() {
        return idProjetoPesquisa;
    }

    public void setIdProjetoPesquisa(Integer idProjetoPesquisa) {
        this.idProjetoPesquisa = idProjetoPesquisa;
    }

}

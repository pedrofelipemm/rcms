package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Idioma extends Entidade{

    private static final long serialVersionUID = 3667531830943589983L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIdioma;

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }
}

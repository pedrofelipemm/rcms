package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"OUTRA_PRODUCAO_TECNICA\"")
public class OutraProducaoTecnica extends ProducaoTecnica {

    private static final long serialVersionUID = -8186618890429814323L;

    @Column
    private String natureza;

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }
}
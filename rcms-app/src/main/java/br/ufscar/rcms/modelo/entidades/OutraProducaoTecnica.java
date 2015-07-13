package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"OUTRA_PRODUCAO_TECNICA\"")
@ForeignKey(name = "fk_outra_producao_tecnica_producao_tecnica")
public class OutraProducaoTecnica extends ProducaoTecnica {

    private static final long serialVersionUID = -8186618890429814323L;

    @Column(name = "natureza")
    private String natureza;

    public OutraProducaoTecnica() {
    }

    public OutraProducaoTecnica(String titulo, List<CitacaoBibliografica> autores, Integer ano, String natureza) {

        super.setTitulo(titulo);
        super.setCitacaoBibliograficas(autores);
        super.setAno(ano);
        this.natureza = natureza;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }
}
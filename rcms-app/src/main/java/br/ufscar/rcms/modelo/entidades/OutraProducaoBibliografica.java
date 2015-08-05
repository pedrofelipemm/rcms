package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "outra_producao_bibliografica")
@ForeignKey(name = "fk_outra_producao_bibliografica_producao_bibliografica")
public class OutraProducaoBibliografica extends ProducaoBibliografica {

    private static final long serialVersionUID = -4621162924821882566L;

    @Column(name = "natureza")
    private String natureza;

    public OutraProducaoBibliografica() {
    }

    public OutraProducaoBibliografica(final String titulo, final Integer ano,
            final String natureza) {

        super.setTitulo(titulo);
        super.setAno(ano);
        this.natureza = natureza;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(final String natureza) {
        this.natureza = natureza;
    }
}
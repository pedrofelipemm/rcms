package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "processo_ou_tecnica")
@ForeignKey(name = "fk_processo_ou_tecnica_producao_tecnica")
public class ProcessoOuTecnica extends ProducaoTecnica {

    private static final long serialVersionUID = 6694121402955244752L;

    @Column(name = "natureza")
    private String natureza;

    public ProcessoOuTecnica() {
    }

    public ProcessoOuTecnica(final String titulo, final Integer ano,
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
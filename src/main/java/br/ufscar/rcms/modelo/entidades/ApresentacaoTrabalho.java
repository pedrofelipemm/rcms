package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"APRESENTACAO_TRABALHO\"")
@ForeignKey(name = "fk_apresentacao_trabalho_producao_bibliografica")
public class ApresentacaoTrabalho extends ProducaoBibliografica {

    private static final long serialVersionUID = 5668638568301910798L;

    @Column(name = "natureza")
    private String natureza;

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }
}
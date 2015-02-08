package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"RESUMO_EXPANDIDO_CONGRESSO\"")
public class ResumoExpandidoCongresso extends ProducaoBibliografica {

    private static final long serialVersionUID = -396352635868864895L;

    @Column
    private String doi;

    @Column
    private String nomeEvento;

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }
}
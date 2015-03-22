package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"TRABALHO_COMPLETO_CONGRESSO\"")
public class TrabalhoCompletoCongresso extends ProducaoBibliografica {

    private static final long serialVersionUID = -1822128070360441152L;

    @Column(name = "doi")
    private String doi;

    @Column(name = "nome_evento")
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
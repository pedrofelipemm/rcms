package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"RESUMO_CONGRESSO\"")
@ForeignKey(name = "fk_resumo_congresso_producao_bibliografica")
public class ResumoCongresso extends ProducaoBibliografica {

    private static final long serialVersionUID = -2563684451376971183L;

    @Column(name = "doi")
    private String doi;

    @Column(name = "nome_evento")
    private String nomeEvento;

    @Column(name = "numero")
    private Integer numero;

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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
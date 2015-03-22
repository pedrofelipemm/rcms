package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "\"ARTIGO_EM_PERIODICO\"")
public class ArtigoEmPeriodico extends ProducaoBibliografica {

    private static final long serialVersionUID = -6003079801862161779L;

    @Column(name = "doi")
    private String doi;

    @Column(name = "revista")
    private String revista;

    @Column(name = "numero")
    private Integer numero;

    public String getDoi() {
        return doi;
    }

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(String revista) {
        this.revista = revista;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
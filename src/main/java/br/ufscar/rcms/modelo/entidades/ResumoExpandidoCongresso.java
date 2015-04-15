package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"RESUMO_EXPANDIDO_CONGRESSO\"")
@ForeignKey(name = "fk_resumo_expandido_congresso_producao_bibliografica")
public class ResumoExpandidoCongresso extends ProducaoBibliografica {

    private static final long serialVersionUID = -396352635868864895L;

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
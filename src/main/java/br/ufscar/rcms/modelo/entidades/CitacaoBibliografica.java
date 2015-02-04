package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CITACAO_BIBLIOGRAFICA")
public class CitacaoBibliografica extends Entidade {

    private static final long serialVersionUID = 1883662481651131111L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCitacaoBibliografica;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pesquisador pesquisador;

    @Column(nullable = false, unique = true)
    private String nomeCitacao;

    public Integer getIdCitacaoBibliografica() {
        return idCitacaoBibliografica;
    }

    public void setIdCitacaoBibliografica(Integer idCitacaoBibliografica) {
        this.idCitacaoBibliografica = idCitacaoBibliografica;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public String getNomeCitacao() {
        return nomeCitacao;
    }

    public void setNomeCitacao(String nomeCitacao) {
        this.nomeCitacao = nomeCitacao;
    }
}
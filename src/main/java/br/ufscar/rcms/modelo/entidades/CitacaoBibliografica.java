package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"CITACAO_BIBLIOGRAFICA\"")
public class CitacaoBibliografica extends Entidade {

    private static final long serialVersionUID = 1883662481651131111L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCitacaoBibliografica;

    @ManyToOne(optional = false)
    private Pesquisador pesquisador;

    @Column(nullable = false)
    private String nomeCitacao;

    public CitacaoBibliografica() {
    }

    public CitacaoBibliografica(Pesquisador pesquisador, String nomeCitacao) {
        this.pesquisador = pesquisador;
        this.nomeCitacao = nomeCitacao;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idCitacaoBibliografica == null) ? 0 : idCitacaoBibliografica.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof CitacaoBibliografica)) {
            return false;
        }
        CitacaoBibliografica other = (CitacaoBibliografica) obj;
        if (idCitacaoBibliografica == null) {
            if (other.idCitacaoBibliografica != null) {
                return false;
            }
        } else if (!idCitacaoBibliografica.equals(other.idCitacaoBibliografica)) {
            return false;
        }
        return true;
    }
}
package br.ufscar.rcms.scorecard.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.ufscar.rcms.scorecard.util.JsonUtil;

@Entity
@Table(name = "citacao_bibliografica")
public class CitacaoBibliografica extends br.ufscar.rcms.scorecard.model.entity.Entity {

    private static final long serialVersionUID = 1883662481651131111L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_citacao_bibliografica")
    private Integer idCitacaoBibliografica;

    @ManyToOne(optional = true)
    @JoinColumn(name = "id_pesquisador", foreignKey = @ForeignKey(name = "fk_citacao_bibliografica_pesquisador"), nullable = true)
    private Pesquisador pesquisador;

    @Column(name = "nome_citacao", nullable = false)
    private String nomeCitacao;

    public CitacaoBibliografica() {/* Serialization */}

    public CitacaoBibliografica(final Pesquisador pesquisador, final String nomeCitacao) {
        this.pesquisador = pesquisador;
        this.nomeCitacao = nomeCitacao;
    }

    @Override
    public Long getId() {
        return getIdCitacaoBibliografica().longValue();
    }

    public Integer getIdCitacaoBibliografica() {
        return idCitacaoBibliografica;
    }

    public void setIdCitacaoBibliografica(final Integer idCitacaoBibliografica) {
        this.idCitacaoBibliografica = idCitacaoBibliografica;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(final Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public String getNomeCitacao() {
        return nomeCitacao;
    }

    public void setNomeCitacao(final String nomeCitacao) {
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
    public boolean equals(final Object obj) {
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

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
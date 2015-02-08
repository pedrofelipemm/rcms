package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"PREMIO_TITULO\"")
public class PremioTitulo extends Entidade {

    private static final long serialVersionUID = 3391459887339405825L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPremioTitulo;

    @ManyToOne(optional = false)
    private Pesquisador pesquisador;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private String descricao;

    public Integer getIdPremioTitulo() {
        return idPremioTitulo;
    }

    public void setIdPremioTitulo(Integer idPremioTitulo) {
        this.idPremioTitulo = idPremioTitulo;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPremioTitulo == null) ? 0 : idPremioTitulo.hashCode());
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
        if (!(obj instanceof PremioTitulo)) {
            return false;
        }
        PremioTitulo other = (PremioTitulo) obj;
        if (idPremioTitulo == null) {
            if (other.idPremioTitulo != null) {
                return false;
            }
        } else if (!idPremioTitulo.equals(other.idPremioTitulo)) {
            return false;
        }
        return true;
    }

}

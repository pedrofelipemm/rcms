package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AREA_ATUACAO")
public class AreaAtuacao extends Entidade {

    private static final long serialVersionUID = -3948561964306499761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAreaAtuacao;

    @Column(nullable = false)
    private String descricao;

    public Integer getIdAreaAtuacao() {
        return idAreaAtuacao;
    }

    public void setIdAreaAtuacao(Integer idAreaAtuacao) {
        this.idAreaAtuacao = idAreaAtuacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
        result = prime * result + ((idAreaAtuacao == null) ? 0 : idAreaAtuacao.hashCode());
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
        if (getClass() != obj.getClass()) {
            return false;
        }
        AreaAtuacao other = (AreaAtuacao) obj;
        if (descricao == null) {
            if (other.descricao != null) {
                return false;
            }
        } else if (!descricao.equals(other.descricao)) {
            return false;
        }
        if (idAreaAtuacao == null) {
            if (other.idAreaAtuacao != null) {
                return false;
            }
        } else if (!idAreaAtuacao.equals(other.idAreaAtuacao)) {
            return false;
        }
        return true;
    }

}

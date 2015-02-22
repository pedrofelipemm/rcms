package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"LINHA_PESQUISA\"")
public class LinhaPesquisa extends Entidade {

    private static final long serialVersionUID = 5307316270928132625L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLinhaPesquisa;

    @Column(nullable = false)
    private String descricao;

    public Long getIdLinhaPesquisa() {
        return idLinhaPesquisa;
    }

    public void setIdLinhaPesquisa(Long idLinhaPesquisa) {
        this.idLinhaPesquisa = idLinhaPesquisa;
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
        result = prime * result + ((idLinhaPesquisa == null) ? 0 : idLinhaPesquisa.hashCode());
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
        if (!(obj instanceof LinhaPesquisa)) {
            return false;
        }
        LinhaPesquisa other = (LinhaPesquisa) obj;
        if (idLinhaPesquisa == null) {
            if (other.idLinhaPesquisa != null) {
                return false;
            }
        } else if (!idLinhaPesquisa.equals(other.idLinhaPesquisa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Linha de Pesquisa [idLinhaPesquisa=" + idLinhaPesquisa + ", descricao=" + descricao + "]";
    }
}

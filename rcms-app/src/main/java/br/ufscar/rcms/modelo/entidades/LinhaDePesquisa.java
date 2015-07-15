package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "linha_pesquisa")
public class LinhaDePesquisa extends Entidade {

    private static final long serialVersionUID = 5307316270928132625L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_linha_de_pesquisa")
    private Long idLinhaDePesquisa;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    public Long getIdLinhaDePesquisa() {
        return idLinhaDePesquisa;
    }

    public void setIdLinhaDePesquisa(final Long idLinhaDePesquisa) {
        this.idLinhaDePesquisa = idLinhaDePesquisa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idLinhaDePesquisa == null) ? 0 : idLinhaDePesquisa.hashCode());
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
        if (!(obj instanceof LinhaDePesquisa)) {
            return false;
        }
        LinhaDePesquisa other = (LinhaDePesquisa) obj;
        if (idLinhaDePesquisa == null) {
            if (other.idLinhaDePesquisa != null) {
                return false;
            }
        } else if (!idLinhaDePesquisa.equals(other.idLinhaDePesquisa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Linha de Pesquisa [idLinhaDePesquisa=" + idLinhaDePesquisa + ", descricao=" + descricao + "]";
    }
}

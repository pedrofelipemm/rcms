package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"ESPECIALIZACAO_AREA_ATUACAO\"")
public class EspecializacaoAreaAtuacao extends Entidade {

    private static final long serialVersionUID = 5149973934587512084L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_especializacao")
    private int idEspecializacao;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToOne
    private SubAreaAtuacao subAreaAtuacao;

    public int getIdEspecializacao() {
        return idEspecializacao;
    }

    public void setIdEspecializacao(int idEspecializacao) {
        this.idEspecializacao = idEspecializacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public SubAreaAtuacao getSubAreaAtuacao() {
        return subAreaAtuacao;
    }

    public void setSubAreaAtuacao(SubAreaAtuacao subAreaAtuacao) {
        this.subAreaAtuacao = subAreaAtuacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
        if (!(obj instanceof EspecializacaoAreaAtuacao)) {
            return false;
        }
        EspecializacaoAreaAtuacao other = (EspecializacaoAreaAtuacao) obj;
        if (descricao == null) {
            if (other.descricao != null) {
                return false;
            }
        } else if (!descricao.equals(other.descricao)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Grande área: " + getSubAreaAtuacao().getAreaAtuacao().getGrandeAreaAtuacao().getDescricao()
                + " / Área: " + getSubAreaAtuacao().getAreaAtuacao().getDescricao() + " / Subárea: "
                + getSubAreaAtuacao().getDescricao() + " / Especialização: " + getDescricao();
    }
}

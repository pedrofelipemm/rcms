package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"AREA_ATUACAO\"")
public class AreaAtuacao extends Entidade {

    private static final long serialVersionUID = -3948561964306499761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area_atuacao")
    private Integer idAreaAtuacao;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_grande_area_atuacao", foreignKey = @ForeignKey(name = "fk_area_atuacao_grande_area_atuacao"))
    private GrandeAreaAtuacao grandeAreaAtuacao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<SubAreaAtuacao> subAreasAtuacao;

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

    public List<SubAreaAtuacao> getSubAreasAtuacao() {
        return subAreasAtuacao;
    }

    public void setSubAreasAtuacao(List<SubAreaAtuacao> subAreasAtuacao) {
        this.subAreasAtuacao = subAreasAtuacao;
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

    @Override
    public String toString() {
        return "AreaAtuacao [Id=" + getIdAreaAtuacao() + ", Descricao=" + getDescricao() + "]";
    }

	public GrandeAreaAtuacao getGrandeAreaAtuacao() {
		return grandeAreaAtuacao;
	}

	public void setGrandeAreaAtuacao(GrandeAreaAtuacao grandeAreaAtuacao) {
		this.grandeAreaAtuacao = grandeAreaAtuacao;
	}

}

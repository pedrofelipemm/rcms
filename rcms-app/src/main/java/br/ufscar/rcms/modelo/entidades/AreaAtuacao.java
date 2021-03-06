package br.ufscar.rcms.modelo.entidades;

import java.util.ArrayList;
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
@Table(name = "area_atuacao")
public class AreaAtuacao extends Entidade {

    private static final long serialVersionUID = -3948561964306499761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_area_atuacao")
    private long idAreaAtuacao;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "id_grande_area_atuacao", foreignKey = @ForeignKey(name = "fk_area_atuacao_grande_area_atuacao"))
    private GrandeAreaAtuacao grandeAreaAtuacao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy ="areaAtuacao")
    private List<SubAreaAtuacao> subAreasAtuacao = new ArrayList<SubAreaAtuacao>();

    public long getIdAreaAtuacao() {
        return idAreaAtuacao;
    }

    public void setIdAreaAtuacao(final long idAreaAtuacao) {
        this.idAreaAtuacao = idAreaAtuacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }

    public List<SubAreaAtuacao> getSubAreasAtuacao() {
        return subAreasAtuacao;
    }

    public void setSubAreasAtuacao(final List<SubAreaAtuacao> subAreasAtuacao) {
        this.subAreasAtuacao = subAreasAtuacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((descricao == null) ? 0 : descricao.hashCode());
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
        if (!(obj instanceof AreaAtuacao)) {
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
        return true;
    }

    @Override
    public String toString() {
        return "AreaAtuacao [Id=" + getIdAreaAtuacao() + ", Descricao=" + getDescricao() + "]";
    }

    public GrandeAreaAtuacao getGrandeAreaAtuacao() {
        return grandeAreaAtuacao;
    }

    public void setGrandeAreaAtuacao(final GrandeAreaAtuacao grandeAreaAtuacao) {
        this.grandeAreaAtuacao = grandeAreaAtuacao;
    }

    public void addSubAreaAtuacao(final SubAreaAtuacao subAreaAtuacao) {
        subAreasAtuacao.add(subAreaAtuacao);
    }
}

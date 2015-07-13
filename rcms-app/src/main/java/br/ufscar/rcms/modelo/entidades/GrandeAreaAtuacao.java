package br.ufscar.rcms.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"GRANDE_AREA_ATUACAO\"")
public class GrandeAreaAtuacao extends Entidade {

    private static final long serialVersionUID = -3303227387678912075L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_grande_area_atuacao")
    private Integer idGrandeAreaAtuacao;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "grandeAreaAtuacao")
    private List<AreaAtuacao> areasDeAtuacao = new ArrayList<AreaAtuacao>();

    public Integer getIdGrandeAreaAtuacao() {
        return idGrandeAreaAtuacao;
    }

    public void setIdGrandeAreaAtuacao(Integer idGrandeAreaAtuacao) {
        this.idGrandeAreaAtuacao = idGrandeAreaAtuacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public List<AreaAtuacao> getAreasDeAtuacao() {
        return areasDeAtuacao;
    }

    public void setAreasDeAtuacao(List<AreaAtuacao> areasDeAtuacao) {
        this.areasDeAtuacao = areasDeAtuacao;
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
        if (!(obj instanceof GrandeAreaAtuacao)) {
            return false;
        }
        GrandeAreaAtuacao other = (GrandeAreaAtuacao) obj;
        if (descricao == null) {
            if (other.descricao != null) {
                return false;
            }
        } else if (!descricao.equals(other.descricao)) {
            return false;
        }
        return true;
    }

    public void addAreaAtuacao(AreaAtuacao areaAtuacao) {
        areasDeAtuacao.add(areaAtuacao);
    }
}

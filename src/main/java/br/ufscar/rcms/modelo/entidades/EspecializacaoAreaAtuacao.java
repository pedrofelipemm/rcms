package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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
    @JoinColumn(name = "id_sub_area_atuacao", foreignKey = @ForeignKey(name = "fk_especializacao_area_atuacao_sub_area_atuacao"))
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
    public String toString() {
        return "Grande área: " + this.getSubAreaAtuacao().getAreaAtuacao().getGrandeAreaAtuacao().getDescricao()
                +
				" / Área: " + this.getSubAreaAtuacao().getAreaAtuacao().getDescricao() +
				" / Subárea: " + this.getSubAreaAtuacao().getDescricao() +
				" / Especialização: " + this.getDescricao();
    }
}

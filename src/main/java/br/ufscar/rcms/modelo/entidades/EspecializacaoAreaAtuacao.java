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
	private int id;
	@Column(nullable = false)
	private String descricao;

	@ManyToOne
	private SubAreaAtuacao subAreaAtuacao;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
	public String toString(){
		return "Grande área: " + this.getSubAreaAtuacao().getAreaAtuacao().getGrandeAreaAtuacao().getDescricao() +
				" / Área: " + this.getSubAreaAtuacao().getAreaAtuacao().getDescricao() +
				" / Subárea: " + this.getSubAreaAtuacao().getDescricao() +
				" / Especialização: " + this.getDescricao();
	}
}

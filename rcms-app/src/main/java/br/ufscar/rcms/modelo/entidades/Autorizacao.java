package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autorizacao")
public class Autorizacao extends Entidade{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autorizacao")
	private long idAutorizacao;

	private String nomeAutorizacao;
	
	private String descricao;

	public Autorizacao() {}

	public String getNomeAutorizacao() {
		return nomeAutorizacao;
	}

	public void setNomeAutorizacao(final String nomeAutorizacao) {
		this.nomeAutorizacao = nomeAutorizacao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}



}

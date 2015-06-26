package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"AUTORIZACAO\"")
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

	public Autorizacao() {}

	public String getNomeAutorizacao() {
		return nomeAutorizacao;
	}

	public void setNomeAutorizacao(String nomeAutorizacao) {
		this.nomeAutorizacao = nomeAutorizacao;
	}
	


}

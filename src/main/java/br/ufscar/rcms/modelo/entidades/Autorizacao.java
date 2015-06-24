package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "\"AUTORIZACAO\"")
public class Autorizacao {
	
	@Id
	private String nome;

	public Autorizacao() {}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}


}

package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RESUMO_CONGRESSO")
public class ResumoCongresso extends ProducaoBibliografica {

	private static final long serialVersionUID = -2563684451376971183L;

	@Column()
	private String doi;
	
	@Column()
	private String nomeEvento;
	
	@Column()
	private Integer numero;

	public String getDoi() {
		return doi;
	}

	public void setDoi(String doi) {
		this.doi = doi;
	}

	public String getNomeEvento() {
		return nomeEvento;
	}

	public void setNomeEvento(String nomeEvento) {
		this.nomeEvento = nomeEvento;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	
}

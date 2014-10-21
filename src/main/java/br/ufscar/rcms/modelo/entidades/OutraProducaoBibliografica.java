package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "OUTRA_PRODUCAO_BIBLIOGRAFICA")
public class OutraProducaoBibliografica extends ProducaoBibliografica {

	private static final long serialVersionUID = -4621162924821882566L;

	@Column()
	private String natureza;

	public String getNatureza() {
		return natureza;
	}

	public void setNatureza(String natureza) {
		this.natureza = natureza;
	}
	
}

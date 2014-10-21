package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PROJETO_PESQUISA_MIDIA")
public class ProjetoPesquisaMidia extends Entidade {

	private static final long serialVersionUID = 3759933099478534637L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProjetoPesquisaMidia;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProjetoPesquisa projetoPesquisa;

	@Column(nullable = false)
    private byte[] arquivo;

	public Integer getIdProjetoPesquisaMidia() {
		return idProjetoPesquisaMidia;
	}

	public void setIdProjetoPesquisaMidia(Integer idProjetoPesquisaMidia) {
		this.idProjetoPesquisaMidia = idProjetoPesquisaMidia;
	}

	public ProjetoPesquisa getProjetoPesquisa() {
		return projetoPesquisa;
	}

	public void setProjetoPesquisa(ProjetoPesquisa projetoPesquisa) {
		this.projetoPesquisa = projetoPesquisa;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}

}

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
@Table(name = "link_midia")
public class LinkMidia extends Entidade {

	private static final long serialVersionUID = -1842438477674970448L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_link_midia")
    private Integer idLinkMidia;
	
	@ManyToOne(optional = false)
    @JoinColumn(name = "id_projeto_pesquisa", foreignKey = @ForeignKey(name = "fk_link_midia_projeto_pesquisa"))
    private ProjetoPesquisa projetoPesquisa;
	
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getIdLinkMidia() {
		return idLinkMidia;
	}

	public void setIdLinkMidia(Integer idLinkMidia) {
		this.idLinkMidia = idLinkMidia;
	}

	public ProjetoPesquisa getProjetoPesquisa() {
		return projetoPesquisa;
	}

	public void setProjetoPesquisa(ProjetoPesquisa projetoPesquisa) {
		this.projetoPesquisa = projetoPesquisa;
	}
	
}

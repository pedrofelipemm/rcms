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
@Table(name = "PESQUISADOR_IDIOMA")
public class Idioma extends Entidade{

	private static final long serialVersionUID = 3667531830943589983L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPesquisadorIdioma;
    
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pesquisador pesquisador;
    
    @Column(nullable = false)
    private String descricaoIdioma;
    
	@Column(nullable = false)
    private String proficiencia;

	public Integer getIdPesquisadorIdioma() {
        return idPesquisadorIdioma;
    }

	public void setIdPesquisadorIdioma(Integer idPesquisadorIdioma) {
		this.idPesquisadorIdioma = idPesquisadorIdioma;
	}

    public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public String getDescricaoIdioma() {
		return descricaoIdioma;
	}

	public void setDescricaoIdioma(String descricaoIdioma) {
		this.descricaoIdioma = descricaoIdioma;
	}

	public String getProficiencia() {
		return proficiencia;
	}

	public void setProficiencia(String proficiencia) {
		this.proficiencia = proficiencia;
	}
}

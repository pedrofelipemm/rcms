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
@Table(name = "PARTICIPACAO_EVENTO")
public class ParticipacaoEvento extends Entidade {

	private static final long serialVersionUID = -1118426502727782681L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPesquisadorParticipacaoEvento;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pesquisador pesquisador;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Integer ano;

	public Integer getIdPesquisadorParticipacaoEvento() {
		return idPesquisadorParticipacaoEvento;
	}

	public void setIdPesquisadorParticipacaoEvento(
			Integer idPesquisadorParticipacaoEvento) {
		this.idPesquisadorParticipacaoEvento = idPesquisadorParticipacaoEvento;
	}

	public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

}

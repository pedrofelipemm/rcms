package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PESQUISADORES")
public class Pesquisador extends Entidade{

	private static final long serialVersionUID = 8459435679917888175L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idPesquisador;

	@Column(nullable = false)
	private String nome;

	@Column(nullable = false)
	private String codigoLattes;

	@Column(nullable = false)
	private String resumoProfissional;


	public Integer getIdPesquisador() {

		return idPesquisador;
	}

	public void setIdPesquisador(Integer idPesquisador) {

		this.idPesquisador = idPesquisador;
	}

	public String getNome() {

		return nome;
	}

	public void setNome(String nome) {

		this.nome = nome;
	}

	public String getCodigoLattes() {

		return codigoLattes;
	}

	public void setCodigoLattes(String codigoLattes) {

		this.codigoLattes = codigoLattes;
	}

	public String getResumoProfissional() {

		return resumoProfissional;
	}

	public void setResumoProfissional(String resumoProfissional) {

		this.resumoProfissional = resumoProfissional;
	}

	@Override
	public int hashCode() {

		final int prime = 31;
		int result = 1;
		result = prime * result + ((idPesquisador == null) ? 0 : idPesquisador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Pesquisador)) {
			return false;
		}
		Pesquisador other = (Pesquisador) obj;
		if (idPesquisador == null) {
			if (other.idPesquisador != null) {
				return false;
			}
		} else if (!idPesquisador.equals(other.idPesquisador)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {

		return "Pesquisador [idPesquisador=" + idPesquisador + ", nome=" + nome + ", codigoLattes="
				+ codigoLattes + ", resumoProfissional=" + resumoProfissional + "]";
	}

}

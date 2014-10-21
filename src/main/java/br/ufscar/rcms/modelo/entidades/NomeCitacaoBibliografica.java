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
@Table(name = "PESQUISADOR_NOME_CITACAO_BIBLIOGRAFICA")
public class NomeCitacaoBibliografica extends Entidade{

    private static final long serialVersionUID = 1883662481651131111L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPesquisadorNomeCitacao;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pesquisador pesquisador;

    @Column(nullable = false, unique = true)
    private String nomeCitacao;

	public Integer getIdPesquisadorNomeCitacao() {
        return idPesquisadorNomeCitacao;
    }

    public void setIdPesquisadorNomeCitacao(Integer idPesquisadorNomeCitacao) {
        this.idPesquisadorNomeCitacao = idPesquisadorNomeCitacao;
    }
    
    public Pesquisador getPesquisador() {
		return pesquisador;
	}

	public void setPesquisador(Pesquisador pesquisador) {
		this.pesquisador = pesquisador;
	}

	public String getNomeCitacao() {
		return nomeCitacao;
	}

	public void setNomeCitacao(String nomeCitacao) {
		this.nomeCitacao = nomeCitacao;
	}

}

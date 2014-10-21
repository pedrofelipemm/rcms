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
@Table(name = "PREMIO_TITULO")
public class PremioTitulo extends Entidade {

    private static final long serialVersionUID = 3391459887339405825L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPesquisadorPremioTitulo;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pesquisador pesquisador;

    @Column(nullable = false)
    private Integer ano;

    @Column(nullable = false)
    private String descricao;

    public Integer getIdPesquisadorPremioTitulo() {
        return idPesquisadorPremioTitulo;
    }

    public void setIdPesquisadorPremioTitulo(Integer idPesquisadorPremioTitulo) {
        this.idPesquisadorPremioTitulo = idPesquisadorPremioTitulo;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

}

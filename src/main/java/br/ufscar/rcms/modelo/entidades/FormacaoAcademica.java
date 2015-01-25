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
@Table(name = "FORMACAO_ACADEMICA")
public class FormacaoAcademica extends Entidade {

    private static final long serialVersionUID = -1883554721627714716L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPesquisadorFormacao;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pesquisador pesquisador;

    @Column(nullable = false)
    private Integer anoInicio;

    @Column(nullable = false)
    private Integer anoConclusao;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String nomeInstituicao;

    @Column(nullable = false)
    private String descricao;

    public Integer getIdPesquisadorFormacao() {
        return idPesquisadorFormacao;
    }

    public void setIdPesquisadorFormacao(Integer idPesquisadorFormacao) {
        this.idPesquisadorFormacao = idPesquisadorFormacao;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public Integer getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(Integer anoInicio) {
        this.anoInicio = anoInicio;
    }

    public Integer getAnoConclusao() {
        return anoConclusao;
    }

    public void setAnoConclusao(Integer anoConclusao) {
        this.anoConclusao = anoConclusao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

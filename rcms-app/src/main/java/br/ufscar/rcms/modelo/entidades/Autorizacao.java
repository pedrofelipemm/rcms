package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "autorizacao")
public class Autorizacao extends Entidade {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_autorizacao")
    private Long idAutorizacao;

    private String nomeAutorizacao;

    private String descricao;

    public Autorizacao() {/* Serialization */}

    public Autorizacao(final String nomeAutorizacao, final String descricao) {
        this.nomeAutorizacao = nomeAutorizacao;
        this.descricao = descricao;
    }

    public String getNomeAutorizacao() {
        return nomeAutorizacao;
    }

    public void setNomeAutorizacao(final String nomeAutorizacao) {
        this.nomeAutorizacao = nomeAutorizacao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
    }
}
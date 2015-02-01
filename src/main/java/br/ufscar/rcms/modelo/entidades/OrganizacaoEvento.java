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
@Table(name = "ORGANIZACAO_EVENTO")
public class OrganizacaoEvento extends Entidade {

    private static final long serialVersionUID = -4704509426426228127L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrganizacaoEvento;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Pesquisador pesquisador;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private String natureza;

    @Column(nullable = false)
    private Integer ano;

    public Integer getIdOrganizacaoEvento() {
        return idOrganizacaoEvento;
    }

    public void setIdOrganizacaoEvento(Integer idOrganizacaoEvento) {
        this.idOrganizacaoEvento = idOrganizacaoEvento;
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

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(String natureza) {
        this.natureza = natureza;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }
}
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
@Table(name = "organizacao_evento")
public class OrganizacaoEvento extends Entidade {

    private static final long serialVersionUID = -4704509426426228127L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_organizacao_evento")
    private Integer idOrganizacaoEvento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_pesquisador", foreignKey = @ForeignKey(name = "fk_organizacao_evento_pesquisador"))
    private Pesquisador pesquisador;

    @Column(name = "titulo", nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String titulo;

    @Column(name = "natureza", nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String natureza;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    public OrganizacaoEvento() {
    }

    public OrganizacaoEvento(final Pesquisador pesquisador, final String titulo, final String natureza, final Integer ano) {
        this.pesquisador = pesquisador;
        this.titulo = titulo;
        this.natureza = natureza;
        this.ano = ano;
    }

    public Integer getIdOrganizacaoEvento() {
        return idOrganizacaoEvento;
    }

    public void setIdOrganizacaoEvento(final Integer idOrganizacaoEvento) {
        this.idOrganizacaoEvento = idOrganizacaoEvento;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(final Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(final String natureza) {
        this.natureza = natureza;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(final Integer ano) {
        this.ano = ano;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idOrganizacaoEvento == null) ? 0 : idOrganizacaoEvento.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof OrganizacaoEvento)) {
            return false;
        }
        OrganizacaoEvento other = (OrganizacaoEvento) obj;
        if (idOrganizacaoEvento == null) {
            if (other.idOrganizacaoEvento != null) {
                return false;
            }
        } else if (!idOrganizacaoEvento.equals(other.idOrganizacaoEvento)) {
            return false;
        }
        return true;
    }
}
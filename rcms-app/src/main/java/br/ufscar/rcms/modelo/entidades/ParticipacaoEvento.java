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
@Table(name = "participacao_evento")
public class ParticipacaoEvento extends Entidade {

    private static final long serialVersionUID = -1118426502727782681L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_participacao_evento")
    private Integer idParticipacaoEvento;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_pesquisador", foreignKey = @ForeignKey(name = "fk_participacao_evento_pesquisador"))
    private Pesquisador pesquisador;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "ano", nullable = false)
    private Integer ano;

    public ParticipacaoEvento() {
    }

    public ParticipacaoEvento(final Pesquisador pesquisador, final String titulo, final Integer ano) {
        this.pesquisador = pesquisador;
        this.titulo = titulo;
        this.ano = ano;
    }

    public Integer getIdParticipacaoEvento() {
        return idParticipacaoEvento;
    }

    public void setIdParticipacaoEvento(final Integer idParticipacaoEvento) {
        this.idParticipacaoEvento = idParticipacaoEvento;
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
        result = prime * result + ((idParticipacaoEvento == null) ? 0 : idParticipacaoEvento.hashCode());
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
        if (!(obj instanceof ParticipacaoEvento)) {
            return false;
        }
        ParticipacaoEvento other = (ParticipacaoEvento) obj;
        if (idParticipacaoEvento == null) {
            if (other.idParticipacaoEvento != null) {
                return false;
            }
        } else if (!idParticipacaoEvento.equals(other.idParticipacaoEvento)) {
            return false;
        }
        return true;
    }
}
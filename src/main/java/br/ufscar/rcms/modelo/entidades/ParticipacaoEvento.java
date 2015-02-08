package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"PARTICIPACAO_EVENTO\"")
public class ParticipacaoEvento extends Entidade {

    private static final long serialVersionUID = -1118426502727782681L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idParticipacaoEvento;

    @ManyToOne(optional = false)
    private Pesquisador pesquisador;

    @Column(nullable = false)
    private String titulo;

    @Column(nullable = false)
    private Integer ano;

    public Integer getIdParticipacaoEvento() {
        return idParticipacaoEvento;
    }

    public void setIdParticipacaoEvento(Integer idParticipacaoEvento) {
        this.idParticipacaoEvento = idParticipacaoEvento;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idParticipacaoEvento == null) ? 0 : idParticipacaoEvento.hashCode());
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
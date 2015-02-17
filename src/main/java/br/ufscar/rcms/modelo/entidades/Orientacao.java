package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"ORIENTACAO\"")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Orientacao extends Entidade {

    private static final long serialVersionUID = 7468560304766568173L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrientacao;

    @ManyToOne(optional = false)
    private Pesquisador pesquisador;

    @Column(nullable = false)
    private String nomeDoAluno;

    @Column(nullable = false)
    private String instituicao;

    @Column
    private String agenciaDeFomento;

    @Column
    private String tipoDeOrientacao;

    @Column
    private String situacao;

    public Integer getIdOrientacao() {
        return idOrientacao;
    }

    public void setIdOrientacao(Integer idOrientacao) {
        this.idOrientacao = idOrientacao;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public String getNomeDoAluno() {
        return nomeDoAluno;
    }

    public void setNomeDoAluno(String nomeDoAluno) {
        this.nomeDoAluno = nomeDoAluno;
    }

    public String getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(String instituicao) {
        this.instituicao = instituicao;
    }

    public String getAgenciaDeFomento() {
        return agenciaDeFomento;
    }

    public void setAgenciaDeFomento(String agenciaDeFomento) {
        this.agenciaDeFomento = agenciaDeFomento;
    }

    public String getTipoDeOrientacao() {
        return tipoDeOrientacao;
    }

    public void setTipoDeOrientacao(String tipoDeOrientacao) {
        this.tipoDeOrientacao = tipoDeOrientacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idOrientacao == null) ? 0 : idOrientacao.hashCode());
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
        if (!(obj instanceof Orientacao)) {
            return false;
        }
        Orientacao other = (Orientacao) obj;
        if (idOrientacao == null) {
            if (other.idOrientacao != null) {
                return false;
            }
        } else if (!idOrientacao.equals(other.idOrientacao)) {
            return false;
        }
        return true;
    }
}
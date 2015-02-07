package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
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
    private Long idFormacaoAcademica;

    @ManyToOne(optional = false)
    private Pesquisador pesquisador;

    @Column(nullable = false)
    private Integer anoInicio;

    @Column(nullable = false)
    private Integer anoConclusao;

    @Column(nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String tipo;

    @Column(nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String nomeInstituicao;

    @Column(nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String descricao;

    public Long getIdFormacaoAcademica() {
        return idFormacaoAcademica;
    }

    public void setIdFormacaoAcademica(Long idFormacaoAcademica) {
        this.idFormacaoAcademica = idFormacaoAcademica;
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idFormacaoAcademica == null) ? 0 : idFormacaoAcademica.hashCode());
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
        if (!(obj instanceof FormacaoAcademica)) {
            return false;
        }
        FormacaoAcademica other = (FormacaoAcademica) obj;
        if (idFormacaoAcademica == null) {
            if (other.idFormacaoAcademica != null) {
                return false;
            }
        } else if (!idFormacaoAcademica.equals(other.idFormacaoAcademica)) {
            return false;
        }
        return true;
    }

}
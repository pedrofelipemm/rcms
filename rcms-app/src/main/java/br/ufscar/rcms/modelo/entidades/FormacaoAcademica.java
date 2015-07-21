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
@Table(name = "formacao_academica")
public class FormacaoAcademica extends Entidade {

    private static final long serialVersionUID = -1883554721627714716L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_formacao_academica")
    private Long idFormacaoAcademica;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_pesquisador", foreignKey = @ForeignKey(name = "fk_formacao_academica_pesquisador"))
    private Pesquisador pesquisador;

    @Column(name = "ano_inicio", nullable = false)
    private Integer anoInicio;

    @Column(name = "ano_conclusao", nullable = false)
    private Integer anoConclusao;

    @Column(name = "tipo", nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String tipo;

    @Column(name = "nome_instituicao", nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String nomeInstituicao;

    @Column(name = "descricao", nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String descricao;

    public Long getIdFormacaoAcademica() {
        return idFormacaoAcademica;
    }

    public void setIdFormacaoAcademica(final Long idFormacaoAcademica) {
        this.idFormacaoAcademica = idFormacaoAcademica;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(final Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public Integer getAnoInicio() {
        return anoInicio;
    }

    public void setAnoInicio(final Integer anoInicio) {
        this.anoInicio = anoInicio;
    }

    public Integer getAnoConclusao() {
        return anoConclusao;
    }

    public void setAnoConclusao(final Integer anoConclusao) {
        this.anoConclusao = anoConclusao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(final String tipo) {
        this.tipo = tipo;
    }

    public String getNomeInstituicao() {
        return nomeInstituicao;
    }

    public void setNomeInstituicao(final String nomeInstituicao) {
        this.nomeInstituicao = nomeInstituicao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
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
    public boolean equals(final Object obj) {
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
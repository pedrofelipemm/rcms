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
@Table(name = "PESQUISADOR_IDIOMA")
public class Idioma extends Entidade {

    private static final long serialVersionUID = 3667531830943589983L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idIdioma;

//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private Pesquisador pesquisador;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String proficiencia;

    public Integer getIdIdioma() {
        return idIdioma;
    }

    public void setIdIdioma(Integer idIdioma) {
        this.idIdioma = idIdioma;
    }

//    public Pesquisador getPesquisador() {
//        return pesquisador;
//    }
//
//    public void setPesquisador(Pesquisador pesquisador) {
//        this.pesquisador = pesquisador;
//    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProficiencia() {
        return proficiencia;
    }

    public void setProficiencia(String proficiencia) {
        this.proficiencia = proficiencia;
    }
}

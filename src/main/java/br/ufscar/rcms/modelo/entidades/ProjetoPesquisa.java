package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PROJETO_PESQUISA")
public class ProjetoPesquisa extends Entidade {

    private static final long serialVersionUID = -9149061665883584009L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProjetoPesquisa;

    @Column(nullable = false)
    private String nome;

    @Column()
    private String descricao;

    @Column()
    private Integer anoInicio;

    @Column()
    private Integer anoConclusao;

    @Column()
    private String agenciaDeFomento;

    public Integer getIdProjetoPesquisa() {
        return idProjetoPesquisa;
    }

    public void setIdProjetoPesquisa(Integer idProjetoPesquisa) {
        this.idProjetoPesquisa = idProjetoPesquisa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
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

    public String getAgenciaDeFomento() {
        return agenciaDeFomento;
    }

    public void setAgenciaDeFomento(String agenciaDeFomento) {
        this.agenciaDeFomento = agenciaDeFomento;
    }
}

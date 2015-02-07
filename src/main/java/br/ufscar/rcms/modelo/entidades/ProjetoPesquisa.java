package br.ufscar.rcms.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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

    @Column
    private String descricao;

    @Column
    private Integer anoInicio;

    @Column
    private Integer anoConclusao;

    @Column
    private String agenciaDeFomento;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    private List<Producao> producoes = new ArrayList<Producao>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projetoPesquisa")
    private List<Midia> midia;

    public List<Producao> getProducoes() {
        return producoes;
    }

    public void setProducoes(List<Producao> producoes) {
        this.producoes = producoes;
    }

    public List<Midia> getMidia() {
        return midia;
    }

    public void setMidia(List<Midia> midia) {
        this.midia = midia;
    }

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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idProjetoPesquisa == null) ? 0 : idProjetoPesquisa.hashCode());
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
        if (!(obj instanceof ProjetoPesquisa)) {
            return false;
        }
        ProjetoPesquisa other = (ProjetoPesquisa) obj;
        if (idProjetoPesquisa == null) {
            if (other.idProjetoPesquisa != null) {
                return false;
            }
        } else if (!idProjetoPesquisa.equals(other.idProjetoPesquisa)) {
            return false;
        }
        return true;
    }

}
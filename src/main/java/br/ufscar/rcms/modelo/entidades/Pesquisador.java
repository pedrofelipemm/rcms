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
@Table(name = "PESQUISADORES")
public class Pesquisador extends Administrador {

    private static final long serialVersionUID = 8459435679917888175L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPesquisador;

    @Column(nullable = false)
    private String codigoLattes;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String enderecoProfissional;

    private byte[] foto;

    @Column(nullable = false)
    private String resumoProfissional;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PesquisadorNomeCitacao> pesquisadorNomeCitacao;

    @OneToMany(cascade = CascadeType.ALL)
    private List<PesquisadorFormacao> pesquisadorFormacoes = new ArrayList<PesquisadorFormacao>();

    @ManyToMany
    private List<AreaAtuacao> areaAtuacoes = new ArrayList<AreaAtuacao>();

    @ManyToMany
    private List<Idioma> idiomas = new ArrayList<Idioma>();

    @ManyToMany
    private List<Publicacao> publicacoes = new ArrayList<Publicacao>();

    @ManyToMany
    private List<ProjetoPesquisa> projetosPesquisa = new ArrayList<ProjetoPesquisa>();
    // TODO
    // @OneToMany
    // private CitacaoBibliografica citacaoBibliografica;

    public Integer getIdPesquisador() {
        return idPesquisador;
    }

    public void setIdPesquisador(Integer idPesquisador) {
        this.idPesquisador = idPesquisador;
    }

    public String getCodigoLattes() {
        return codigoLattes;
    }

    public void setCodigoLattes(String codigoLattes) {
        this.codigoLattes = codigoLattes;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEnderecoProfissional() {
        return enderecoProfissional;
    }

    public void setEnderecoProfissional(String enderecoProfissional) {
        this.enderecoProfissional = enderecoProfissional;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getResumoProfissional() {
        return resumoProfissional;
    }

    public void setResumoProfissional(String resumoProfissional) {
        this.resumoProfissional = resumoProfissional;
    }

    public List<PesquisadorNomeCitacao> getPesquisadorNomeCitacao() {
        return pesquisadorNomeCitacao;
    }

    public void setPesquisadorNomeCitacao(List<PesquisadorNomeCitacao> pesquisadorNomeCitacao) {
        this.pesquisadorNomeCitacao = pesquisadorNomeCitacao;
    }

    public List<PesquisadorFormacao> getPesquisadorFormacoes() {
        return pesquisadorFormacoes;
    }

    public void setPesquisadorFormacoes(List<PesquisadorFormacao> pesquisadorFormacoes) {
        this.pesquisadorFormacoes = pesquisadorFormacoes;
    }

    public List<AreaAtuacao> getAreaAtuacoes() {
        return areaAtuacoes;
    }

    public void setAreaAtuacoes(List<AreaAtuacao> areaAtuacoes) {
        this.areaAtuacoes = areaAtuacoes;
    }

    public List<Idioma> getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(List<Idioma> idiomas) {
        this.idiomas = idiomas;
    }

    public List<Publicacao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<Publicacao> publicacoes) {
        this.publicacoes = publicacoes;
    }

    public List<ProjetoPesquisa> getProjetosPesquisa() {
        return projetosPesquisa;
    }

    public void setProjetosPesquisa(List<ProjetoPesquisa> projetosPesquisa) {
        this.projetosPesquisa = projetosPesquisa;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idPesquisador == null) ? 0 : idPesquisador.hashCode());
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
        if (!(obj instanceof Pesquisador)) {
            return false;
        }
        Pesquisador other = (Pesquisador) obj;
        if (idPesquisador == null) {
            if (other.idPesquisador != null) {
                return false;
            }
        } else if (!idPesquisador.equals(other.idPesquisador)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pesquisador [idPesquisador=" + idPesquisador + ", codigoLattes=" + codigoLattes + ", nome=" + nome
                + "]";
    }

}

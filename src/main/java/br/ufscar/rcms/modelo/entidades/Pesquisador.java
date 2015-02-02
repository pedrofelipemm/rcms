package br.ufscar.rcms.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "PESQUISADOR")
public class Pesquisador extends Usuario {

    private static final long serialVersionUID = 7468024654193724256L;

    @Column(nullable = false, unique = true)
    private String codigoLattes;

    @Column
    private byte[] foto;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pesquisador")
    private Endereco endereco;

    @Column(nullable = false)
    private String resumoProfissional;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CitacaoBibliografica> nomeCitacao;

    @OneToMany(cascade = CascadeType.ALL)
    private List<FormacaoAcademica> formacoes = new ArrayList<FormacaoAcademica>();

    @ManyToMany(cascade = CascadeType.PERSIST)
    private List<Idioma> idiomas = new ArrayList<Idioma>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<PremioTitulo> premios = new ArrayList<PremioTitulo>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<ParticipacaoEvento> participacaoEventos = new ArrayList<ParticipacaoEvento>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrganizacaoEvento> organizacaoEventos = new ArrayList<OrganizacaoEvento>();

    @ManyToMany
    private List<AreaAtuacao> areaAtuacoes = new ArrayList<AreaAtuacao>();

    @ManyToMany
    private List<Producao> producoes = new ArrayList<Producao>();

    @ManyToMany
    private List<ProjetoPesquisa> projetosPesquisa = new ArrayList<ProjetoPesquisa>();

    public String getCodigoLattes() {
        return codigoLattes;
    }

    public void setCodigoLattes(String codigoLattes) {
        this.codigoLattes = codigoLattes;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public byte[] getFoto() {

        if (foto == null) {
            return new byte[0];
        }

        byte[] copyOfFoto = new byte[foto.length];
        System.arraycopy(foto, 0, copyOfFoto, 0, foto.length);

        return copyOfFoto;
    }

    public void setFoto(byte[] foto) {

        if (foto == null) {
            return;
        }

        byte[] copyOfFoto = new byte[foto.length];
        System.arraycopy(foto, 0, copyOfFoto, 0, foto.length);

        this.foto = copyOfFoto;
    }

    public String getResumoProfissional() {
        return resumoProfissional;
    }

    public void setResumoProfissional(String resumoProfissional) {
        this.resumoProfissional = resumoProfissional;
    }

    public List<CitacaoBibliografica> getNomeCitacao() {
        return nomeCitacao;
    }

    public void setNomeCitacao(List<CitacaoBibliografica> nomeCitacao) {
        this.nomeCitacao = nomeCitacao;
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

    public List<FormacaoAcademica> getFormacoes() {
        return formacoes;
    }

    public void setFormacoes(List<FormacaoAcademica> formacoes) {
        this.formacoes = formacoes;
    }

    public List<PremioTitulo> getPremios() {
        return premios;
    }

    public void setPremios(List<PremioTitulo> premios) {
        this.premios = premios;
    }

    public List<ParticipacaoEvento> getParticipacaoEventos() {
        return participacaoEventos;
    }

    public void setParticipacaoEventos(List<ParticipacaoEvento> participacaoEventos) {
        this.participacaoEventos = participacaoEventos;
    }

    public List<OrganizacaoEvento> getOrganizacaoEventos() {
        return organizacaoEventos;
    }

    public void setOrganizacaoEventos(List<OrganizacaoEvento> organizacaoEventos) {
        this.organizacaoEventos = organizacaoEventos;
    }

    public List<Producao> getProducoes() {
        return producoes;
    }

    public void setProducoes(List<Producao> producoes) {
        this.producoes = producoes;
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
        result = prime * result + ((getIdUsuario() == null) ? 0 : getIdUsuario().hashCode());
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
        if (!(obj instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) obj;
        if (getIdUsuario() == null) {
            if (other.getIdUsuario() != null) {
                return false;
            }
        } else if (!getIdUsuario().equals(other.getIdUsuario())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pesquisador [idUsuario=" + getIdUsuario() + ", nome=" + getNome() + ", codigoLattes=" + codigoLattes
                + "]";
    }

}

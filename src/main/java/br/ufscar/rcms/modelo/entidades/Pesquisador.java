package br.ufscar.rcms.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "\"PESQUISADOR\"")
public class Pesquisador extends Usuario {

    private static final long serialVersionUID = 7468024654193724256L;

    @Column(nullable = false, unique = true)
    private String codigoLattes;

    @Column
    private String sexo;

    @Column
    private byte[] foto;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pesquisador")
    private Endereco endereco = new Endereco();

    @Column(nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String resumoProfissional;

    @OrderColumn
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pesquisador")
    private List<CitacaoBibliografica> citacaoBibliograficas = new ArrayList<CitacaoBibliografica>();

    @OrderColumn
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pesquisador")
    private List<FormacaoAcademica> formacoes = new ArrayList<FormacaoAcademica>();

    @OrderColumn
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compreensaoIdiomaPK.pesquisador")
    private List<CompreensaoIdioma> compreensaoIdiomas = new ArrayList<CompreensaoIdioma>();

    @OrderColumn
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pesquisador")
    private List<PremioTitulo> premios = new ArrayList<PremioTitulo>();

    @OrderColumn
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pesquisador")
    private List<ParticipacaoEvento> participacaoEventos = new ArrayList<ParticipacaoEvento>();

    @OrderColumn
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pesquisador")
    private List<OrganizacaoEvento> organizacaoEventos = new ArrayList<OrganizacaoEvento>();

    @OrderColumn
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pesquisador")
    private List<Orientacao> orientacoes = new ArrayList<Orientacao>();

    @OrderColumn(name = "INDEX")
    @ManyToMany(cascade = CascadeType.ALL)
    private List<AtuacaoPesquisador> areaAtuacoes = new ArrayList<AtuacaoPesquisador>();

    @OrderColumn
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    private List<ProjetoPesquisa> projetosPesquisa = new ArrayList<ProjetoPesquisa>();

    @OrderColumn
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "pesquisadorLinhaPesquisaPK.pesquisador")
    private List<PesquisadorLinhaPesquisa> pesquisadorLinhaPesquisa = new ArrayList<PesquisadorLinhaPesquisa>();

    public String getCodigoLattes() {
        return codigoLattes;
    }

    public void setCodigoLattes(String codigoLattes) {
        this.codigoLattes = codigoLattes;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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

    public List<CompreensaoIdioma> getCompreensaoIdiomas() {
        return compreensaoIdiomas;
    }

    public void setCompreensaoIdiomas(List<CompreensaoIdioma> compreensaoIdiomas) {
        this.compreensaoIdiomas = compreensaoIdiomas;
    }

    public List<CitacaoBibliografica> getCitacaoBibliograficas() {
        return citacaoBibliograficas;
    }

    public void setCitacaoBibliograficas(List<CitacaoBibliografica> citacaoBibliograficas) {
        this.citacaoBibliograficas = citacaoBibliograficas;
    }

    public List<AtuacaoPesquisador> getAreaAtuacoes() {
        return areaAtuacoes;
    }

    public void setAreaAtuacoes(List<AtuacaoPesquisador> areaAtuacoes) {
        this.areaAtuacoes = areaAtuacoes;
    }

    public List<FormacaoAcademica> getFormacoes() {
        return formacoes;
    }

    public FormacaoAcademica containsFormacaoAcademica(Long idFormacao) {
        for (FormacaoAcademica formacaoAcademica : formacoes) {
            if (formacaoAcademica.getIdFormacaoAcademica().equals(idFormacao)) {
                return formacaoAcademica;
            }
        }
        return null;
    }

    public FormacaoAcademica containsFormacaoAcademica(String descricao) {
        for (FormacaoAcademica formacaoAcademica : formacoes) {
            if (formacaoAcademica != null && formacaoAcademica.getDescricao().equals(descricao)) {
                return formacaoAcademica;
            }
        }
        return null;
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

    public List<ProjetoPesquisa> getProjetosPesquisa() {
        return projetosPesquisa;
    }

    public void setProjetosPesquisa(List<ProjetoPesquisa> projetosPesquisa) {
        this.projetosPesquisa = projetosPesquisa;
    }

    public List<Orientacao> getOrientacoes() {
        return orientacoes;
    }

    public void setOrientacoes(List<Orientacao> orientacoes) {
        this.orientacoes = orientacoes;
    }

    public List<PesquisadorLinhaPesquisa> getPesquisadorLinhaPesquisa() {
        return pesquisadorLinhaPesquisa;
    }

    public void setPesquisadorLinhaPesquisa(List<PesquisadorLinhaPesquisa> pesquisadorLinhaPesquisa) {
        this.pesquisadorLinhaPesquisa = pesquisadorLinhaPesquisa;
    }

    @Override
    public String toString() {
        return "Pesquisador [idUsuario=" + getIdUsuario() + ", nome=" + getNome() + ", codigoLattes=" + codigoLattes
                + "]";
    }

    public void addCitacoesBibliograficas(String[] citacoes) {
        for (String citacao : citacoes) {
            citacaoBibliograficas.add(new CitacaoBibliografica(this, citacao));
        }
    }
}
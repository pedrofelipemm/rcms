package br.ufscar.rcms.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "PESQUISADOR")
public class Pesquisador extends Usuario {

    private static final long serialVersionUID = 7468024654193724256L;

    @Column(nullable = false, unique = true)
    private String codigoLattes;

    @Column()
    private byte[] foto;

    @Column(nullable = false)
    private String enderecoProfissional;

    @Column()
    private double enderecoProfissionalLatitude;

    @Column()
    private double enderecoProfissionalLongitude;

    @Column(nullable = false)
    private String resumoProfissional;

    @OneToMany(cascade = CascadeType.ALL)
    private List<NomeCitacaoBibliografica> pesquisadorNomeCitacao;

    @OneToMany(cascade = CascadeType.ALL)
    private List<FormacaoAcademica> pesquisadorFormacoes = new ArrayList<FormacaoAcademica>();

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Idioma> idiomas = new ArrayList<Idioma>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<PremioTitulo> pesquisadorPremiosTitulos = new ArrayList<PremioTitulo>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<ParticipacaoEvento> pesquisadorParticipacaoEventos = new ArrayList<ParticipacaoEvento>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrganizacaoEvento> pesquisadorOrganizacaoEventos = new ArrayList<OrganizacaoEvento>();

    @ManyToMany
    private List<AreaAtuacao> areaAtuacoes = new ArrayList<AreaAtuacao>();

    @ManyToMany
    private List<Producao> publicacoes = new ArrayList<Producao>();

    @ManyToMany
    private List<ProjetoPesquisa> projetosPesquisa = new ArrayList<ProjetoPesquisa>();

    public String getCodigoLattes() {
        return codigoLattes;
    }

    public void setCodigoLattes(String codigoLattes) {
        this.codigoLattes = codigoLattes;
    }

    public String getEnderecoProfissional() {
        return enderecoProfissional;
    }

    public void setEnderecoProfissional(String enderecoProfissional) {
        this.enderecoProfissional = enderecoProfissional;
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

    public double getEnderecoProfissionalLatitude() {
        return enderecoProfissionalLatitude;
    }

    public void setEnderecoProfissionalLatitude(double enderecoProfissionalLatitude) {
        this.enderecoProfissionalLatitude = enderecoProfissionalLatitude;
    }

    public double getEnderecoProfissionalLongitude() {
        return enderecoProfissionalLongitude;
    }

    public void setEnderecoProfissionalLongitude(double enderecoProfissionalLongitude) {
        this.enderecoProfissionalLongitude = enderecoProfissionalLongitude;
    }

    public String getResumoProfissional() {
        return resumoProfissional;
    }

    public void setResumoProfissional(String resumoProfissional) {
        this.resumoProfissional = resumoProfissional;
    }

    public List<NomeCitacaoBibliografica> getPesquisadorNomeCitacao() {
        return pesquisadorNomeCitacao;
    }

    public void setPesquisadorNomeCitacao(List<NomeCitacaoBibliografica> pesquisadorNomeCitacao) {
        this.pesquisadorNomeCitacao = pesquisadorNomeCitacao;
    }

    public List<FormacaoAcademica> getPesquisadorFormacoes() {
        return pesquisadorFormacoes;
    }

    public void setPesquisadorFormacoes(List<FormacaoAcademica> pesquisadorFormacoes) {
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

    public List<PremioTitulo> getPesquisadorPremiosTitulos() {
        return pesquisadorPremiosTitulos;
    }

    public void setPesquisadorPremiosTitulos(List<PremioTitulo> pesquisadorPremiosTitulos) {
        this.pesquisadorPremiosTitulos = pesquisadorPremiosTitulos;
    }

    public List<ParticipacaoEvento> getPesquisadorParticipacaoEventos() {
        return pesquisadorParticipacaoEventos;
    }

    public void setPesquisadorParticipacaoEventos(List<ParticipacaoEvento> pesquisadorParticipacaoEventos) {
        this.pesquisadorParticipacaoEventos = pesquisadorParticipacaoEventos;
    }

    public List<OrganizacaoEvento> getPesquisadorOrganizacaoEventos() {
        return pesquisadorOrganizacaoEventos;
    }

    public void setPesquisadorOrganizacaoEventos(List<OrganizacaoEvento> pesquisadorOrganizacaoEventos) {
        this.pesquisadorOrganizacaoEventos = pesquisadorOrganizacaoEventos;
    }

    public List<Producao> getPublicacoes() {
        return publicacoes;
    }

    public void setPublicacoes(List<Producao> publicacoes) {
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

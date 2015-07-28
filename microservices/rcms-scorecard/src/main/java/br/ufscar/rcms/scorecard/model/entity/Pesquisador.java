package br.ufscar.rcms.scorecard.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.ForeignKey;

import br.ufscar.rcms.scorecard.util.JsonUtil;

@Entity
@Table(name = "pesquisador")
@ForeignKey(name = "fk_pesquisador_usuario")
public class Pesquisador extends Usuario {

    private static final long serialVersionUID = 7468024654193724256L;

    @Column(name = "codigo_lattes", nullable = false, unique = true)
    private String codigoLattes;

    @Column(name = "sexo")
    private String sexo;

//    @Transient
//    private TransientFile foto = new TransientFile();

//    @OneToOne(cascade = CascadeType.ALL, mappedBy = "pesquisador")
//    private Endereco endereco = new Endereco();

    @Column(name = "resumo_profissional", nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String resumoProfissional;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pesquisador")
    private List<CitacaoBibliografica> citacaoBibliograficas = new ArrayList<CitacaoBibliografica>();

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pesquisador")
//    private List<FormacaoAcademica> formacoes = new ArrayList<FormacaoAcademica>();

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "compreensaoIdiomaPK.pesquisador")
//    private List<CompreensaoIdioma> compreensaoIdiomas = new ArrayList<CompreensaoIdioma>();

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pesquisador")
//    private List<PremioTitulo> premios = new ArrayList<PremioTitulo>();

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pesquisador")
//    private List<ParticipacaoEvento> participacaoEventos = new ArrayList<ParticipacaoEvento>();

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pesquisador")
//    private List<OrganizacaoEvento> organizacaoEventos = new ArrayList<OrganizacaoEvento>();

//    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "pesquisador")
//    private List<Orientacao> orientacoes = new ArrayList<Orientacao>();

//    @ManyToMany(cascade = CascadeType.MERGE)
//    private List<AtuacaoPesquisador> areaAtuacoes = new ArrayList<AtuacaoPesquisador>();

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy="pesquisadores")
//    private List<ProjetoPesquisa> projetosPesquisa = new ArrayList<ProjetoPesquisa>();

//    @ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
//    @JoinTable(joinColumns = { @JoinColumn(name = "id_usuario") }, inverseJoinColumns = { @JoinColumn(name = "id_linha_pesquisa") })
//    @org.hibernate.annotations.ForeignKey(name = "fk_pesquisador_linha_pesquisa_pesquisador", inverseName = "fk_pesquisador_linha_pesquisa_linha_pesquisa")
//    private List<LinhaDePesquisa> linhasDePesquisa = new ArrayList<LinhaDePesquisa>();

    public String getCodigoLattes() {
        return codigoLattes;
    }

    public void setCodigoLattes(final String codigoLattes) {
        this.codigoLattes = codigoLattes;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(final String sexo) {
        this.sexo = sexo;
    }

//    public Endereco getEndereco() {
//        return endereco;
//    }
//
//    public void setEndereco(final Endereco endereco) {
//        this.endereco = endereco;
//    }

//    public TransientFile getFoto() {
//        return foto;
//    }
//
//    public void setFoto(final TransientFile foto) {
//        this.foto = foto;
//    }

    public String getResumoProfissional() {
        return resumoProfissional;
    }

    public void setResumoProfissional(final String resumoProfissional) {
        this.resumoProfissional = resumoProfissional;
    }

//    public List<CompreensaoIdioma> getCompreensaoIdiomas() {
//        return compreensaoIdiomas;
//    }
//
//    public void setCompreensaoIdiomas(final List<CompreensaoIdioma> compreensaoIdiomas) {
//        this.compreensaoIdiomas = compreensaoIdiomas;
//    }

    public List<CitacaoBibliografica> getCitacaoBibliograficas() {
        return citacaoBibliograficas;
    }

    public void setCitacaoBibliograficas(final List<CitacaoBibliografica> citacaoBibliograficas) {
        this.citacaoBibliograficas = citacaoBibliograficas;
    }

//    public List<AtuacaoPesquisador> getAreaAtuacoes() {
//        return areaAtuacoes;
//    }
//
//    public void setAreaAtuacoes(final List<AtuacaoPesquisador> areaAtuacoes) {
//        this.areaAtuacoes = areaAtuacoes;
//    }

//    public List<FormacaoAcademica> getFormacoes() {
//        return formacoes;
//    }
//
//    public FormacaoAcademica containsFormacaoAcademica(final Long idFormacao) {
//        for (FormacaoAcademica formacaoAcademica : formacoes) {
//            if (formacaoAcademica.getIdFormacaoAcademica().equals(idFormacao)) {
//                return formacaoAcademica;
//            }
//        }
//        return null;
//    }
//
//    public FormacaoAcademica containsFormacaoAcademica(
//			final FormacaoLattes formacaoLattes) {
//		for (FormacaoAcademica formacaoAcademica : formacoes) {
//			if (formacaoAcademica != null) {
//				if (formacaoAcademica.getDescricao().equals(
//						formacaoLattes.getDescricao())
//						&& formacaoAcademica.getAnoInicio().equals(
//								formacaoLattes.getAnoInicio())
//						&& formacaoAcademica.getAnoConclusao().equals(
//								formacaoLattes.getAnoConclusao())
//						&& formacaoAcademica.getNomeInstituicao().equals(
//								formacaoLattes.getNomeInstituicao())
//						&& formacaoAcademica.getTipo().equals(
//								formacaoLattes.getTipo())) {
//						return formacaoAcademica;
//				}
//			}
//		}
//		return null;
//	}
//
//    public void setFormacoes(final List<FormacaoAcademica> formacoes) {
//        this.formacoes = formacoes;
//    }

//    public List<PremioTitulo> getPremios() {
//        return premios;
//    }
//
//    public void setPremios(final List<PremioTitulo> premios) {
//        this.premios = premios;
//    }

//    public List<ParticipacaoEvento> getParticipacaoEventos() {
//        return participacaoEventos;
//    }
//
//    public void setParticipacaoEventos(final List<ParticipacaoEvento> participacaoEventos) {
//        this.participacaoEventos = participacaoEventos;
//    }

//    public List<OrganizacaoEvento> getOrganizacaoEventos() {
//        return organizacaoEventos;
//    }
//
//    public void setOrganizacaoEventos(final List<OrganizacaoEvento> organizacaoEventos) {
//        this.organizacaoEventos = organizacaoEventos;
//    }

//    public List<ProjetoPesquisa> getProjetosPesquisa() {
//        return projetosPesquisa;
//    }
//
//    public void setProjetosPesquisa(final List<ProjetoPesquisa> projetosPesquisa) {
//        this.projetosPesquisa = projetosPesquisa;
//    }

//    public List<Orientacao> getOrientacoes() {
//        return orientacoes;
//    }
//
//    public void setOrientacoes(final List<Orientacao> orientacoes) {
//        this.orientacoes = orientacoes;
//    }

//    public List<LinhaDePesquisa> getLinhasDePesquisa() {
//        return linhasDePesquisa;
//    }
//
//    public void setLinhasDePesquisa(final List<LinhaDePesquisa> linhasDePesquisa) {
//        this.linhasDePesquisa = linhasDePesquisa;
//    }

//    public void addCompreensaoIdiomas(final CompreensaoIdioma... compreensaoIdiomas) {
//        if (compreensaoIdiomas != null) {
//            this.compreensaoIdiomas.addAll(Arrays.asList(compreensaoIdiomas));
//        }
//    }
//
//    public void removeCompreensaoIdiomas(final CompreensaoIdioma... compreensaoIdiomas) {
//        if (compreensaoIdiomas != null) {
//            this.compreensaoIdiomas.removeAll(Arrays.asList(compreensaoIdiomas));
//        }
//    }

//    public void addCitacaoBibliografica(final CitacaoBibliografica... citacaoBibliografica) {
//        if (citacaoBibliografica != null) {
//            citacaoBibliograficas.addAll(Arrays.asList(citacaoBibliografica));
//        }
//    }
//
//    public void removeCitacaoBibliografica(final CitacaoBibliografica... citacaoBibliografica) {
//        if (citacaoBibliografica != null) {
//            citacaoBibliograficas.removeAll(Arrays.asList(citacaoBibliografica));
//        }
//    }

//    public void removeOrganizacaoEventos(final OrganizacaoEvento... organizacaoEventos) {
//		if(organizacaoEventos != null){
//			this.organizacaoEventos.removeAll(Arrays.asList(organizacaoEventos));
//		}
//	}

//    public void addParticipacaoEventos(final ParticipacaoEvento... participacaoEventos) {
//        if (participacaoEventos != null) {
//            this.participacaoEventos.addAll(Arrays.asList(participacaoEventos));
//        }
//    }
//
//    public void removeParticipacaoEventos(final ParticipacaoEvento... participacaoEventos) {
//        if (participacaoEventos != null) {
//            this.participacaoEventos.removeAll(Arrays.asList(participacaoEventos));
//        }
//    }

//    public void addCitacaoBibliograficas(final CitacaoBibliografica... citacaoBibliograficas) {
//        if (citacaoBibliograficas != null) {
//            this.citacaoBibliograficas.addAll(Arrays.asList(citacaoBibliograficas));
//        }
//    }

//    public void addPremios(final PremioTitulo... premios) {
//        if (premios != null) {
//            this.premios.addAll(Arrays.asList(premios));
//        }
//    }

//    public void addOrgazicaoEventos(final OrganizacaoEvento... organizacaoEventos) {
//        if (organizacaoEventos != null) {
//            this.organizacaoEventos.addAll(Arrays.asList(organizacaoEventos));
//        }
//    }

//    public void addProjetosPesquisa(final ProjetoPesquisa... projetosPesquisa) {
//
//    	for(ProjetoPesquisa p : projetosPesquisa){
//    		if (!this.projetosPesquisa.contains(p)){
//    			this.projetosPesquisa.add(p);
//    		}
//    	}
//    }
//
//    public void removeProjetosPesquisa(final ProjetoPesquisa... projetosPesquisa) {
//
//    	for(ProjetoPesquisa p : projetosPesquisa){
//    		if(getProjetosPesquisa().contains(p)){
//        		getProjetosPesquisa().remove(p);
//        	}
//    	}
//    }

//    public void addOrientacoes(final Orientacao... orientacoes) {
//        if (orientacoes != null) {
//            this.orientacoes.addAll(Arrays.asList(orientacoes));
//        }
//    }
//
//    public void addOrientacoes(final List<? extends Orientacao> orientacoes) {
//        if (orientacoes != null) {
//            this.orientacoes.addAll(orientacoes);
//        }
//    }

//    public void addAtuacoesPesquisador(final AtuacaoPesquisador... atuacoesPesquisador) {
//        if (atuacoesPesquisador != null) {
//            areaAtuacoes.addAll(Arrays.asList(atuacoesPesquisador));
//        }
//    }
//
//    public void addProducoes(final List<? extends Producao> producoes) {
//        if (producoes != null) {
//
//            for (Producao producao : producoes) {
//
//            }
//        }
//    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((codigoLattes == null) ? 0 : codigoLattes.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Pesquisador other = (Pesquisador) obj;
        if (codigoLattes == null) {
            if (other.codigoLattes != null) {
                return false;
            }
        } else if (!codigoLattes.equals(other.codigoLattes)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
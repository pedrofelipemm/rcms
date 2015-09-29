package br.ufscar.rcms.modelo.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "projeto_pesquisa")
public class ProjetoPesquisa extends Entidade implements Comparable {

    private static final long serialVersionUID = -9149061665883584009L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_projeto_de_pesquisa")
    private Long idProjetoPesquisa;

    @Column(name = "nome", nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String nome;

    @Column(name = "descricao", length = COLUMN_DEFAULT_LENGTH)
    private String descricao;

    @Column(name = "ano_inicio")
    private Integer anoInicio;

    @Column(name = "ano_conclusao")
    private Integer anoConclusao;

    @Column(name = "agencia_de_fomento", length = COLUMN_DEFAULT_LENGTH)
    private String agenciaDeFomento;

    @Transient
    private TransientFile imagemCarousel = new TransientFile();

    @Transient
    private List<TransientFile> galeria = new ArrayList<TransientFile>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
    @JoinTable(joinColumns = { @JoinColumn(name = "id_projeto_de_pesquisa") }, inverseJoinColumns = { @JoinColumn(name = "id_producao") })
    @org.hibernate.annotations.ForeignKey(name = "fk_projeto_pesquisa_producao_projeto_pesquisa", inverseName = "fk_projeto_pesquisa_producao_producao")
    private List<Producao> producoes = new ArrayList<Producao>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projetoPesquisa")
    private List<Midia> midia = new ArrayList<Midia>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(joinColumns = { @JoinColumn(name = "id_projeto_de_pesquisa") }, inverseJoinColumns = { @JoinColumn(name = "id_usuario") })
    @org.hibernate.annotations.ForeignKey(name = "fk_projeto_pesquisa_pesquisador_projeto_pesquisa", inverseName = "fk_projeto_pesquisa_pesquisador_pesquisador")
    private List<Pesquisador> pesquisadores = new ArrayList<Pesquisador>();

    public ProjetoPesquisa() {
    }

    public ProjetoPesquisa(final String nome, final String descricao, final Integer anoInicio, final Integer anoConclusao) {
        this(nome, descricao, anoInicio, anoConclusao, null);
    }

    public ProjetoPesquisa(final String nome, final String descricao, final Integer anoInicio, final Integer anoConclusao, final String agenciaDeFomento) {
        this(nome, descricao, anoInicio, anoConclusao, agenciaDeFomento, new ArrayList<Producao>(), new ArrayList<Midia>(), new ArrayList<Pesquisador>());
    }

    public ProjetoPesquisa(final String nome, final String descricao, final Integer anoInicio, final Integer anoConclusao,
            final String agenciaDeFomento, final List<Producao> producoes, final List<Midia> midia, final List<Pesquisador> pesquisadores) {
        this.nome = nome;
        this.descricao = descricao;
        this.anoInicio = anoInicio;
        this.anoConclusao = anoConclusao;
        this.agenciaDeFomento = agenciaDeFomento;
        this.producoes = producoes;
        this.midia = midia;
        this.pesquisadores = pesquisadores;
    }

    public void adicionarPesquisador(final Pesquisador pesquisador){
    	if(!this.getPesquisadores().contains(pesquisador)){
    		this.pesquisadores.add(pesquisador);
    	}
    }

    public void removerPesquisador(final Pesquisador pesquisador){
    	if(this.getPesquisadores().contains(pesquisador)){
    		this.pesquisadores.remove(pesquisador);
    	}
    }

    public List<Producao> getProducoes() {
        return producoes;
    }

    public void setProducoes(final List<Producao> producoes) {
        this.producoes = producoes;
    }

    public List<Midia> getMidia() {
        return midia;
    }

    public void setMidia(final List<Midia> midia) {
        this.midia = midia;
    }

    public Long getIdProjetoPesquisa() {
        return idProjetoPesquisa;
    }

    public void setIdProjetoPesquisa(final Long idProjetoPesquisa) {
        this.idProjetoPesquisa = idProjetoPesquisa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(final String descricao) {
        this.descricao = descricao;
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

    public String getAgenciaDeFomento() {
        return agenciaDeFomento;
    }

    public void setAgenciaDeFomento(final String agenciaDeFomento) {
        this.agenciaDeFomento = agenciaDeFomento;
    }

    public List<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(final List<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
    }

    public List<TransientFile> getGaleria() {
        return galeria;
    }

    public void setGaleria(List<TransientFile> galeria) {
        this.galeria = galeria;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idProjetoPesquisa == null) ? 0 : idProjetoPesquisa.hashCode());
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

    @Override
    public int compareTo(Object o) {
        ProjetoPesquisa pp = (ProjetoPesquisa) o;
        Integer ppAnoConclusao = (pp.anoConclusao != null) ? pp.anoConclusao : 10000;
        Integer thisAnoConclusao = (this.anoConclusao != null) ? this.anoConclusao : 10000;

        return thisAnoConclusao.compareTo(ppAnoConclusao);
    }

    public TransientFile getImagemCarousel() {
        return imagemCarousel;
    }

    public void setImagemCarousel(TransientFile imagemCarousel) {
        this.imagemCarousel = imagemCarousel;
    }
}
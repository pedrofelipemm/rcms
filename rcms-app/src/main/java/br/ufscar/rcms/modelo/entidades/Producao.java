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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "producao")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Producao extends Entidade {

    private static final long serialVersionUID = 4347330488122627124L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producao")
    private Long idProducao;

    @Column(name = "titulo", nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String titulo;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "link")
    private String link;

    @Column(name = "pdf")
    private byte[] pdf;

    @ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH })
    @JoinTable( name="producao_citacao_bibliografica", joinColumns = { @JoinColumn(name = "id_producao") }, inverseJoinColumns = { @JoinColumn(name = "id_citacao_bibliografica") })
    @org.hibernate.annotations.ForeignKey(name = "fk_producao_citacao_bibliografica_producao", inverseName = "fk_producao_citacao_bibliografica_citacao_bibliografica")
    private List<CitacaoBibliografica> citacaoBibliograficas = new ArrayList<CitacaoBibliografica>();


    public Long getIdProducao() {
        return idProducao;
    }

    public void setIdProducao(final Long idProducao) {
        this.idProducao = idProducao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(final String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(final Integer ano) {
        this.ano = ano;
    }

    public String getLink() {
        return link;
    }

    public void setLink(final String link) {
        this.link = link;
    }

    public byte[] getPdf() {

        if (pdf == null) {
            return new byte[0];
        }

        byte[] copyOfPdf = new byte[pdf.length];
        System.arraycopy(pdf, 0, copyOfPdf, 0, pdf.length);

        return copyOfPdf;
    }

    public void setPdf(final byte[] pdf) {

        if (pdf == null) {
            return;
        }

        byte[] copyOfPdf = new byte[pdf.length];
        System.arraycopy(pdf, 0, copyOfPdf, 0, pdf.length);

        this.pdf = copyOfPdf;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idProducao == null) ? 0 : idProducao.hashCode());
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
        if (!(obj instanceof Producao)) {
            return false;
        }
        Producao other = (Producao) obj;
        if (idProducao == null) {
            if (other.idProducao != null) {
                return false;
            }
        } else if (!idProducao.equals(other.idProducao)) {
            return false;
        }
        return true;
    }

    public Producao CloneTo(final Producao producaoDestino) {
        producaoDestino.setAno(ano);
        producaoDestino.setCitacaoBibliograficas(citacaoBibliograficas);
        producaoDestino.setIdProducao(idProducao);
        producaoDestino.setLink(link);
        producaoDestino.setPdf(pdf);
        producaoDestino.setTitulo(titulo);
        return producaoDestino;
    }

	public List<CitacaoBibliografica> getCitacaoBibliograficas() {
		return citacaoBibliograficas;
	}

	public void setCitacaoBibliograficas(final List<CitacaoBibliografica> citacaoBibliograficas) {
		this.citacaoBibliograficas = citacaoBibliograficas;
	}
}
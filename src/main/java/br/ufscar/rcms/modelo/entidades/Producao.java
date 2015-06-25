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
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "\"PRODUCAO\"")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Producao extends Entidade {

    private static final long serialVersionUID = 4347330488122627124L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producao")
    private Long idProducao;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "ano")
    private Integer ano;

    @Column(name = "link")
    private String link;

    @Column(name = "pdf")
    private byte[] pdf;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<CitacaoBibliografica> citacaoBibliograficas = new ArrayList<CitacaoBibliografica>();


    public Long getIdProducao() {
        return idProducao;
    }

    public void setIdProducao(Long idProducao) {
        this.idProducao = idProducao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
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

    public void setPdf(byte[] pdf) {

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
    public boolean equals(Object obj) {
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
    
    public Producao CloneTo(Producao publicacaoDestino){
    	publicacaoDestino.setAno(ano);
    	publicacaoDestino.setCitacaoBibliograficas(citacaoBibliograficas);
    	publicacaoDestino.setIdProducao(idProducao);
    	publicacaoDestino.setLink(link);
    	publicacaoDestino.setPdf(pdf);
    	publicacaoDestino.setTitulo(titulo);
    	return publicacaoDestino;
    }

	public List<CitacaoBibliografica> getCitacaoBibliograficas() {
		return citacaoBibliograficas;
	}

	public void setCitacaoBibliograficas(List<CitacaoBibliografica> citacaoBibliograficas) {
		this.citacaoBibliograficas = citacaoBibliograficas;
	}
}
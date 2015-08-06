package br.ufscar.rcms.scorecard.model.entity;

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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.ufscar.rcms.commons.util.JsonUtil;

@Entity
@Table(name = "producao")
@Inheritance(strategy = InheritanceType.JOINED)
public class Producao extends br.ufscar.rcms.scorecard.model.entity.Entity {

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

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "producao")
    private List<AutorProducao> autores = new ArrayList<AutorProducao>();

    @Override
    public Long getId() {
        return getIdProducao();
    }

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

    public void setAutores(final List<AutorProducao> autores) {
        this.autores = autores;
    }

    public List<AutorProducao> getAutores() {
        return autores;
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

    @Override
    public String toString() {
        return JsonUtil.toJson(this);
    }
}
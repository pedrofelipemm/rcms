package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCAO")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Producao extends Entidade {

    private static final long serialVersionUID = 4347330488122627124L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProducao;

    @Column(nullable = false)
    private String titulo;

    @Column
    private Integer ano;

    @Column
    private Integer volume;

    @Column
    private String paginas;

    @Column
    private String link;

    @Column
    private byte[] pdf;

    public Integer getIdProducao() {
        return idProducao;
    }

    public void setIdProducao(Integer idProducao) {
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

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(String paginas) {
        this.paginas = paginas;
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
}
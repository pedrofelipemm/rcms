package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "artigo_em_periodico")
@ForeignKey(name = "fk_artigo_em_periodico_producao_bibliografica")
public class ArtigoEmPeriodico extends ProducaoBibliografica {

    private static final long serialVersionUID = -6003079801862161779L;

    @Column(name = "volume")
    private String volume;

    @Column(name = "paginas")
    private String paginas;

    @Column(name = "doi")
    private String doi;

    @Column(name = "revista")
    private String revista;

    @Column(name = "numero")
    private Integer numero;

    public ArtigoEmPeriodico() {
    }

    public ArtigoEmPeriodico(final String titulo, final List<CitacaoBibliografica> autores, final Integer ano, final String volume,
            final String paginas, final String doi, final String revista, final Integer numero) {

        super.setTitulo(titulo);
        super.setCitacaoBibliograficas(autores);
        super.setAno(ano);
        this.volume = volume;
        this.paginas = paginas;
        this.doi = doi;
        this.revista = revista;
        this.numero = numero;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(final String volume) {
        this.volume = volume;
    }

    public String getPaginas() {
        return paginas;
    }

    public void setPaginas(final String paginas) {
        this.paginas = paginas;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(final String doi) {
        this.doi = doi;
    }

    public String getRevista() {
        return revista;
    }

    public void setRevista(final String revista) {
        this.revista = revista;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(final Integer numero) {
        this.numero = numero;
    }
}
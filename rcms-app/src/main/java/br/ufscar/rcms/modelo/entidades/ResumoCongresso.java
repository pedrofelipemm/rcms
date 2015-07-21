package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "resumo_congresso")
@ForeignKey(name = "fk_resumo_congresso_producao_bibliografica")
public class ResumoCongresso extends ProducaoBibliografica {

    private static final long serialVersionUID = -2563684451376971183L;

    @Column(name = "doi")
    private String doi;

    @Column(name = "nome_evento", length = COLUMN_DEFAULT_LENGTH)
    private String nomeEvento;

    @Column(name = "volume")
    private String volume;

    @Column(name = "paginas")
    private String paginas;

    @Column(name = "numero")
    private Integer numero;

    public ResumoCongresso() {
    }

    public ResumoCongresso(final String titulo, final List<CitacaoBibliografica> autores, final Integer ano, final String doi,
 final String nomeEvento, final String volume, final String paginas, final Integer numero) {

        super.setTitulo(titulo);
        super.setCitacaoBibliograficas(autores);
        super.setAno(ano);
        this.doi = doi;
        this.nomeEvento = nomeEvento;
        this.volume = volume;
        this.paginas = paginas;
        this.numero = numero;
    }

    public String getDoi() {
        return doi;
    }

    public void setDoi(final String doi) {
        this.doi = doi;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(final String nomeEvento) {
        this.nomeEvento = nomeEvento;
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(final Integer numero) {
        this.numero = numero;
    }
}
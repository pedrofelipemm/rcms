package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "resumo_expandido_congresso")
@ForeignKey(name = "fk_resumo_expandido_congresso_producao_bibliografica")
public class ResumoExpandidoCongresso extends ProducaoBibliografica {

    private static final long serialVersionUID = -396352635868864895L;

    @Column(name = "doi")
    private String doi;

    @Column(name = "nome_evento", length = COLUMN_DEFAULT_LENGTH)
    private String nomeEvento;

    @Column(name = "volume")
    private String volume;

    @Column(name = "paginas")
    private String paginas;

    public ResumoExpandidoCongresso() {
    }

    public ResumoExpandidoCongresso(final String titulo, final List<CitacaoBibliografica> autores, final Integer ano, final String doi,
 final String nomeEvento, final String volume, final String paginas) {

        super.setTitulo(titulo);
        super.setCitacaoBibliograficas(autores);
        super.setAno(ano);
        this.doi = doi;
        this.nomeEvento = nomeEvento;
        this.volume = volume;
        this.paginas = paginas;
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
}
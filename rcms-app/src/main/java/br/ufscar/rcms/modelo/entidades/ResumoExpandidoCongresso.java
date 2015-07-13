package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"RESUMO_EXPANDIDO_CONGRESSO\"")
@ForeignKey(name = "fk_resumo_expandido_congresso_producao_bibliografica")
public class ResumoExpandidoCongresso extends ProducaoBibliografica {

    private static final long serialVersionUID = -396352635868864895L;

    @Column(name = "doi")
    private String doi;

    @Column(name = "nome_evento")
    private String nomeEvento;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "paginas")
    private String paginas;

    public ResumoExpandidoCongresso() {
    }

    public ResumoExpandidoCongresso(String titulo, List<CitacaoBibliografica> autores, Integer ano, String doi,
            String nomeEvento, Integer volume, String paginas) {

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

    public void setDoi(String doi) {
        this.doi = doi;
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
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
}
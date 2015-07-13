package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"TRABALHO_COMPLETO_CONGRESSO\"")
@ForeignKey(name = "fk_trabalho_completo_congresso_producao_bibliografica")
public class TrabalhoCompletoCongresso extends ProducaoBibliografica {

    private static final long serialVersionUID = -1822128070360441152L;

    @Column(name = "doi")
    private String doi;

    @Column(name = "nome_evento")
    private String nomeEvento;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "paginas")
    private String paginas;

    public TrabalhoCompletoCongresso() {
    }

    public TrabalhoCompletoCongresso(String titulo, List<CitacaoBibliografica> autores, Integer ano, String doi,
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
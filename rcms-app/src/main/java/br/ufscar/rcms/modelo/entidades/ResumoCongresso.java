package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"RESUMO_CONGRESSO\"")
@ForeignKey(name = "fk_resumo_congresso_producao_bibliografica")
public class ResumoCongresso extends ProducaoBibliografica {

    private static final long serialVersionUID = -2563684451376971183L;

    @Column(name = "doi")
    private String doi;

    @Column(name = "nome_evento")
    private String nomeEvento;

    @Column(name = "volume")
    private Integer volume;

    @Column(name = "paginas")
    private String paginas;

    @Column(name = "numero")
    private Integer numero;

    public ResumoCongresso() {
    }

    public ResumoCongresso(String titulo, List<CitacaoBibliografica> autores, Integer ano, String doi,
            String nomeEvento, Integer volume, String paginas, Integer numero) {

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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }
}
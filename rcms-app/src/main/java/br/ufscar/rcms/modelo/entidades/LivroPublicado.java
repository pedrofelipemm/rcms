package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "livro_publicado")
@ForeignKey(name = "fk_livro_publicado_producao_bibliografica")
public class LivroPublicado extends ProducaoBibliografica {

    private static final long serialVersionUID = -998961459295778010L;

    @Column(name = "edicao")
    private String edicao;

    @Column(name = "volume")
    private String volume;

    @Column(name = "paginas")
    private String paginas;

    public LivroPublicado() {
    }

    public LivroPublicado(final String titulo, final Integer ano,
            final String edicao, final String volume, final String paginas) {

        super.setTitulo(titulo);
        super.setAno(ano);
        this.edicao = edicao;
        this.volume = volume;
        this.paginas = paginas;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(final String edicao) {
        this.edicao = edicao;
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
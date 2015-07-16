package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "capitulo_livro")
@ForeignKey(name = "fk_capitulo_livro_producao_bibliografica")
public class CapituloLivro extends ProducaoBibliografica {

    private static final long serialVersionUID = -2059134777817514692L;

    @Column(name = "livro")
    private String livro;

    @Column(name = "edicao")
    private String edicao;

    @Column(name = "editora")
    private String editora;

    public CapituloLivro() {
    }

    public CapituloLivro(final String titulo, final List<CitacaoBibliografica> autores, final Integer ano, final String livro, final String edicao,
            final String editora) {

        super.setTitulo(titulo);
        super.setCitacaoBibliograficas(autores);
        super.setAno(ano);
        this.livro = livro;
        this.edicao = edicao;
        this.editora = editora;
    }

    public String getLivro() {
        return livro;
    }

    public void setLivro(final String livro) {
        this.livro = livro;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(final String edicao) {
        this.edicao = edicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(final String editora) {
        this.editora = editora;
    }
}
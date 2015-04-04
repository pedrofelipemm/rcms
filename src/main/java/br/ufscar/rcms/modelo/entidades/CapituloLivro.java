package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"CAPITULO_LIVRO\"")
@ForeignKey(name = "fk_capitulo_livro_producao_bibliografica")
public class CapituloLivro extends ProducaoBibliografica {

    private static final long serialVersionUID = -2059134777817514692L;

    @Column(name = "livro")
    private String livro;

    @Column(name = "edicao")
    private String edicao;

    @Column(name = "editora")
    private String editora;

    public String getLivro() {
        return livro;
    }

    public void setLivro(String livro) {
        this.livro = livro;
    }

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }
}
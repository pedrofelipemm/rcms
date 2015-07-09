package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"LIVRO_PUBLICADO\"")
@ForeignKey(name = "fk_livro_publicado_producao_bibliografica")
public class LivroPublicado extends ProducaoBibliografica {

    private static final long serialVersionUID = -998961459295778010L;

    @Column(name = "edicao")
    private String edicao;

    public String getEdicao() {
        return edicao;
    }

    public void setEdicao(String edicao) {
        this.edicao = edicao;
    }
}
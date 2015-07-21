package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "apresentacao_trabalho")
@ForeignKey(name = "fk_apresentacao_trabalho_producao_bibliografica")
public class ApresentacaoTrabalho extends ProducaoBibliografica {

    private static final long serialVersionUID = 5668638568301910798L;

    @Column(name = "natureza")
    private String natureza;

    public ApresentacaoTrabalho() {
    }

    public ApresentacaoTrabalho(final String titulo, final List<CitacaoBibliografica> autores, final Integer ano, final String natureza) {

        super.setTitulo(titulo);
        super.setCitacaoBibliograficas(autores);
        super.setAno(ano);
        this.natureza = natureza;
    }
    public String getNatureza() {
        return natureza;
    }

    public void setNatureza(final String natureza) {
        this.natureza = natureza;
    }
}
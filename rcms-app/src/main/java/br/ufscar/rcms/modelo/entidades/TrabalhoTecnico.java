package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "trabalho_tecnico")
@ForeignKey(name = "fk_trabalho_tecnico_producao_tecnica")
public class TrabalhoTecnico extends ProducaoTecnica {

    private static final long serialVersionUID = 4593589147605257418L;

    public TrabalhoTecnico() {
    }

    public TrabalhoTecnico(final String titulo, final List<CitacaoBibliografica> autores, final Integer ano) {

        super.setTitulo(titulo);
        super.setCitacaoBibliograficas(autores);
        super.setAno(ano);
    }
}
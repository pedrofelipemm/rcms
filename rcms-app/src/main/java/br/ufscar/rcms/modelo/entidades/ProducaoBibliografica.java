package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "producao_bibliografica")
@ForeignKey(name = "fk_producao_bibliografica_producao")
public abstract class ProducaoBibliografica extends Producao {

    private static final long serialVersionUID = 6014105271050898430L;
}
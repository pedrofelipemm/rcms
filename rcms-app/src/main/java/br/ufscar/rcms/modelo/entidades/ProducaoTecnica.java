package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "producao_tecnica")
@ForeignKey(name = "fk_producao_tecnica_producao")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProducaoTecnica extends Producao {

    private static final long serialVersionUID = -3045760787108604524L;
}
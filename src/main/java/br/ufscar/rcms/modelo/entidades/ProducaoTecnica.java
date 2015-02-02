package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCAO_TECNICA")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class ProducaoTecnica extends Producao {

    private static final long serialVersionUID = -3045760787108604524L;
}
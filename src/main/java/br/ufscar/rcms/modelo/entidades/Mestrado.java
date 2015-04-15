package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"MESTRADO\"")
@ForeignKey(name = "fk_mestrado_orientacao")
public class Mestrado extends Orientacao {

    private static final long serialVersionUID = -8176353599874502313L;
}
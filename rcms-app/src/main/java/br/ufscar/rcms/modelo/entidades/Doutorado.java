package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "doutorado")
@ForeignKey(name = "fk_doutorado_orientacao")
public class Doutorado extends Orientacao {

    private static final long serialVersionUID = 4919782666029531420L;

}
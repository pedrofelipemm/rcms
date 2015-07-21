package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "tcc")
@ForeignKey(name = "fk_tcc_orientacao")
public class TCC extends Orientacao {

    private static final long serialVersionUID = 9109058711682168611L;
}
package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "orientacao_outro_tipo")
@ForeignKey(name = "fk_orientacao_outro_tipo_orientacao")
public class OrientacaoOutroTipo extends Orientacao {

    private static final long serialVersionUID = 6703477547303850350L;
}
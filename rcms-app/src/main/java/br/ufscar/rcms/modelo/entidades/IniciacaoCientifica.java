package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"INICIACAO_CIENTIFICA\"")
@ForeignKey(name = "fk_iniciacao_cientifica_orientacao")
public class IniciacaoCientifica extends Orientacao {

    private static final long serialVersionUID = 1812260340670959631L;
}
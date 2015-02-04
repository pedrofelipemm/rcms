package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PROCESSO_OU_TECNICA")
public class ProcessoOuTecnica extends ProducaoTecnica {

    private static final long serialVersionUID = 6694121402955244752L;
}
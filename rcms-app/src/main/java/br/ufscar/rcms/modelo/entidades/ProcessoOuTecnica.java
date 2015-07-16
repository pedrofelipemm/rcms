package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "processo_ou_tecnica")
@ForeignKey(name = "fk_processo_ou_tecnica_producao_tecnica")
public class ProcessoOuTecnica extends ProducaoTecnica {

    private static final long serialVersionUID = 6694121402955244752L;
}
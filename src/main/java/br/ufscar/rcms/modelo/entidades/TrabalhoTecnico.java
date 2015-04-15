package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "\"TRABALHO_TECNICO\"")
@ForeignKey(name = "fk_trabalho_tecnico_producao_tecnica")
public class TrabalhoTecnico extends ProducaoTecnica {

    private static final long serialVersionUID = 4593589147605257418L;
}
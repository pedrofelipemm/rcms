package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "producao_outra")
@ForeignKey(name = "fk_producao_outra_producao")
public class ProducaoOutra extends Producao {

    private static final long serialVersionUID = 5292071587229933690L;
}
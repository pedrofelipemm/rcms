package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "PRODUCAO_BIBLIOGRAFICA")
public abstract class ProducaoBibliografica extends Producao {

    private static final long serialVersionUID = 6014105271050898430L;
}
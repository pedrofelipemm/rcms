package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

@Entity
@Table(name = "produto_tecnologico")
@ForeignKey(name = "fk_produto_tecnologico_producao_tecnica")
public class ProdutoTecnologico extends ProducaoTecnica {

    private static final long serialVersionUID = -6731364486982583869L;

    public ProdutoTecnologico() {
    }

    public ProdutoTecnologico(final String titulo, final Integer ano) {

        super.setTitulo(titulo);
        super.setAno(ano);
    }
}
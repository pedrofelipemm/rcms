package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PesquisadorNomeCitacao extends Entidade{

    private static final long serialVersionUID = 1883662481651131111L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPesquisadorNomeCitacao;

    public Integer getIdPesquisadorNomeCitacao() {
        return idPesquisadorNomeCitacao;
    }

    public void setIdPesquisadorNomeCitacao(Integer idPesquisadorNomeCitacao) {
        this.idPesquisadorNomeCitacao = idPesquisadorNomeCitacao;
    }

}

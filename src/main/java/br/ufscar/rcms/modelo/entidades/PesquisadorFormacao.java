package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PesquisadorFormacao extends Entidade{

    private static final long serialVersionUID = -1883554721627714716L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPesquisadorFormacao;

    public Integer getIdPesquisadorFormacao() {
        return idPesquisadorFormacao;
    }

    public void setIdPesquisadorFormacao(Integer idPesquisadorFormacao) {
        this.idPesquisadorFormacao = idPesquisadorFormacao;
    }

}

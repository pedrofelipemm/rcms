package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class AreaAtuacao extends Entidade{

    private static final long serialVersionUID = -3948561964306499761L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAreaAtuacao;

    public Integer getIdAreaAtuacao() {
        return idAreaAtuacao;
    }

    public void setIdAreaAtuacao(Integer idAreaAtuacao) {
        this.idAreaAtuacao = idAreaAtuacao;
    }

}

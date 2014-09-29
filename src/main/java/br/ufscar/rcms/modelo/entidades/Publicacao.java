package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Publicacao extends Entidade{

    private static final long serialVersionUID = 4347330488122627124L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPublicacao;

    public Integer getIdPublicacao() {
        return idPublicacao;
    }

    public void setIdPublicacao(Integer idPublicacao) {
        this.idPublicacao = idPublicacao;
    }

}

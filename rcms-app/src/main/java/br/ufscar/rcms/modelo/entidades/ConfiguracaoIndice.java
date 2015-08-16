package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "configuracao_indice")
public class ConfiguracaoIndice extends Configuracao {

    private static final long serialVersionUID = 338377855927447827L;

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

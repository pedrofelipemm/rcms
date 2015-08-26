package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "configuracao_sistema")
public class ConfiguracaoSistema extends Configuracao {

    private static final long serialVersionUID = -5694951495457486670L;

    public ConfiguracaoSistema() {/* Serialization */}

    public ConfiguracaoSistema(final Tipo key) {
        super(key);
    }

    public ConfiguracaoSistema(final Tipo key, final String value) {
        super(key, value);
    }

}
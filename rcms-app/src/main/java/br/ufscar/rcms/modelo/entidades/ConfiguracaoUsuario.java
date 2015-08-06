package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "configuracao_usuario")
public class ConfiguracaoUsuario extends Configuracao {

    private static final long serialVersionUID = 175415876271187864L;

    @ManyToOne
    private Usuario usuario;

    public ConfiguracaoUsuario() {/* Serialization */}

    public ConfiguracaoUsuario(final Tipo tipo, final Usuario usuario) {
        super(tipo, null);
        this.usuario = usuario;
    }
}
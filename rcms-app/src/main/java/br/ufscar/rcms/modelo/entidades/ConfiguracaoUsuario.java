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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!super.equals(obj)) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        ConfiguracaoUsuario other = (ConfiguracaoUsuario) obj;
        if (usuario == null) {
            if (other.usuario != null) {
                return false;
            }
        } else if (!usuario.equals(other.usuario)) {
            return false;
        }
        return true;
    }

}
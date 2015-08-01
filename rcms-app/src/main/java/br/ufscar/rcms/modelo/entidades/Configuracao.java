package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "configuracao")
public class Configuracao extends Entidade {

    private static final long serialVersionUID = -3474384694113846818L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracao")
    private Long idConfiguracao;

    @Enumerated(EnumType.STRING)
    private Tipos key;

    private String value;

    public Configuracao() {/* Serialization */}

    public Configuracao(final Tipos key) {
        this(key, null);
    }

    public Configuracao(final Tipos key, final String value) {
        this.key = key;
        this.value = value;
    }

    public Long getIdConfiguracao() {
        return idConfiguracao;
    }

    public void setIdConfiguracao(final Long idConfiguracao) {
        this.idConfiguracao = idConfiguracao;
    }

    public Tipos getKey() {
        return key;
    }

    public void setKey(final Tipos key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((key == null) ? 0 : key.hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Configuracao other = (Configuracao) obj;
        if (key != other.key) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public enum Tipos {
        ESTILO_ADMIN("Estilo admin"),
        ESTILO_PORTAL("Estilo portal"),
        IDIOMA("Idioma"),
        IMPORTACAO_LATTES_AUTOMATICA("Importação lattes automática");

        private final String descricao;

        private Tipos(final String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }
}
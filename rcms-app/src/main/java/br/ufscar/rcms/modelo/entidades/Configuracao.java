package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name = "configuracao")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Configuracao extends Entidade {

    private static final long serialVersionUID = -3474384694113846818L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracao")
    private Long idConfiguracao;

    @Enumerated(EnumType.STRING)
    private Tipo key;

    private String value;

    public Configuracao() {/* Serialization */}

    public Configuracao(final Tipo key, final String value) {
        this.key = key;
        this.value = value;
    }

    public Long getIdConfiguracao() {
        return idConfiguracao;
    }

    public void setIdConfiguracao(final Long idConfiguracao) {
        this.idConfiguracao = idConfiguracao;
    }

    public Tipo getKey() {
        return key;
    }

    public void setKey(final Tipo key) {
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
    public boolean equals(Object obj) {
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

    public enum Tipo {
        ESTILO_ADMIN,
        ESTILO_PORTAL,
        IDIOMA,
        IMPORTACAO_LATTES_AUTOMATICA,
        MICROSERVICE_AMOUNT_PRODUCAO_BY_RESEARCHER,
        MICROSERVICE_AMOUNT_PRODUCAO_BY_YEAR, INDICE_CAROUSEL,
        INDICE_PROJETO_PESQUISA,
        INDICE_PRODUCAO;
    }
}
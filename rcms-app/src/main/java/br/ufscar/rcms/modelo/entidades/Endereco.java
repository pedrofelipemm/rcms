package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "endereco")
public class Endereco extends Entidade {

    private static final long serialVersionUID = -8224274709519593945L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_endereco")
    private Long idEndereco;

    @Column(name = "endereco_profissional", nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String enderecoProfissional;

    @Column(name = "latitude")
    private Double enderecoProfissionalLatitude;

    @Column(name = "longitude")
    private Double enderecoProfissionalLongitude;

    @OneToOne
    @JoinColumn(name = "id_pesquisador", foreignKey = @ForeignKey(name = "fk_endereco_pesquisador"))
    private Pesquisador pesquisador;

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(final Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(final Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getEnderecoProfissional() {
        return enderecoProfissional;
    }

    public void setEnderecoProfissional(final String enderecoProfissional) {
        this.enderecoProfissional = enderecoProfissional;
    }

    public Double getEnderecoProfissionalLatitude() {
        return enderecoProfissionalLatitude;
    }

    public void setEnderecoProfissionalLatitude(final Double enderecoProfissionalLatitude) {
        this.enderecoProfissionalLatitude = enderecoProfissionalLatitude;
    }

    public Double getEnderecoProfissionalLongitude() {
        return enderecoProfissionalLongitude;
    }

    public void setEnderecoProfissionalLongitude(final Double enderecoProfissionalLongitude) {
        this.enderecoProfissionalLongitude = enderecoProfissionalLongitude;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idEndereco == null) ? 0 : idEndereco.hashCode());
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
        if (!(obj instanceof Endereco)) {
            return false;
        }
        Endereco other = (Endereco) obj;
        if (idEndereco == null) {
            if (other.idEndereco != null) {
                return false;
            }
        } else if (!idEndereco.equals(other.idEndereco)) {
            return false;
        }
        return true;
    }

}
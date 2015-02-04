package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ENDERECO")
public class Endereco extends Entidade {

    private static final long serialVersionUID = -8224274709519593945L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;

    @Column(nullable = false, length = COLUMN_DEFAULT_LENGTH)
    private String enderecoProfissional;

    @Column
    private double enderecoProfissionalLatitude;

    @Column
    private double enderecoProfissionalLongitude;

    @OneToOne
    private Pesquisador pesquisador;

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

    public Long getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Long idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getEnderecoProfissional() {
        return enderecoProfissional;
    }

    public void setEnderecoProfissional(String enderecoProfissional) {
        this.enderecoProfissional = enderecoProfissional;
    }

    public double getEnderecoProfissionalLatitude() {
        return enderecoProfissionalLatitude;
    }

    public void setEnderecoProfissionalLatitude(double enderecoProfissionalLatitude) {
        this.enderecoProfissionalLatitude = enderecoProfissionalLatitude;
    }

    public double getEnderecoProfissionalLongitude() {
        return enderecoProfissionalLongitude;
    }

    public void setEnderecoProfissionalLongitude(double enderecoProfissionalLongitude) {
        this.enderecoProfissionalLongitude = enderecoProfissionalLongitude;
    }
}
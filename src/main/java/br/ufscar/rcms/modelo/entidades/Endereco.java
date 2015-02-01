package br.ufscar.rcms.modelo.entidades;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ENDERECO")
public class Endereco extends Entidade {

    private static final long serialVersionUID = -8224274709519593945L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEndereco;

    @Column(nullable = false)
    private String enderecoProfissional;

    @Column
    private double enderecoProfissionalLatitude;

    @Column
    private double enderecoProfissionalLongitude;

    @OneToMany(mappedBy = "endereco")
    private List<Pesquisador> pesquisadores;

    public List<Pesquisador> getPesquisadores() {
        return pesquisadores;
    }

    public void setPesquisadores(List<Pesquisador> pesquisadores) {
        this.pesquisadores = pesquisadores;
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
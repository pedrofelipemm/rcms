package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MIDIA")
public class Midia extends Entidade {

    private static final long serialVersionUID = 3759933099478534637L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idMidia;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private ProjetoPesquisa projetoPesquisa;

    @Column(nullable = false)
    private byte[] midia;

    public Integer getIdMidia() {
        return idMidia;
    }

    public void setIdMidia(Integer idMidia) {
        this.idMidia = idMidia;
    }

    public ProjetoPesquisa getProjetoPesquisa() {
        return projetoPesquisa;
    }

    public void setProjetoPesquisa(ProjetoPesquisa projetoPesquisa) {
        this.projetoPesquisa = projetoPesquisa;
    }

    public byte[] getMidia() {

        if (midia == null) {
            return new byte[0];
        }

        byte[] copyOfMidia = new byte[midia.length];
        System.arraycopy(midia, 0, copyOfMidia, 0, midia.length);

        return copyOfMidia;
    }

    public void setMidia(byte[] midia) {

        if (midia == null) {
            return;
        }

        byte[] copyOfMidia = new byte[midia.length];
        System.arraycopy(midia, 0, copyOfMidia, 0, midia.length);

        this.midia = copyOfMidia;
    }
}
package br.ufscar.rcms.modelo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "\"MIDIA\"")
public class Midia extends Entidade {

    private static final long serialVersionUID = 3759933099478534637L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_midia")
    private Integer idMidia;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_projeto_pesquisa", foreignKey = @ForeignKey(name = "fk_midia_projeto_pesquisa"))
    private ProjetoPesquisa projetoPesquisa;

    @Column(name = "midia", nullable = false)
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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idMidia == null) ? 0 : idMidia.hashCode());
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
        if (!(obj instanceof Midia)) {
            return false;
        }
        Midia other = (Midia) obj;
        if (idMidia == null) {
            if (other.idMidia != null) {
                return false;
            }
        } else if (!idMidia.equals(other.idMidia)) {
            return false;
        }
        return true;
    }
}
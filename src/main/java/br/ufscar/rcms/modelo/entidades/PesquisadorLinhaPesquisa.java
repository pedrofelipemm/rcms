package br.ufscar.rcms.modelo.entidades;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import br.ufscar.rcms.modelo.entidades.pk.PesquisadorLinhaPesquisaPK;

@Entity
@Table(name = "\"PESQUISADOR_LINHA_PESQUISA\"")
public class PesquisadorLinhaPesquisa extends Entidade {

    private static final long serialVersionUID = 2171967260802918355L;

    @EmbeddedId
    private PesquisadorLinhaPesquisaPK pesquisadorLinhaPesquisaPK;

    public PesquisadorLinhaPesquisaPK getPesquisadorLinhaPesquisaPK() {
        return pesquisadorLinhaPesquisaPK;
    }

    public void setPesquisadorLinhaPesquisaPK(PesquisadorLinhaPesquisaPK compreensaoIdiomaPK) {
        this.pesquisadorLinhaPesquisaPK = compreensaoIdiomaPK;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pesquisadorLinhaPesquisaPK == null) ? 0 : pesquisadorLinhaPesquisaPK.hashCode());
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
        if (!(obj instanceof PesquisadorLinhaPesquisa)) {
            return false;
        }
        PesquisadorLinhaPesquisa other = (PesquisadorLinhaPesquisa) obj;
        if (pesquisadorLinhaPesquisaPK == null) {
            if (other.pesquisadorLinhaPesquisaPK != null) {
                return false;
            }
        } else if (!pesquisadorLinhaPesquisaPK.equals(other.pesquisadorLinhaPesquisaPK)) {
            return false;
        }
        return true;
    }
}

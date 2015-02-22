package br.ufscar.rcms.modelo.entidades.pk;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import br.ufscar.rcms.modelo.entidades.Entidade;
import br.ufscar.rcms.modelo.entidades.LinhaPesquisa;
import br.ufscar.rcms.modelo.entidades.Pesquisador;

@Embeddable
public class PesquisadorLinhaPesquisaPK extends Entidade {

    private static final long serialVersionUID = 7672917049825128357L;

    @ManyToOne
    private LinhaPesquisa linhaPesquisa;

    @ManyToOne
    private Pesquisador pesquisador;

    public PesquisadorLinhaPesquisaPK() {
    }

    public PesquisadorLinhaPesquisaPK(LinhaPesquisa linhaPesquisa, Pesquisador pesquisador) {
        this.linhaPesquisa = linhaPesquisa;
        this.pesquisador = pesquisador;
    }

    public LinhaPesquisa getLinhaPesquisa() {
        return linhaPesquisa;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((linhaPesquisa == null) ? 0 : linhaPesquisa.hashCode());
        result = prime * result + ((pesquisador == null) ? 0 : pesquisador.hashCode());
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
        if (!(obj instanceof PesquisadorLinhaPesquisaPK)) {
            return false;
        }
        PesquisadorLinhaPesquisaPK other = (PesquisadorLinhaPesquisaPK) obj;
        if (linhaPesquisa == null) {
            if (other.linhaPesquisa != null) {
                return false;
            }
        } else if (!linhaPesquisa.equals(other.linhaPesquisa)) {
            return false;
        }
        if (pesquisador == null) {
            if (other.pesquisador != null) {
                return false;
            }
        } else if (!pesquisador.equals(other.pesquisador)) {
            return false;
        }
        return true;
    }
}
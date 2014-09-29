package br.ufscar.rcms.servico;

import java.io.Serializable;

import br.ufscar.rcms.modelo.entidades.Pesquisador;

public interface PesquisadorService extends Serializable {

    public abstract void salvar(Pesquisador pesquisador);

}

package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.Pesquisador;

public interface PesquisadorService extends Serializable {

    void salvar(Pesquisador pesquisador);

    List<Pesquisador> buscarTodos();
}

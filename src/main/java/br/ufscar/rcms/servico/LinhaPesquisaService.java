package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.LinhaPesquisa;

public interface LinhaPesquisaService extends Serializable {

    void salvar(LinhaPesquisa linhaPesquisa);

    void salvarOuAtualizar(LinhaPesquisa linhaPesquisa);

    void remover(LinhaPesquisa linhaPesquisa);

    List<LinhaPesquisa> buscarTodos();
}

package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.LinhaDePesquisa;

public interface LinhaDePesquisaService extends Serializable {

    void salvar(LinhaDePesquisa linhaPesquisa);

    void salvarOuAtualizar(LinhaDePesquisa linhaPesquisa);

    void remover(LinhaDePesquisa linhaPesquisa);

    List<LinhaDePesquisa> buscarTodas();
}

package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.AreaAtuacao;

public interface AreaAtuacaoService extends Serializable {

    void salvar(AreaAtuacao area);

    void alterar(AreaAtuacao area);
    
    void remover(AreaAtuacao area);

    List<AreaAtuacao> buscarTodas();

    List<AreaAtuacao> buscarPorDescricao(String Descricao);
}

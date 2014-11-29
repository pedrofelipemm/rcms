package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;

public interface GrandeAreaAtuacaoService extends Serializable {

    void salvar(GrandeAreaAtuacao area);

    void alterar(GrandeAreaAtuacao area);
    
    void remover(GrandeAreaAtuacao area);

    List<GrandeAreaAtuacao> buscarTodas();

    List<GrandeAreaAtuacao> buscarPorDescricao(String Descricao);
}
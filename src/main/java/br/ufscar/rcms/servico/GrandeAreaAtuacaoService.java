package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.GrandeAreaAtuacao;
import br.ufscar.rcms.servico.exception.RCMSException;

public interface GrandeAreaAtuacaoService extends Serializable {

    void salvar(GrandeAreaAtuacao area);

    void saveOrUpdate(GrandeAreaAtuacao grandeAreaAtuacao) throws RCMSException;

    void alterar(GrandeAreaAtuacao area);

    void remover(GrandeAreaAtuacao area);

    List<GrandeAreaAtuacao> buscarTodas();

    List<GrandeAreaAtuacao> buscarPorDescricao(String Descricao);
}
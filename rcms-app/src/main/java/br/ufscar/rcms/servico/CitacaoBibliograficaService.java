package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;

public interface CitacaoBibliograficaService extends Serializable {

    CitacaoBibliografica buscarPorNomeCitacao(String nomeCitacao);

    Boolean exists(String nomeCitacao);
    
    void salvar(CitacaoBibliografica citacaoBibliografica);

    void remover(List<CitacaoBibliografica> citacoesBibliograficas);

    void remover(CitacaoBibliografica citacaoBibliografica);
    
    List<CitacaoBibliografica> buscarTodas();

}

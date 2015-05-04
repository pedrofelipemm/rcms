package br.ufscar.rcms.servico;

import java.io.Serializable;
import java.util.List;

import br.ufscar.rcms.modelo.entidades.CitacaoBibliografica;

public interface CitacaoBibliograficaService extends Serializable {

    List<CitacaoBibliografica> buscarCitacaoBibliografica(Long idPesquisador);

}

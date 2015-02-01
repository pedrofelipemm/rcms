package br.ufscar.rcms.servico;

import java.io.IOException;
import java.io.Serializable;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.lattes.PesquisadorLattes;
import br.ufscar.rcms.servico.exception.CurriculoLattesNaoEncontradoException;

public interface LattesService extends Serializable {

    void executarComandoLattes(Pesquisador pesquisador) throws IOException;

    PesquisadorLattes carregarCurriculoLattes(String codigoLattes) throws CurriculoLattesNaoEncontradoException;
}
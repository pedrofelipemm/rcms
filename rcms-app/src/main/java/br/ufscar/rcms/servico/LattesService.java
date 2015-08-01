package br.ufscar.rcms.servico;

import java.io.Serializable;

import br.ufscar.rcms.modelo.entidades.Pesquisador;
import br.ufscar.rcms.modelo.lattes.PesquisadorLattes;
import br.ufscar.rcms.servico.exception.ArquivoNaoEncontradoException;
import br.ufscar.rcms.servico.exception.CurriculoLattesNaoEncontradoException;
import br.ufscar.rcms.servico.exception.RCMSException;

public interface LattesService extends Serializable {

    PesquisadorLattes carregarCurriculoLattes(String codigoLattes) throws CurriculoLattesNaoEncontradoException, ArquivoNaoEncontradoException;

    Pesquisador salvarDadosLattes(Pesquisador pesquisdor) throws RCMSException;
}
package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoIndice;

public interface ConfiguracaoDAO extends BaseDAO<Configuracao, Long> {

    List<Configuracao> buscarPorTipo(Tipo... tipos);

    ConfiguracaoIndice buscarPorIdETipo(Long id, Tipo tipo);
}
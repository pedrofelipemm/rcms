package br.ufscar.rcms.dao;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoIndice;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoSistema;

public interface ConfiguracaoDAO extends BaseDAO<Configuracao, Long> {

    List<Configuracao> buscarPorTipo(Tipo... tipos);

    ConfiguracaoSistema buscarPorKey(Tipo tipo);

    ConfiguracaoIndice buscarPorIdETipo(Long id, Tipo tipo);
}
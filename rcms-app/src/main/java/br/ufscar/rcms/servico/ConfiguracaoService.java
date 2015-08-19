package br.ufscar.rcms.servico;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoIndice;

public interface ConfiguracaoService {

    Configuracao saveOrUpdate(Configuracao configuracao);

    List<Configuracao> buscarPorTipo(Tipo... tipos);

    ConfiguracaoIndice buscarPorIdETipo(Long id, Tipo tipo);

    void remover(Configuracao configuracao);
}
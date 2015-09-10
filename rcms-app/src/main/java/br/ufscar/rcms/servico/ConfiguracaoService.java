package br.ufscar.rcms.servico;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoIndice;
import br.ufscar.rcms.modelo.entidades.ConfiguracaoSistema;

public interface ConfiguracaoService {

    Configuracao saveOrUpdate(Configuracao configuracao);

    List<Configuracao> buscarPorTipo(Tipo... tipos);
    
    boolean verificaConfiguracao(final Tipo... tipos);

    ConfiguracaoIndice buscarPorIdETipo(Long id, Tipo tipo);

    void remover(Configuracao configuracao);

    ConfiguracaoSistema buscarPorKey(Tipo tipo);
}
package br.ufscar.rcms.servico;

import java.util.List;

import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;

public interface ConfiguracaoService {

    Configuracao saveOrUpdate(Configuracao configuracao);

    List<Configuracao> buscarPorTipo(Tipo... tipos);
}
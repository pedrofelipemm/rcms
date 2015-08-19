package br.ufscar.rcms.servico.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufscar.rcms.dao.ConfiguracaoDAO;
import br.ufscar.rcms.modelo.entidades.Configuracao;
import br.ufscar.rcms.modelo.entidades.Configuracao.Tipo;
import br.ufscar.rcms.servico.ConfiguracaoService;

@Service("configuracaoService")
@Transactional
public class ConfiguracaoServiceImpl implements ConfiguracaoService {

    @Autowired
    private ConfiguracaoDAO configuracaoDAO;

    @Override
    public Configuracao saveOrUpdate(final Configuracao configuracao) {
        return configuracaoDAO.saveOrUpdate(configuracao);
    }

    @Override
    public List<Configuracao> buscarPorTipo(final Tipo... tipos) {
        return configuracaoDAO.buscarPorTipo(tipos);
    }
}
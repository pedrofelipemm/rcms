package br.ufscar.rcms.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.ConfiguracaoDAO;
import br.ufscar.rcms.modelo.entidades.Configuracao;

@Repository
public class ConfiguracaoDAOImpl extends BaseDAOImpl<Configuracao, Long>implements ConfiguracaoDAO {

    private static final long serialVersionUID = 32735639850047914L;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConfiguracaoDAOImpl.class);

    public ConfiguracaoDAOImpl() {
        setClazz(Configuracao.class);
    }
}
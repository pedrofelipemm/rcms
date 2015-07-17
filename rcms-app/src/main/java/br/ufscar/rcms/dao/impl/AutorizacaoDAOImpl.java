package br.ufscar.rcms.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.AutorizacaoDAO;
import br.ufscar.rcms.modelo.entidades.Autorizacao;

@Repository
public class AutorizacaoDAOImpl extends BaseDAOImpl<Autorizacao, Long> implements AutorizacaoDAO {

    private static final long serialVersionUID = 32735639850047914L;
    private static final Logger LOGGER = LoggerFactory.getLogger(AutorizacaoDAOImpl.class);

    public AutorizacaoDAOImpl() {
        setClazz(Autorizacao.class);
    }


}
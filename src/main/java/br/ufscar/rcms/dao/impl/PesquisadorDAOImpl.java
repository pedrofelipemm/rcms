package br.ufscar.rcms.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.PesquisadorDAO;
import br.ufscar.rcms.modelo.entidades.Pesquisador;

@Repository
public class PesquisadorDAOImpl extends BaseDAOImpl<Pesquisador, Long> implements PesquisadorDAO {

    private static final long serialVersionUID = 4493458867776635947L;
    private static final Logger LOGGER = LoggerFactory.getLogger(PesquisadorDAOImpl.class);

    public PesquisadorDAOImpl() {

        setClazz(Pesquisador.class);
    }
}
package br.ufscar.rcms.dao.impl;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.LinhaPesquisaDAO;
import br.ufscar.rcms.modelo.entidades.LinhaPesquisa;

@Repository
public class LinhaPesquisaDAOImpl extends BaseDAOImpl<LinhaPesquisa, Long> implements LinhaPesquisaDAO {

    private static final long serialVersionUID = 2019569198373810443L;

    public LinhaPesquisaDAOImpl() {

        setClazz(LinhaPesquisa.class);
    }
}

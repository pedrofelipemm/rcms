package br.ufscar.rcms.dao.impl;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.LinhaDePesquisaDAO;
import br.ufscar.rcms.modelo.entidades.LinhaDePesquisa;

@Repository
public class LinhaDePesquisaDAOImpl extends BaseDAOImpl<LinhaDePesquisa, Long> implements LinhaDePesquisaDAO {

    private static final long serialVersionUID = 2019569198373810443L;

    public LinhaDePesquisaDAOImpl() {

        setClazz(LinhaDePesquisa.class);
    }
}

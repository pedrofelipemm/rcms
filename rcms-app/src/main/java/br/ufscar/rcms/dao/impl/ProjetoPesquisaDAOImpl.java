package br.ufscar.rcms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.ProjetoPesquisaDAO;
import br.ufscar.rcms.modelo.entidades.ProjetoPesquisa;

@Repository
public class ProjetoPesquisaDAOImpl extends BaseDAOImpl<ProjetoPesquisa, Long> implements ProjetoPesquisaDAO {

    private static final long serialVersionUID = 4493458867776635947L;

    public ProjetoPesquisaDAOImpl() {
        setClazz(ProjetoPesquisa.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<ProjetoPesquisa> buscarTodosOrderByNome() {
        return createQuery("from " + getClazz().getName() + " order by nome").getResultList();
    }
}
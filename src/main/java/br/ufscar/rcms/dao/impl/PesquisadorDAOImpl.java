package br.ufscar.rcms.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.PesquisadorDAO;
import br.ufscar.rcms.modelo.entidades.Pesquisador;

@Repository
public class PesquisadorDAOImpl extends BaseDAOImpl<Pesquisador, Long> implements PesquisadorDAO {

    private static final long serialVersionUID = 4493458867776635947L;

    public PesquisadorDAOImpl() {
        setClazz(Pesquisador.class);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Pesquisador> buscarTodosOrderByNome() {
        return createQuery("from " + getClazz().getName() + " order by nome").getResultList();
    }
}
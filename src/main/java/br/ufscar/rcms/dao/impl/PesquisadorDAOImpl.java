package br.ufscar.rcms.dao.impl;

import java.util.List;

import javax.persistence.Query;

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

    @SuppressWarnings("unchecked")
    @Override
    public List<Pesquisador> buscarTodosComIdioma(final Long idIdioma) {

        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT P FROM " + getClazz().getName() + " P ");
        jpql.append(" JOIN P.compreensaoIdiomas CI ");
        jpql.append(" JOIN CI.compreensaoIdiomaPK PK ");
        jpql.append(" JOIN PK.idioma I ");
        if (idIdioma != null) {
            jpql.append(" WHERE I.idIdioma = :idIdioma");
        }

        Query query = createQuery(jpql.toString());
        if (idIdioma != null) {
            query.setParameter("idIdioma", idIdioma);
        }

        return query.getResultList();
    }
}
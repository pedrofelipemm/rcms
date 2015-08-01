package br.ufscar.rcms.dao.impl;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.PesquisadorDAO;
import br.ufscar.rcms.modelo.entidades.Configuracao;
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

    @Override
    public Pesquisador buscarPorLogin(final String login) {

        if (isEmpty(login)) {
            throw new IllegalArgumentException("Login cannot be null!");
        }

        String jpql = "select p from " + getClazz().getName() + " p where p.login = :login";
        Query query = createQuery(jpql);
        query.setParameter("login", login);

        try {
            return (Pesquisador) query.getSingleResult();
        } catch (final NoResultException exception) {
            return null;
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Pesquisador> findToAutoImport() {

        String jpql = "select p from " + getClazz().getName() + " p "
                + "inner join p.configuracoes c "
                + "where c.key = '" + Configuracao.Tipos.IMPORTACAO_LATTES_AUTOMATICA + "' "
                + "and c.value = 'true' ";

        Query query = createQuery(jpql);
        return query.getResultList();
    }
}
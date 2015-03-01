package br.ufscar.rcms.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
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
    public Pesquisador buscarTodosDados(Long idUsuario) {

        StringBuilder jpql = new StringBuilder();
        jpql.append("select p from " + Pesquisador.class.getName() + " p ");
        jpql.append("left join fetch p.endereco e ");
        jpql.append("left join fetch p.formacoes f ");
        jpql.append("left join fetch p.compreensaoIdiomas ci ");
        jpql.append("left join fetch p.areaAtuacoes atu ");
        jpql.append("left join fetch p.citacaoBibliograficas cb ");
        jpql.append("left join fetch p.premios premios ");
        jpql.append("where p.idUsuario = :idUsuario ");

        Query query = createQuery(jpql.toString());
        query.setParameter("idUsuario", idUsuario);

        try {
            return (Pesquisador) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } catch (NonUniqueResultException e) {
            return null;
        }
    }
}
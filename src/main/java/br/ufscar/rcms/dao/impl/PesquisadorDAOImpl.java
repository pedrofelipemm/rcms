package br.ufscar.rcms.dao.impl;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.Query;

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

    @Override
    public Pesquisador buscarTodosDados(Long idUsuario) {

        StringBuilder jpql = new StringBuilder("select p from " + Pesquisador.class.getName() + " p ");
        jpql.append("left join fetch p.endereco ende ");
        jpql.append("left join fetch p.formacoes form ");
        jpql.append("left join fetch p.compreensaoIdiomas compId ");
        jpql.append("left join fetch p.areaAtuacoes atu ");
        jpql.append("left join fetch p.citacaoBibliograficas citBib ");
        jpql.append("left join fetch p.premios premios ");
        jpql.append("left join fetch p.participacaoEventos partEventos ");
        jpql.append("left join fetch p.organizacaoEventos orgEventos ");
        jpql.append("left join fetch p.projetosPesquisa projPesq ");
        jpql.append("left join fetch p.orientacoes orientacoes ");
        jpql.append("where p.idUsuario = :idUsuario ");

        Query query = createQuery(jpql.toString());
        query.setParameter("idUsuario", idUsuario);

        try {
            return (Pesquisador) query.getSingleResult();
        } catch (NoResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        } catch (NonUniqueResultException e) {
            LOGGER.error(e.getMessage(), e);
            return null;
        }
    }
}
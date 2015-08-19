package br.ufscar.rcms.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.ProducaoDAO;
import br.ufscar.rcms.modelo.entidades.Producao;

@Repository
public class ProducaoDAOImpl extends BaseDAOImpl<Producao, Long> implements ProducaoDAO {

    private static final long serialVersionUID = 5464576921352357099L;

    public ProducaoDAOImpl() {
        setClazz(Producao.class);
    }

    public Boolean exists(String titulo, Integer ano) {

        StringBuilder jpql = new StringBuilder();
        jpql.append("SELECT p FROM " + Producao.class.getName() + " p ");
        jpql.append("WHERE p.titulo = :titulo AND p.ano = :ano ");

        Query query = createQuery(jpql.toString());
        query.setParameter("titulo", titulo);
        query.setParameter("ano", ano);

        return query.getResultList().size() > 0;
    }

    @Override
    public <T> List<T> buscarProducoes(Class<T> clazz) {

        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT DISTINCT P FROM " + clazz.getName() + " P ");
        jpql.append(" JOIN FETCH P.autores A ");
        jpql.append(" JOIN FETCH A.citacaoBibliografica CB ");
        jpql.append(" ORDER BY P.ano DESC ");

        Query query = createQuery(jpql.toString());

        return query.getResultList();
    }

    public <T> List<T> buscarProducoes(Class<T> clazz, final Long idUsuario) {

        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT DISTINCT P FROM " + clazz.getName() + " P ");
        jpql.append(" JOIN FETCH P.autores A ");
        jpql.append(" JOIN FETCH A.citacaoBibliografica CB ");
        jpql.append(" WHERE CB.idCitacaoBibliografica IN (SELECT CBA FROM CitacaoBibliografica CBA WHERE CBA.pesquisador.idUsuario = :idUsuario) ");
        jpql.append(" ORDER BY P.ano DESC ");

        Query query = createQuery(jpql.toString());

        if (idUsuario != null) {
            query.setParameter("idUsuario", idUsuario);
        }

        return query.getResultList();
    }
}

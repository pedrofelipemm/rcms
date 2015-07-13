package br.ufscar.rcms.dao.impl;

import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.ProducaoDAO;
import br.ufscar.rcms.modelo.entidades.ArtigoEmPeriodico;
import br.ufscar.rcms.modelo.entidades.Producao;

@Repository
public class ProducaoDAOImpl extends BaseDAOImpl<Producao, Long> implements ProducaoDAO {

    private static final long serialVersionUID = 5464576921352357099L;

    public ProducaoDAOImpl() {
        setClazz(Producao.class);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ArtigoEmPeriodico> buscarArtigosEmPeriodicos(final Long idUsuario) {

        StringBuilder jpql = new StringBuilder();
        jpql.append(" SELECT DISTINCT AP FROM ArtigoEmPeriodico AP ");
        jpql.append(" JOIN FETCH AP.citacaoBibliograficas CB ");
        jpql.append(" WHERE CB.idCitacaoBibliografica IN (SELECT CBA FROM CitacaoBibliografica CBA WHERE CBA.pesquisador.idUsuario = :idUsuario) ");

        Query query = createQuery(jpql.toString());

        if (idUsuario != null) {
            query.setParameter("idUsuario", idUsuario);
        }

        return query.getResultList();
    }
}

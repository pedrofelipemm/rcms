package br.ufscar;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import br.ufscar.util.EntityMapper;

public class CustomScorecardRepositoryImpl implements CustomScorecardRepository {

    @PersistenceUnit
    private EntityManager entityManager;

    @Override
    public List<ProducaoPesquisador> findAll() {

        StringBuilder sql = new StringBuilder("select * from producao prod ");
        sql.append("join producao_citacao_bibliografica pcb on prod.id_producao = pcb.id_citacao_bibliografica ");
        sql.append("join citacao_bibliografica cb on cb.id_citacao_bibliografica = pcb.id_citacao_bibliografica ");
        sql.append("join pesquisador pesq on pesq.id_usuario = cb.id_pesquisador");

        Query query = entityManager.createNativeQuery(sql.toString());

        return EntityMapper.getResultList(query, ProducaoPesquisador.class);
    }

}
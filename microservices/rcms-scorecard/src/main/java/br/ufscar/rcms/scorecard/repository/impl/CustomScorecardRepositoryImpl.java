package br.ufscar.rcms.scorecard.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import br.ufscar.rcms.scorecard.model.entity.ProducaoPesquisador;
import br.ufscar.rcms.scorecard.repository.CustomScorecardRepository;
import br.ufscar.rcms.scorecard.util.EntityMapper;

public class CustomScorecardRepositoryImpl implements CustomScorecardRepository {

    @PersistenceUnit
    private EntityManager entityManager;

    @Override
    public List<ProducaoPesquisador> findAll() {

        String sql = "select * from Producao prod "
                + "join producao_citacao_bibliografica pcb on prod.id_producao = pcb.id_citacao_bibliografica "
                + "join citacao_bibliografica cb on cb.id_citacao_bibliografica = pcb.id_citacao_bibliografica "
                + "join pesquisador pesq on pesq.id_usuario = cb.id_pesquisador";

        Query query = entityManager.createNativeQuery(sql);
        return EntityMapper.getResultList(query, ProducaoPesquisador.class);
    }
}
// package br.ufscar.rcms.scorecard.repository.impl;
//
// import java.util.List;
//
// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceUnit;
// import javax.persistence.Query;
//
// import br.ufscar.rcms.scorecard.model.entity.Producao;
// import br.ufscar.rcms.scorecard.repository.ProducaoRepository;
// import br.ufscar.rcms.scorecard.util.EntityMapper;
//
// public class ProducaoRepositoryImpl implements ProducaoRepository {
//
// private static final long serialVersionUID = -310904177733386868L;
//
// @PersistenceUnit
// private EntityManager entityManager;
//
// @Override
// public List<Producao> findAll() {
//
// String sql = "select * from Producao prod "
// + "join producao_citacao_bibliografica pcb on prod.id_producao = pcb.id_citacao_bibliografica "
// + "join citacao_bibliografica cb on cb.id_citacao_bibliografica = pcb.id_citacao_bibliografica "
// + "join pesquisador pesq on pesq.id_usuario = cb.id_pesquisador";
//
// Query query = entityManager.createNativeQuery(sql);
// return EntityMapper.getResultList(query, Producao.class);
// }
// }
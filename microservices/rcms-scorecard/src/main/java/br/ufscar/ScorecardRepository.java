package br.ufscar;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScorecardRepository extends CrudRepository<ProducaoPesquisador, Long>, CustomScorecardRepository {

}
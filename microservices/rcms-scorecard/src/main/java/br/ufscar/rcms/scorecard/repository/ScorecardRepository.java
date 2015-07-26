package br.ufscar.rcms.scorecard.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufscar.rcms.scorecard.model.entity.ProducaoPesquisador;

@Repository
@Transactional
public interface ScorecardRepository extends JpaRepository<ProducaoPesquisador, Long>, CustomScorecardRepository {

}
package br.ufscar.rcms.scorecard.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufscar.rcms.scorecard.model.entity.Producao;

@Repository
@Transactional
public interface ProducaoRepository extends JpaRepository<Producao, Long> {

}
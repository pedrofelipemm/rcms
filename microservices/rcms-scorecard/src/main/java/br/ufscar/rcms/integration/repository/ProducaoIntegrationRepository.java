package br.ufscar.rcms.integration.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufscar.rcms.scorecard.model.entity.Producao;

@Repository
public interface ProducaoIntegrationRepository extends JpaRepository<Producao, Long> {

}
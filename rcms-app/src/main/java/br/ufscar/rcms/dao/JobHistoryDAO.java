package br.ufscar.rcms.dao;

import org.springframework.stereotype.Repository;

import br.ufscar.rcms.modelo.entidades.JobHistory;

@Repository
public interface JobHistoryDAO extends BaseDAO<JobHistory, Long> {

}
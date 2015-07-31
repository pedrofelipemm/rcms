package br.ufscar.rcms.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import br.ufscar.rcms.dao.JobHistoryDAO;
import br.ufscar.rcms.modelo.entidades.JobHistory;

@Repository
public class JobHistoryDAOImpl extends BaseDAOImpl<JobHistory, Long>implements JobHistoryDAO {

    private static final long serialVersionUID = 32735639850047914L;
    private static final Logger LOGGER = LoggerFactory.getLogger(JobHistoryDAOImpl.class);

    public JobHistoryDAOImpl() {
        setClazz(JobHistory.class);
    }
}
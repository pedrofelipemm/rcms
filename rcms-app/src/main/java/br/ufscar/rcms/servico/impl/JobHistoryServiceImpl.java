package br.ufscar.rcms.servico.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.ufscar.rcms.dao.JobHistoryDAO;
import br.ufscar.rcms.modelo.entidades.JobHistory;
import br.ufscar.rcms.servico.JobHistoryService;

@Service
@Transactional
public class JobHistoryServiceImpl implements JobHistoryService {

    @Autowired
    private JobHistoryDAO jobDAO;

    @Override
    public void saveOrUpdate(final JobHistory jobHistory) {
        jobDAO.saveOrUpdate(jobHistory);
    }
}
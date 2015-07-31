package br.ufscar.rcms.servico;

import br.ufscar.rcms.modelo.entidades.JobHistory;

public interface JobHistoryService {

    void saveOrUpdate(JobHistory jobHistory);

}
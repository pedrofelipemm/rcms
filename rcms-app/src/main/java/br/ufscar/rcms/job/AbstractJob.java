package br.ufscar.rcms.job;

import static br.ufscar.rcms.commons.util.MiscellanyUtil.isEmpty;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.ufscar.rcms.modelo.entidades.JobHistory;
import br.ufscar.rcms.servico.JobHistoryService;

@Component
public abstract class AbstractJob {

    private final String jobName;

    @Autowired
    private JobHistoryService jobHistoryService;

    public AbstractJob() {
        jobName = getJobName();
        validateState();
    }

    // TODO PEDRO PROPERTIES
    @Scheduled(fixedRate = 3600000)
    private void run() {
        beforeProcess();
        process();
        afterProcess();
    }

    protected abstract void process();

    protected abstract String getJobName();

    private void beforeProcess() {
        // TODO Auto-generated method stub
    }

    private void afterProcess() {
        saveJobHistory();
    }

    private void saveJobHistory() {
        jobHistoryService.saveOrUpdate(new JobHistory(jobName, new Date()));
    }

    private void validateState() {
        if (isEmpty(jobName)) {
            throw new IllegalStateException("Field jobName must not be null.");
        }
    }
}
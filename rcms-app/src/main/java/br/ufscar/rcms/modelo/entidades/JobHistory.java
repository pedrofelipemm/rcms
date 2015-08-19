package br.ufscar.rcms.modelo.entidades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;

@Entity
@Table(name="job_history")
public class JobHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_job_history")
    private Long idJobHistory;

    @Column(name = "job_name", nullable = false)
    private String jobName;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    public JobHistory() {/* Serialization */}

    public JobHistory(final String jobName, final Date createdDate) {
        this.jobName = jobName;
        this.createdDate = createdDate;
    }

    public Long getIdJobHistory() {
        return idJobHistory;
    }

    public void setIdJobHistory(Long idJobHistory) {
        this.idJobHistory = idJobHistory;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idJobHistory == null) ? 0 : idJobHistory.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        JobHistory other = (JobHistory) obj;
        if (idJobHistory == null) {
            if (other.idJobHistory != null) {
                return false;
            }
        } else if (!idJobHistory.equals(other.idJobHistory)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
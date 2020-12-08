package fr.plb.jobv2.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Job")
public class Job implements Serializable {

	@Id
	@GeneratedValue(generator = "sequence-generator")
	@SequenceGenerator(name = "sequence-generator", sequenceName = "seqjob", initialValue = 5, allocationSize = 100)
    private Long idjob;

	@Column
    private String jobTitle;

	@Column
    private Long minSalary;

	@Column
    private Long maxSalary;

//	@ManyToMany(mappedBy = "jobs") on ne peut pas avoir à la fois @mappedby et @joinTable
	//du coté de tasks, on met que mappedby
	@ManyToMany
	@JoinTable(
	        name = "jobstasks", 
	        joinColumns = { @JoinColumn(name = "jobid", referencedColumnName = "idjob") }, 
	        inverseJoinColumns = { @JoinColumn(name = "taskid", referencedColumnName = "idtask") }
	    )
    private Set<Task> tasks = new HashSet<>();

	@ManyToOne
    private Employee employee;

    public Long getIdjob() {
        return idjob;
    }

    public void setIdjob(Long id) {
        this.idjob = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public Long getMinSalary() {
        return minSalary;
    }

    public void setMinSalary(Long minSalary) {
        this.minSalary = minSalary;
    }

    public Long getMaxSalary() {
        return maxSalary;
    }

    public void setMaxSalary(Long maxSalary) {
        this.maxSalary = maxSalary;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public Job() {
    	
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idjob == null) ? 0 : idjob.hashCode());
		result = prime * result + ((jobTitle == null) ? 0 : jobTitle.hashCode());
		result = prime * result + ((maxSalary == null) ? 0 : maxSalary.hashCode());
		result = prime * result + ((minSalary == null) ? 0 : minSalary.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Job other = (Job) obj;
		if (idjob == null) {
			if (other.idjob != null)
				return false;
		} else if (!idjob.equals(other.idjob))
			return false;
		if (jobTitle == null) {
			if (other.jobTitle != null)
				return false;
		} else if (!jobTitle.equals(other.jobTitle))
			return false;
		if (maxSalary == null) {
			if (other.maxSalary != null)
				return false;
		} else if (!maxSalary.equals(other.maxSalary))
			return false;
		if (minSalary == null) {
			if (other.minSalary != null)
				return false;
		} else if (!minSalary.equals(other.minSalary))
			return false;
		return true;
	}
    
    
}

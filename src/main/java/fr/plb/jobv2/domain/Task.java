package fr.plb.jobv2.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Task")
public class Task implements Serializable {

	@Id
	@GeneratedValue(generator = "sequence-generator")
	@SequenceGenerator(name = "sequence-generator", sequenceName = "seqtask", initialValue = 5, allocationSize = 100)
    private Long id;

	@Column
    private String title;

	@Column
    private String description;

//    private Set<Job> jobs = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

//    public Set<Job> getJobs() {
//        return jobs;
//    }
//
//    public void setJobs(Set<Job> jobs) {
//        this.jobs = jobs;
//    }
    
    public Task() {
    	
    }
}

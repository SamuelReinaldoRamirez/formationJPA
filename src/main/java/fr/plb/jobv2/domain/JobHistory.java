package fr.plb.jobv2.domain;

import fr.plb.jobv2.domain.enumeration.Language;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name="JobHistory")
public class JobHistory implements Serializable {

	@Id
	@GeneratedValue(generator = "sequence-generator")
	@SequenceGenerator(name = "sequence-generator", sequenceName = "seqjobHistory", initialValue = 5, allocationSize = 100)
    private Long id;

	@Column
    private Instant startDate;

	@Column
    private Instant endDate;

	//pas OneToOne car c'est une enum
	//enumerated car sinon, les enum sont des int en bdd et du coup si on change l'ordre des enum, ca va etre n'importe quoi dans la bdd
	@Column
	@Enumerated(EnumType.STRING)
    private Language language;

	@OneToOne
    private Job job;

	@OneToOne
    private Department department;

	@ManyToOne
    private Employee employee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getStartDate() {
        return startDate;
    }

    public void setStartDate(Instant startDate) {
        this.startDate = startDate;
    }

    public Instant getEndDate() {
        return endDate;
    }

    public void setEndDate(Instant endDate) {
        this.endDate = endDate;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
    
    public JobHistory() {
    	
    }
}

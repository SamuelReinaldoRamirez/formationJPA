package fr.plb.jobv2.domain;

import java.io.Serializable;
import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Employee")
public class Employee extends Person implements Serializable {

//	@Id
//	@GeneratedValue(generator = "sequence-generator")
//	@SequenceGenerator(name = "sequence-generator", sequenceName = "seqemployee", initialValue = 5, allocationSize = 100)
//    private Long id;

	@Column
    private Instant hireDate;

	@Column
    private Long salary;

	public Address getContacts() {
		return contacts;
	}

	public void setContacts(Address contacts) {
		this.contacts = contacts;
	}

	@Column
    private Long commissionPct;
	
	
	
	
	//on met @embedded si on n'a pas un set mais juste un champ simple 
//	@ElementCollection(targetClass = Address.class, fetch = FetchType.EAGER)
//	@JoinTable(name = "address", joinColumns = {​​​​​
//	        @JoinColumn(name = "firstName"),
//	        @JoinColumn(name = "lastName"),
//	        @JoinColumn(name = "phoneNumber")
//	}​​​​​)
//	private Set<Address> contacts;
	
	@Embedded
	private Address contacts;

	
	

//    private Set<Job> jobs = new HashSet<>();

	@ManyToOne
    private Employee manager;
	
	@ManyToOne
    private Department department;

//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

    public Instant getHireDate() {
        return hireDate;
    }

    public void setHireDate(Instant hireDate) {
        this.hireDate = hireDate;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public Long getCommissionPct() {
        return commissionPct;
    }

    public void setCommissionPct(Long commissionPct) {
        this.commissionPct = commissionPct;
    }

//    public Set<Job> getJobs() {
//        return jobs;
//    }
//
//    public void setJobs(Set<Job> jobs) {
//        this.jobs = jobs;
//    }
//
    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
    
    public Employee() {
    	
    }
}

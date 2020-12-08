package fr.plb.jobv2.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * A Department.
 */
@Entity
@Table(name="Department")
public class Department implements Serializable {

	@Id
	@GeneratedValue(generator = "sequence-generator")
	@SequenceGenerator(name = "sequence-generator", sequenceName = "seqdepartment", initialValue = 5, allocationSize = 100)
    private Long id;

	@Column
    private String departmentName;

	@OneToOne
    private Location location;

//    private Set<Employee> employees = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
//
//    public Set<Employee> getEmployees() {
//        return employees;
//    }
//
//    public void setEmployees(Set<Employee> employees) {
//        this.employees = employees;
//    }
    
    public Department() {
    	
    }
}

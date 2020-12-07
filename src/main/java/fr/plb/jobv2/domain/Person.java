package fr.plb.jobv2.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="Person")
public class Person implements Serializable {

//	@Id
//	@GeneratedValue(generator = "sequence-generator")
//	@SequenceGenerator(name = "sequence-generator", sequenceName = "seqperson", initialValue = 5, allocationSize = 100)
//    private Long id;

	@EmbeddedId
	private PersonId personId;

//    private String firstName;
//
//    private String lastName;
//
//    private String phoneNumber;
    
	@Column
    private String email;


//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }

//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//    
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    public void setPhoneNumber(String phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public Person() {
    	
    }
}

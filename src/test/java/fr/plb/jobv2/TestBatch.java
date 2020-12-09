package fr.plb.jobv2;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transaction;
import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.transaction.TestTransaction;

import fr.plb.jobv2.domain.Address;
import fr.plb.jobv2.domain.Employee;
import fr.plb.jobv2.domain.Job;
import fr.plb.jobv2.domain.PersonId;

@SpringBootTest(classes = Jobv2Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "server.port=8080")
public class TestBatch {
	
	private final Logger log = LoggerFactory.getLogger(EmployeeJoinedTest.class);

    @Autowired
    private EntityManager entityManager;
	
	@Test
	@Commit
	@Transactional
	void batchInsertTest() {
		for (int i = 0; i < 2000; i++) {
			if (i > 0 && i % 200 == 0) {
			//execute ce qu'il y a dans le cache (les requetes sql)
				entityManager.flush();
			//clear le cache
				entityManager.clear();
				}
			Address address = new Address();
			address.setCity("Nice");
			address.setPostalCode("06000");
			address.setStateProvince("PACA");
	    	
			 PersonId personIdEmployee = new PersonId();
			 personIdEmployee.setFirstName("tata" + i);
			 personIdEmployee.setLastName("dupont");
			 personIdEmployee.setPhoneNumber("+336546455");
			
			 Employee employee = new Employee();
			 employee.setCommissionPct(2L);
			 employee.setHireDate(Instant.now());
			 employee.setSalary(100L);
			 employee.setContacts(address);
			 employee.setPersonId(personIdEmployee);
			 employee.setEmail("employee@toto.com");
			 
			 Set<Job> jobs  = new HashSet<Job>();
		    	
	    	Job job = new Job();
	    	job.setJobTitle("jobtitle");
	    	job.setMaxSalary(2500L);
	    	job.setMinSalary(1500L);
	    	job.setEmployee(employee);
	    	
	    	Job job2 = new Job();
	    	job2.setJobTitle("jobtitle2");
	    	job2.setMaxSalary(2400L);
	    	job2.setMinSalary(1500L);
	    	job2.setEmployee(employee);
	    	
	    	jobs.add(job);
	    	jobs.add(job2);
	    	
	    	employee.setJobs(jobs);
			 
			//met la requete dans le cache 
			entityManager.persist(employee);
			}
		//sert pas pour la prod
		TestTransaction.end();
		TestTransaction.start();

		TypedQuery<Long> countTypedQuery = entityManager.createQuery("SELECT count(employee) FROM Employee employee", Long.class);		
		Long count = countTypedQuery.getSingleResult();
		
		for (int i = 0; i < count; i+=20) {
			log.info("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ" + i);			
			String query = "SELECT employee FROM Employee employee";
			TypedQuery<Employee> employeeTypedQuery = entityManager.createQuery(query, Employee.class);
			employeeTypedQuery.setFirstResult(i);
			employeeTypedQuery.setMaxResults(i+20);
			List<Employee> resultList = employeeTypedQuery.getResultList();
			for (Employee employee : resultList) {
				employee.setSalary(employee.getSalary()+3L);
				}
		}		
				
		TestTransaction.end();
		TestTransaction.start();

		TypedQuery<Employee> selectTypedQuery = entityManager.createQuery("SELECT employee FROM Employee employee", Employee.class);		
		selectTypedQuery.setFirstResult(1);
		selectTypedQuery.setMaxResults(10);
		List<Employee> resultList = selectTypedQuery.getResultList();
		for (Employee employee : resultList) {
			log.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA"+ employee.getSalary() + "");			
			}
		
		
		
	}
	

}

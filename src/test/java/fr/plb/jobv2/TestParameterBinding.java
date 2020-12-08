package fr.plb.jobv2;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
import fr.plb.jobv2.domain.Person;
import fr.plb.jobv2.domain.PersonId;


@SpringBootTest(classes = Jobv2Application.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource(properties = "server.port=8080")
//@SpringBootTest(classes = Jobv2Application.class)
public class TestParameterBinding {
	private final Logger log = LoggerFactory.getLogger(EmployeeJoinedTest.class);

    @Autowired
    private EntityManager entityManager;
    
    private Employee createEmployee(String firstname, String lastName, Long salary) {
	    Address address = new Address();
	    address.setCity("Nice");
	    address.setPostalCode("06000");
	    address.setStateProvince("PACA");
	    PersonId personIdEmployee = new PersonId();
	    personIdEmployee.setFirstName(firstname);
	    personIdEmployee.setLastName(lastName);
	    personIdEmployee.setPhoneNumber("+336546455");
	    Employee employee = new Employee();
	    employee.setCommissionPct(2L);
	    employee.setHireDate(Instant.now());
	    employee.setSalary(salary);
	    employee.setContacts(address);
	    employee.setPersonId(personIdEmployee);
	    employee.setEmail("employee@toto.com");
	    return employee;
	}

    @Test
    //fait le start
    @Transactional
    //pour transactionstart et end
    @Commit
    void joinedStrategyTest() {
		Address address = new Address();
		address.setCity("Nice");
		address.setPostalCode("06000");
		address.setStateProvince("PACA");
    	
		 PersonId personIdEmployee = new PersonId();
		 personIdEmployee.setFirstName("tata");
		 personIdEmployee.setLastName("dupont");
		 personIdEmployee.setPhoneNumber("+336546455");
		
		 Employee employee = new Employee();
		 employee.setCommissionPct(2L);
		 employee.setHireDate(Instant.now());
		 employee.setSalary(100L);
		 employee.setContacts(address);
		 employee.setPersonId(personIdEmployee);
		 employee.setEmail("employee@toto.com");
		
		 PersonId personId = new PersonId();
		 personId.setFirstName("toto");
		 personId.setLastName("dupont");
		 personId.setPhoneNumber("+336546755");
		 
		 Person person = new Person();
		 person.setEmail("person@toto.com");
		 person.setPersonId(personId);
		 
		 
		 //tasks
		 
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
    	
      

//	        entityManager.persist(person);
        entityManager.persist(employee);
//	    	entityManager.persist(job);

        TestTransaction.end();
        //breakpoint à start pour pouvoir observer dans H2 avant le rollback si j'ai bien compris
        TestTransaction.start();
        
        
        //la query utile
//        String query = "SELECT e FROM Employee as e join e.jobs as j WHERE j.maxSalary <= :maxsalary and j.minSalary >= :minsalary";
        String query = "SELECT DISTINCT e FROM Employee e join e.jobs as j WHERE j.maxSalary <= :maxsalary and j.minSalary >= :minsalary";
        TypedQuery<Employee> employeeTypedQuery = entityManager.createQuery(query, Employee.class);
        employeeTypedQuery.setParameter("maxsalary", 2500L);
        employeeTypedQuery.setParameter("minsalary", 1500L);
        List<Employee> resultList = employeeTypedQuery.getResultList();
        for (Employee e : resultList) {
        	log.info("EEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        	log.info(e.getEmail());
        }


        TypedQuery<Employee> employeeQuery = entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> employeeList = employeeQuery.getResultList();
        log.info("CCCCCCCCCCCCCCCCCCCCCCCCCCEmployee displaying : " + employeeList.stream().findFirst().get().getEmail());
        log.info("JOBS FROM EMPLOYEE : " + employeeList.get(0).getJobs().stream().findFirst().get().getJobTitle());
        
//	        TypedQuery<Person> personQuery = entityManager.createQuery("FROM Person", Person.class);
//	        List<Person> personList = personQuery.getResultList();
//	        log.info("BBBBBBBBBBBBBBBBBBBBBBBBBBPerson displaying : " + personList.stream().findFirst().get().getEmail());
        
        TypedQuery<Job> jobQuery = entityManager.createQuery("FROM Job", Job.class);
        List<Job> jobList = jobQuery.getResultList();
        log.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAJob displaying : " + jobList.stream().findFirst().get().getJobTitle());
        TestTransaction.end();

    }
}




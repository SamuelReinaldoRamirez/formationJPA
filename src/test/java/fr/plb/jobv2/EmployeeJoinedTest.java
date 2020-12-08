package fr.plb.jobv2;

import fr.plb.jobv2.Jobv2Application;
import fr.plb.jobv2.domain.Address;
import fr.plb.jobv2.domain.Employee;
import fr.plb.jobv2.domain.Person;
import fr.plb.jobv2.domain.PersonId;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;

@SpringBootTest(classes = Jobv2Application.class)
public class EmployeeJoinedTest {

    private final Logger log = LoggerFactory.getLogger(EmployeeJoinedTest.class);

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
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

        entityManager.persist(person);
        entityManager.persist(employee);

        TypedQuery<Employee> employeeQuery = entityManager.createQuery("FROM Employee", Employee.class);
        List<Employee> employeeList = employeeQuery.getResultList();
        log.info("Employee displaying : " + employeeList.stream().findFirst().get().getEmail());

        TypedQuery<Person> personQuery = entityManager.createQuery("FROM Person", Person.class);
        List<Person> personList = personQuery.getResultList();
        log.info("Person displaying : " + personList.stream().findFirst().get().getEmail());
    }
}

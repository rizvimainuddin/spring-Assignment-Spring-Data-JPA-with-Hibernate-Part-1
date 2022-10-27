package com.mainuddin.springdata.employee;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;

import com.mainuddin.springdata.employee.entities.Employee;
import org.hibernate.Session;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.mainuddin.springdata.employee.repos.EmployeeRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeApplicationTests {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	EntityManager entityManager;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testCreate() {
		Employee employee = new Employee();
		employee.setId(5);
		employee.setName("Maaz");
		employee.setAge(22);
		employee.setAddress("Saharanpur");

		repository.save(employee);
	}

	@Test
	public void testRead() {
		Employee employee = repository.findById(2).get();
//		assertNotNull(employee);
//		assertEquals("Iphone", employee.getName());
		System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + employee.getAddress());
	}

	@Test
	public void testUpdate() {
		Employee employee = repository.findById(1).get();
		employee.setAge(20);
		repository.save(employee);

	}

	@Test
	public void testDelete() {
		if (repository.existsById(1)) {
			System.out.println("Deleting a employee");
			repository.deleteById(1);
		}
	}

	@Test
	public void testCount() {
		System.out.println("Total Records===============>>>>>>>>>>>>>>>" + repository.count());

	}

	@Test
	public void testFindByName() {
		List<Employee> employees = repository.findByName("Shimanshu");
		employees.forEach(e -> System.out.println(e.getAge()));

		List<Employee> employees1 = repository.findByName("Sahil");
		employees1.forEach(e -> System.out.println(e.getAge()));
	}

	@Test
	public void testFindByNameAndAge() {
		List<Employee> employees = repository.findByNameAndAge("Maaz", 20);
		employees.forEach(e -> System.out.println(e.getAge()));
	}

	@Test
	public void testFindByAgeGreaterThan() {
		List<Employee> employees = repository.findByAgeGreaterThan(23);
		employees.forEach(e -> System.out.println(e.getName()));
	}

	@Test
	public void testFindByAddressContains() {
		List<Employee> employees = repository.findByAddressContains("Mee");
		employees.forEach(e -> System.out.println(e.getName()));
	}

	@Test
	public void testFindByAgeBetween() {
		List<Employee> employees = repository.findByAgeBetween(28, 32); // Not found
		employees.forEach(e -> System.out.println(e.getName()));
	}

	@Test
	public void testFindByNameLike() {
		List<Employee> employees = repository.findByNameLike("M%");
		employees.forEach(e -> System.out.println(e.getName()));
	}

	@Test
	public void testFindByAddressLike() {
		List<Employee> employees = repository.findByAddressLike("M%");
		employees.forEach(e -> System.out.println(e.getName()));
	}

	@Test
	public void testFindByIdsIn() {
		// Pageable pageable = new PageRequest(0, 2);
		Pageable pageable = PageRequest.of(0, 2);
		List<Employee> employees = repository.findByIdIn(Arrays.asList(1, 2, 3), pageable);
		employees.forEach(p -> System.out.println(p.getName()));
	}

	@Test
	public void testFindAllPaging() {
		Pageable pageable = PageRequest.of(0, 2);
		Iterable<Employee> results = repository.findAll(pageable);
		results.forEach(e -> System.out.println(e.getName()));

	}

	@Test
	public void testFindAllSorting() {
		repository.findAll(Sort.by(new Sort.Order(Direction.DESC, "name"), new Sort.Order(null, "age")))
				.forEach(e -> System.out.println(e.getName()));

		// repository.findAll(Sort.by("name", "price")).forEach(p ->
		// System.out.println(p.getName()));

	}

	@Test
	public void testFindAllPagingAndSorting() {
		Pageable pageable = PageRequest.of(0, 4, Direction.ASC, "age");
		repository.findAll(pageable).forEach(e -> System.out.println(e.getName() + " " + e.getAge()));

	}

//	@Test
//	@Transactional
//	public void testCaching() {
//		Session session = entityManager.unwrap(Session.class);
//		Employee employee = repository.findById(1).get();
//
//		repository.findById(1).get();
//
//		session.evict(employee);
//
//		repository.findById(1).get();
//
//	}

}
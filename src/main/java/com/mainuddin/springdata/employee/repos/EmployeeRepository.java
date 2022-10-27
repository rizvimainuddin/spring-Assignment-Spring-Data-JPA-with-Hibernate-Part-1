package com.mainuddin.springdata.employee.repos;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mainuddin.springdata.employee.entities.Employee;
import java.lang.String;
import java.util.List;
public interface EmployeeRepository extends PagingAndSortingRepository<Employee,Integer> {

    List<Employee> findByName(String name);

    List<Employee> findByNameAndAge(String name, int age);

    List<Employee> findByAgeGreaterThan(int age);

    List<Employee> findByAddressContains(String address);

    List<Employee> findByAgeBetween(int age1, int age2);

    List<Employee> findByAddressLike(String address);
    List<Employee> findByNameLike(String name);


    List<Employee> findByIdIn(List<Integer> ids,Pageable pageable);

}
package com.spring.assignment.employeesystem.dao;

import com.spring.assignment.employeesystem.entity.Employee;
import com.spring.assignment.employeesystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    List<Employee> findByUsername(String username);
    Employee findById(int id);
    List<Employee> findByProject(String project );
    Boolean existsByUsername(String username);


}

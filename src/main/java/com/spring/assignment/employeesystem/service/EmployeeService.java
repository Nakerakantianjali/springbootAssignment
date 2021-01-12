package com.spring.assignment.employeesystem.service;

import com.spring.assignment.employeesystem.entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public List<Employee> findAll();
    public Employee findEmployeeById(int id);
    public Employee save(Employee employee);
    public void deleteById(int id);
    public List<Employee> findByUsername(String firstName);
    public List<Employee> findByProject(int id );
    public List<Employee> findByProjectName(String username );
    public boolean existsByUsername(String username);


}

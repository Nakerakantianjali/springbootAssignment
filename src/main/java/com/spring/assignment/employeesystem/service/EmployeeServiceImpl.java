package com.spring.assignment.employeesystem.service;

import com.spring.assignment.employeesystem.dao.EmployeeRepository;
import com.spring.assignment.employeesystem.dao.UserRepository;
import com.spring.assignment.employeesystem.entity.Employee;
import com.spring.assignment.employeesystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class EmployeeServiceImpl implements  EmployeeService{
    EmployeeRepository employeeRepository;
    UserRepository userRepository;
    @Autowired
    public  EmployeeServiceImpl(EmployeeRepository employeeRepository,UserRepository userRepository){
        this.employeeRepository=employeeRepository;
        this.userRepository=userRepository;

    }

    @Override
    public List<Employee> findAll() {
       return  employeeRepository.findAll();
    }

    @Override
    public Employee findEmployeeById(int id) {
        return employeeRepository.findById(id);
    }
    @Override
    public Employee save(Employee employee) {
        String password="test123";
        User user = new User(employee.getId(),employee.getUsername(), password, employee.getRole());
        user.setUserName(employee.getUsername());
        System.out.println("------->"+user.getUserName());
        try{
        employeeRepository.save(employee);
            System.out.println("------->"+user.getUserName());

        }
        catch (Exception e){
            System.out.println(e);
        }
        try {
            userRepository.save(user);
        }catch (Exception ex){
            System.out.println(ex);

        }
         return employee;
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);

    }

    @Override
    public List<Employee> findByUsername(String username) {

        System.out.println("serviceImpl------>"+username);
        return employeeRepository.findByUsername(username);
    }

    @Override
    public List<Employee> findByProject(int id) {
        System.out.println("going to project");
        //System.out.println(id);
        List<Employee>employeeList=new ArrayList<>();
        System.out.println("going to project");

        Employee employee=employeeRepository.findById(id);
        System.out.println("and geting these "+employeeRepository.findByProject(employee.getProject()));
        return employeeRepository.findByProject(employee.getProject());


    }

    @Override
    public List<Employee> findByProjectName(String username) {
       List< Employee> employee=  employeeRepository.findByUsername(username);
        return employeeRepository.findByProject(employee.get(0).getProject());

    }

    @Override
    public boolean existsByUsername(String username) {
        return employeeRepository.existsByUsername(username);
    }

}

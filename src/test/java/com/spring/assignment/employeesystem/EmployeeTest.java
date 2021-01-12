package com.spring.assignment.employeesystem;

import com.spring.assignment.employeesystem.controller.EmployeeController;
import com.spring.assignment.employeesystem.dao.EmployeeRepository;
import com.spring.assignment.employeesystem.dao.ReviewRepository;
import com.spring.assignment.employeesystem.dao.UserRepository;
import com.spring.assignment.employeesystem.entity.Employee;
import com.spring.assignment.employeesystem.entity.Review;
import com.spring.assignment.employeesystem.entity.User;
import com.spring.assignment.employeesystem.service.*;
import junit.framework.Assert;
import org.assertj.core.internal.bytebuddy.matcher.ElementMatcher;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static junit.framework.TestCase.assertEquals;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeTest {
    @InjectMocks
    EmployeeServiceImpl employeeService;

    @Mock
    EmployeeRepository employeeRepository;
    @InjectMocks
    ReviewServiceImpl reviewService;

    @Mock
    ReviewRepository reviewRepository;
    @InjectMocks
    MyUserServices userServices;
    @Mock
    UserRepository userRepository;
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindUser(){
        // Given
        Employee emp = new Employee();
       // System.out.println(employeeService.findEmployeeById(13));
        when(employeeRepository.findById(13)).thenReturn(emp);

        // When
        Employee result = employeeService.findEmployeeById(13);

        // Then
        // you are expecting service to return whatever returned by repo
        assertThat("result", result, is(sameInstance(emp)));

        // you are expecting repo to be called once with correct param
        verify(employeeRepository).findById(13);
    }

    private void assertThat(String result, Employee result1, ElementMatcher.Junction<Object> objectJunction) {


    }


    /*@Autowired
    private EmployeeService employeeService;
    @MockBean
    private EmployeeRepository employeeRepository;*/
      @Test
    public  void saveEmployee(){
        Employee employee= new Employee(28,"rishi","TeamLead","passport");
        employee=employeeService.save(employee);
       //employee= Mockito.verify(employeeRepository,Mockito.times(1)).save(employee);
    }
    @Test
    public void findAll(){
        List<Employee>employeeList = new ArrayList<>();
        Mockito.when(employeeService.findAll()).thenReturn(employeeList);
        employeeService.findAll();
        Mockito.verify(employeeRepository,Mockito.times(1)).findAll();

      }
    @Test
    public void findById() {

        //EmployeeRepository employeeRepository = employeeService.;
        Employee employee = employeeRepository.findById(13);       // employeeService.save(employee);
            System.out.println(employeeService.findEmployeeById(13));
   //    Mockito.when(employeeService.findEmployeeById(13)).thenReturn(employee);
        assertEquals(employeeService.findEmployeeById(13), employee);

    }
    @Test
    public  void existsByUsername(){
        Boolean flag=employeeRepository.existsByUsername("naren");
        System.out.println(employeeRepository.findAll());

        Mockito.when(employeeRepository.existsByUsername("naren")).thenReturn(flag);
        assertEquals(Optional.of(employeeService.existsByUsername("naren")), Optional.of(flag));

    }


    @Test
    public void findByProject(){
       // Employee employee= employeeRepository.findById(13);
        try {
            employeeService.findByProjectName("Megha");
        }
        catch (Exception ex){}
        List<Employee>employeeList = new ArrayList<>();
        when(employeeRepository.findByProject("passport")).thenReturn(employeeList);
        //verify(employeeRepository).findByProject("passport");
        // assertEquals(Optional.of(employeeService.findByProjectName("passport")), Optional.of(employeeList));

    }
    @Test
    public  void addReview(){
        Date date= new Date();
        Review review= new Review("shalini",date,"Good");
        reviewService.addReview(review);
        Mockito.verify(reviewRepository,Mockito.times(1)).save(review);
    }
    @Test
    public  void findByUserName(){
        List<Review >reviewList= reviewService.findByUserName("sravani");       // employeeService.save(employee);
        Mockito.when(reviewService.findByUserName("sravani")).thenReturn(reviewList);
        assertEquals(reviewService.findByUserName("sravani"), reviewList);

    }
  /*  @Test
    public void listOfEmpWithSameProject(){
    E
          //List<Employee> employeeList=  employeeRepository.findByProject("passport");
       //System.out.println(employeeList);
        Mockito.when(employeeService.findByProject(9)).thenReturn(employeeList);
        assertEquals(employeeService.findByProject(9), employeeList);

    }*/
    @Test
    public void delete() {
     //   Student student = new Student(3, "1604-16-733-030", "Anand",
       //         "Shabad", "adil@gmail.com", "Semester_1", "Due");
        employeeService.deleteById(9);
        Mockito.verify(employeeRepository, Mockito.times(1)).deleteById(9);

    }


    @Test
    public void deleteReview() {
        //   Student student = new Student(3, "1604-16-733-030", "Anand",
        //         "Shabad", "adil@gmail.com", "Semester_1", "Due");
        reviewService.deleteByName("Madhu");
        List<Review>reviewList= reviewRepository.findByUsername("madhu");
        Mockito.verify(reviewRepository, Mockito.times(1)).deleteAll(reviewList);

    }
    @Test
    public  void findByUserName1(){
        List<Employee>emplist=employeeService.findByUsername("sravani");
        Mockito.verify(employeeRepository,Mockito.times(1)).findByUsername("sravani");
    }
    public void findByProject(int id) {
        List<Employee>emplist=employeeService.findByProject(9);
        Mockito.verify(employeeRepository,Mockito.times(1)).findById(9);

    }
    @Test
    public void deleteUser() {
        //   Student student = new Student(3, "1604-16-733-030", "Anand",
        //         "Shabad", "adil@gmail.com", "Semester_1", "Due");
        User user =userRepository.findByUserName("naren");

        userServices.delete("naren");

        Mockito.verify(userRepository, Mockito.times(1)).delete(user);

    }


}

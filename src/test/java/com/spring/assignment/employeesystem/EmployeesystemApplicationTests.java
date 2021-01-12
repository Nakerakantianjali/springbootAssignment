package com.spring.assignment.employeesystem;

import com.spring.assignment.employeesystem.dao.EmployeeRepository;
import com.spring.assignment.employeesystem.entity.Employee;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ContextConfiguration()
@SpringBootTest
@WebMvcTest
class EmployeesystemApplicationTests {

	@Test
	public void contextLoads() {

	}


}

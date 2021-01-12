package com.spring.assignment.employeesystem.annotation;

import com.spring.assignment.employeesystem.dao.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Service
public class UserUniqueValidator implements ConstraintValidator<Unique,String> {

   private EmployeeRepository employeeRepository;
    public UserUniqueValidator(){}

    @Autowired
    public UserUniqueValidator(EmployeeRepository employeeRepository){
        this.employeeRepository=employeeRepository;
    }


    @Override
    public void initialize(Unique constraintAnnotation) {
        //employeeService= ServiceUtils.getEmployeeService();
        constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
       //employeeService= ServiceUtils.getEmployeeService();
        //List<Employee>emplist=employeeService.findByEmail(email);
       //List<Employee>emplist;

        if(employeeRepository!=null) {
           boolean flag = employeeRepository.existsByUsername(username);

            //if(emplist.size()>0){
                //  System.out.println("------->constraint validator"+employeeService.findByEmail(email));
              //  return false;
            ///}
            if(flag)
                return false;

          //  System.out.println("------->constraint validator" + flag);
            return  true;
        }


     return true;
    }
}

package com.spring.assignment.employeesystem.controller;

import com.spring.assignment.employeesystem.config.Userdetails;
import com.spring.assignment.employeesystem.entity.Employee;
import com.spring.assignment.employeesystem.entity.Review;
import com.spring.assignment.employeesystem.entity.User;
import com.spring.assignment.employeesystem.service.EmployeeService;
import com.spring.assignment.employeesystem.service.MyUserServices;
import com.spring.assignment.employeesystem.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    private List<Employee> employeeList;
    EmployeeService employeeService;
    ReviewService reviewService;
    MyUserServices myUserServices;

    @Autowired
    public EmployeeController(EmployeeService employeeService, ReviewService reviewService,MyUserServices myUserServices){
        this.employeeService=employeeService;
        this.reviewService=reviewService;
        this.myUserServices=myUserServices;
    }
    @RequestMapping("/home")
    public String home(){
        return "homepage";
    }
    @GetMapping("/profile")
    public String profile(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("---->get UserRole"+authentication.getName()+"hey ");
        String name=authentication.getName();
         employeeList = employeeService.findByUsername(name);
        System.out.println(employeeList);

        Employee employee1=employeeList.get(0);
        model.addAttribute("employee",employee1);

        return "profile";
    }

    @RequestMapping("/list")
    public  String hello(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("---->get UserRole"+authentication.getAuthorities()+"hey ");
        Userdetails userdetails= (Userdetails) authentication.getPrincipal();
        if(userdetails.getRole().equals("TeamLead")){
            try {
                System.out.println("***----->Logged in as TeamLead " +userdetails.getUId()+" ");
                    int id=userdetails.getUId();
                employeeList = employeeService.findByProjectName(userdetails.getUsername());
                System.out.println("***----->Logged in as TeamLead" + employeeService.findByProjectName(userdetails.getUsername()).size());
                model.addAttribute("employeeList", employeeList);


                return "list-employees";
            }
            catch (Exception e){}
        }
                employeeList = employeeService.findAll();
                System.out.println("***----->" + employeeService.findAll());
                model.addAttribute("employeeList", employeeList);

        return "list-employees";
    }
    @GetMapping("/showFormForAdd")
    public String showFormforAdd(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "employee-form";
    }
    @GetMapping("/showFormForUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") int empid,Model model){
        Employee employee=employeeService.findEmployeeById(empid);
        model.addAttribute("employee",employee);
        return "employee--update-form";
    }
    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult,Model model){
        System.out.println("----->checking fill all the fileds"+ bindingResult.hasErrors());

        if(!bindingResult.hasErrors()){
           // model.addAttribute("employee",employee);
                      employeeService.save(employee);

            return "redirect:/employee/list";

        }

        System.out.println("----->pelease fill all the fileds");
        model.addAttribute("employee",employee);

        return "employee-form";


    }
    @PostMapping("/save1")
    public String save1(@Valid @ModelAttribute("employee") Employee employee, BindingResult bindingResult,Model model){
        System.out.println("----->checking fill all the fileds"+bindingResult.hasErrors());

        if(!bindingResult.hasErrors()){
            // model.addAttribute("employee",employee);
            Employee employee1=employeeService.findEmployeeById(employee.getId());
            employee.setUsername(employee1.getUsername());
            employeeService.save(employee);

            return "redirect:/employee/list";

        }


        System.out.println("----->pelease fill all the fileds");
        model.addAttribute("employee",employee);

        return "employee--update-form";


    }
    @GetMapping("/delete")
    public String showFormForDelete(@RequestParam("employeeId") int empid){
        Employee employee=employeeService.findEmployeeById(empid);
        System.out.println("employee name-------> "+employee.getUsername());
        reviewService.deleteByName(employee.getUsername());
        System.out.println("employee name1-------> "+employee.getUsername());
        String str=employee.getUsername();
        myUserServices.delete(employee.getUsername());
            System.out.println("employee name2-------> "+employee.getUsername());

      //  System.out.println("employee name2-------> "+employee.getUsername());

        employeeService.deleteById(empid);
        return "redirect:/employee/list";
    }
    @GetMapping("/search")
    public String search(@RequestParam("username")String username, Model model){
        employeeList=employeeService.findByUsername(username);
        System.out.println("***----->"+employeeService.findAll());
        if(employeeList==null){

        }
        model.addAttribute("employeeList",employeeList);
        return "list-employees";
    }

    @GetMapping("/project")
    public String findByProject(@RequestParam("employeeId")int id, Model model, Principal principal){
       // Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
         //   System.out.println("---->get UserRole"+authentication.getAuthorities()+"hey ");
        employeeList=employeeService.findByProject(id);
        System.out.println("***----->"+employeeService.findAll());
        if(employeeList==null){

        }
        model.addAttribute("employeeList",employeeList);
        return "list-employees2";
    }
    @RequestMapping("/addReview")
    public String addReview(@RequestParam("employeeId")int id,Model model){
        Date date= new Date();
              Employee employee=employeeService.findEmployeeById(id);
        model.addAttribute("employee",employee);
        return "details";
    }
    @RequestMapping("/saveReview")
    public String saveReview(@RequestParam("review")String reviews,@ModelAttribute("employee")Employee employee,Model model){
        Date date= new Date();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Userdetails userdetails = (Userdetails) authentication.getPrincipal();
        System.out.println("-->user details"+employee.getUsername());
        String Username=userdetails.getUsername();
        Review review = new Review(employee.getUsername(),date,reviews);
        //UserDetails userdetails1=  myUserServices.loadUserByUsername(employee.getUsername());
        //String user= userdetails1.getUsername();
        User user=myUserServices.findByUser(employee.getUsername());
        System.out.println("user _____"+user.getRole() );
        if(!user.getRole().equals("TeamLead")) {
            reviewService.addReview(review);
        }

        model.addAttribute("employee",employee);
          return "details";
    }
    @RequestMapping("/checkReview")
    public String checkReview(@ModelAttribute("employee")Employee employee, Model model){
        List<Review>reviewList=reviewService.findByUserName(employee.getUsername());

        model.addAttribute("reviewList",reviewList);
        return "review-list.html";
    }

}

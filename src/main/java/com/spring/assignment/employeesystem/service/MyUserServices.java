package com.spring.assignment.employeesystem.service;

import com.spring.assignment.employeesystem.config.Userdetails;
import com.spring.assignment.employeesystem.dao.UserRepository;
import com.spring.assignment.employeesystem.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserServices implements UserDetailsService {
    UserRepository userRepository;
    @Autowired
    public MyUserServices(UserRepository userRepository){
        System.out.println("----->hey you are there");

        this.userRepository=userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
       User user =userRepository.findByUserName(userName);
       System.out.println("----->hey you are there"+user.getUserName());

       if(user==null){
           throw  new UsernameNotFoundException("user not found");
       }
        return new Userdetails(user);
    }
    public  void delete( String username)throws UsernameNotFoundException{

        User user =userRepository.findByUserName(username);
        if(user != null) {
            userRepository.delete(user);
        }
    }
    public  User findByUser(String name){
        return userRepository.findByUserName(name);
    }
    /*public boolean find(String username)throws UsernameNotFoundException{
        User user =userRepository.findByUserName(username);

    }*/

}



package com.spring.assignment.employeesystem.entity;

import com.spring.assignment.employeesystem.annotation.Unique;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int id;
    @Size(min = 1, message = "name should not be empty")
    @Column(name = "username")
    @Unique(message = "user alredy exists in db")
    String username;
    @NotNull
    @Size(min = 1, message = "role should not be empty")
    @Column(name = "role")
    String role;
    @NotNull
    @Size(min = 1, message = "project should not be empty")
    @Column(name = "projectname")
    String project;

    public Employee() {

    }

    public Employee(String username, String role, String project) {
        this.username = username;
        this.role = role;
        this.project = project;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public Employee(int id, String username, String role, String project) {
        this.id = id;
        this.username = username;
        this.role = role;
        this.project = project;
    }

}

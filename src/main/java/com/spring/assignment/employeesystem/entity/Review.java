package com.spring.assignment.employeesystem.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
@Entity
@Table(name = "review")
public class Review {


    @Column(name = "id")
    int id;
    @Column(name = "username")
    @NotNull
    @Size(min = 1, message = "please don't leave it empty")
    String username;
    @Column(name = "date")

    Date date;
    @NotNull
    @Size(min = 1, message = "please don't leave it empty")
    @Column(name = "review")
    String review;
    public Review(){}
    public Review(String username, Date date, String review) {
        //this.id = id;
        this.username=username;
        this.date = date;
        this.review = review;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }



}

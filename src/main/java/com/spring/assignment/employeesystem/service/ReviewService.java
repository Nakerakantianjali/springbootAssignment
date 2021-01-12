package com.spring.assignment.employeesystem.service;

import com.spring.assignment.employeesystem.entity.Review;

import java.util.List;

public interface ReviewService {
    public  void addReview(Review review);
    public List<Review> findByUserName(String username);
    public void deleteByName(String username);
}

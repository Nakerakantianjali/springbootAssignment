package com.spring.assignment.employeesystem.dao;

import com.spring.assignment.employeesystem.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> getReviewById(int id);
    List<Review>findByUsername(String username);
}

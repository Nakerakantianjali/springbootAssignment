package com.spring.assignment.employeesystem.service;

import com.spring.assignment.employeesystem.dao.ReviewRepository;
import com.spring.assignment.employeesystem.entity.Employee;
import com.spring.assignment.employeesystem.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    ReviewRepository reviewRepository;
    @Autowired
    ReviewServiceImpl(ReviewRepository reviewRepository){
       this.reviewRepository=reviewRepository;
    }
    @Override
    public void addReview(Review review) {
        reviewRepository.save(review);
    }

    @Override
    public List<Review> findByUserName(String username) {

        return reviewRepository.findByUsername(username);
    }

    @Override
    public void deleteByName(String username) {
       List<Review>reviewList= reviewRepository.findByUsername(username);
        reviewRepository.deleteAll(reviewList);
    }

}

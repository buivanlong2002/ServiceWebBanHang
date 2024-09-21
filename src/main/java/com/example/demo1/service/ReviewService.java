package com.example.demo1.service;

import com.example.demo1.dto.request.AdminCreationRequest;
import com.example.demo1.dto.request.AdminUpdateRequest;
import com.example.demo1.dto.request.ReviewCreationRequest;
import com.example.demo1.dto.request.ReviewUpdateRequest;
import com.example.demo1.exception.AppException;
import com.example.demo1.exception.ErrorCode;
import com.example.demo1.mapper.ReviewMapper;
import com.example.demo1.models.Admin;
import com.example.demo1.models.Review;
import com.example.demo1.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ReviewMapper reviewMapper;
    public List<Review> CreateReview(ReviewCreationRequest request) {
        Review review = reviewMapper.toReview(request);
        return Collections.singletonList(reviewRepository.save(review));

    }
    // lấy tất cả danh sách
    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }
    // lấy theo id
    public Review getReviewById(Integer id) {
        return reviewRepository.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }
    /// cập nhập admin
    public Review updateReview(Integer id , ReviewUpdateRequest request) {
        Review review = getReviewById(id);
        reviewMapper.updateReview(review,request);
//
        return reviewRepository.save(review);
    }
    // xóa admin theo id
    public void deleteReview(Integer id) {
        reviewRepository.deleteById(id);
    }
}

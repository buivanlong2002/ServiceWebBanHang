package com.example.demo1.controller;
import com.example.demo1.dto.request.AdminCreationRequest;
import com.example.demo1.dto.request.AdminUpdateRequest;
import com.example.demo1.dto.request.ReviewCreationRequest;
import com.example.demo1.dto.request.ReviewUpdateRequest;
import com.example.demo1.dto.response.ApiResponse;
import com.example.demo1.models.Admin;
import com.example.demo1.models.Review;
import com.example.demo1.service.AdminService;
import com.example.demo1.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/review")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;
    @PostMapping
    ApiResponse<List<Review>> createReview(@RequestBody ReviewCreationRequest request) {
        ApiResponse<List<Review>> apiResponse = new ApiResponse<>();
        apiResponse.setData(reviewService.CreateReview(request));
        return apiResponse;
    }
    @GetMapping
    ApiResponse<List<Review>> getAllReview() {
        ApiResponse<List<Review>> response = new ApiResponse<>();
        response.setData(reviewService.getAllReview());
        return response;
    }
    @GetMapping("/{id}")
    ApiResponse<Review>  getReviewById(@PathVariable Integer id) {
        ApiResponse<Review> response = new ApiResponse<>();
        response.setData(reviewService.getReviewById(id));

        return response;
    }
    @PutMapping("/{id}")
    ApiResponse<Review> UpdateReview(@PathVariable Integer id, @RequestBody ReviewUpdateRequest request) {
        ApiResponse<Review> apiResponse = new ApiResponse<>();
        Review review = reviewService.updateReview(id, request);
        if(review != null){
            apiResponse.setData(review);
            apiResponse.setStatus("00");
            apiResponse.setMessage("Update thành công");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("Update thất bại");
        }
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    ApiResponse<Review> deleteReview(@PathVariable Integer id ) {
        ApiResponse<Review> apiResponse = new ApiResponse<>();
        reviewService.deleteReview(id);
        apiResponse.setMessage("Deleted Admin");
        return apiResponse ;
//        return apiResponse;
    }
}

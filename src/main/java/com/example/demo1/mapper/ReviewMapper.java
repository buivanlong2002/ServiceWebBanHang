package com.example.demo1.mapper;

import com.example.demo1.dto.request.ReviewCreationRequest;
import com.example.demo1.dto.request.ReviewUpdateRequest;
import com.example.demo1.models.Review;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ReviewMapper {
    Review toReview(ReviewCreationRequest request);
    void updateReview(@MappingTarget Review review, ReviewUpdateRequest request);
}

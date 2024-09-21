package com.example.demo1.mapper;

import com.example.demo1.dto.request.CategoryCreationRequest;
import com.example.demo1.dto.request.CategoryUpdateRequest;
import com.example.demo1.models.Catalog;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    Catalog toCategory(CategoryCreationRequest request);
    void updateCategory(@MappingTarget Catalog catalog , CategoryUpdateRequest request);
}

package com.example.demo1.mapper;

import com.example.demo1.dto.request.ProductCreationRequest;
import com.example.demo1.dto.request.ProductUpdateRequest;
import com.example.demo1.models.Product;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProductMapper1 {
    Product toProduct(ProductCreationRequest request);
    void updateProduct(@MappingTarget Product product, ProductUpdateRequest request);

}

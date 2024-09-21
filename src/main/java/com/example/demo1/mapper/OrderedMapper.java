package com.example.demo1.mapper;

import com.example.demo1.dto.request.OrderedCreationRequest;
import com.example.demo1.dto.request.OrderedUpdateRequest;
import com.example.demo1.models.Ordered;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface OrderedMapper {
    Ordered toOrdered(OrderedCreationRequest request);
    void updateOrdered(@MappingTarget Ordered ordered, OrderedUpdateRequest request);
}

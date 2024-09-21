package com.example.demo1.mapper;

import com.example.demo1.dto.request.AdminCreationRequest;
import com.example.demo1.dto.request.AdminUpdateRequest;
import com.example.demo1.models.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AdminMapper {
    Admin toAdmin(AdminCreationRequest request);
    void updateAdmin(@MappingTarget Admin admin, AdminUpdateRequest request);
}

package com.example.demo1.mapper;

import com.example.demo1.dto.request.UserCreationRequest;
import com.example.demo1.dto.request.UserUpdateRequest;
import com.example.demo1.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}

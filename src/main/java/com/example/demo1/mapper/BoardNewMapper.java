package com.example.demo1.mapper;

import com.example.demo1.dto.request.AdminCreationRequest;
import com.example.demo1.dto.request.AdminUpdateRequest;
import com.example.demo1.dto.request.BoardNewCreationRequest;
import com.example.demo1.dto.request.BoardNewUpdateRequest;
import com.example.demo1.models.Admin;
import com.example.demo1.models.Boardnew;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface BoardNewMapper {
    Boardnew toBoardNew(BoardNewCreationRequest request);
    void updateBoardNew(@MappingTarget Boardnew boardnew, BoardNewUpdateRequest request);
}

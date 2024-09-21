package com.example.demo1.mapper;

import com.example.demo1.dto.request.UserCreationRequest;
import com.example.demo1.dto.request.UserUpdateRequest;
import com.example.demo1.dto.request.VoucherCreationRequest;
import com.example.demo1.dto.request.VoucherUpdateRequest;
import com.example.demo1.models.User;
import com.example.demo1.models.Vouchers;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
@Mapper(componentModel = "spring")
public interface VoucherMapper {
    Vouchers toVouchers(VoucherCreationRequest request);
    void updateVoucher(@MappingTarget Vouchers vouchers, VoucherUpdateRequest request);
}

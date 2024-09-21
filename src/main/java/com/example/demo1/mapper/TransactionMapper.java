package com.example.demo1.mapper;

import com.example.demo1.dto.request.TransactionCreationRequest;
import com.example.demo1.dto.request.TransactionUpdateRequest;
import com.example.demo1.models.Transactions;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
    Transactions toTransactions(TransactionCreationRequest request);
    void updateTransaction(@MappingTarget Transactions transactions, TransactionUpdateRequest request);
}

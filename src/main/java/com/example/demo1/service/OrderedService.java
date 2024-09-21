package com.example.demo1.service;

import com.example.demo1.dto.request.CategoryCreationRequest;
import com.example.demo1.dto.request.CategoryUpdateRequest;
import com.example.demo1.dto.request.OrderedCreationRequest;
import com.example.demo1.dto.request.OrderedUpdateRequest;
import com.example.demo1.mapper.CategoryMapper;
import com.example.demo1.mapper.OrderedMapper;
import com.example.demo1.models.Catalog;
import com.example.demo1.models.Ordered;
import com.example.demo1.repositories.CatalogRepository;
import com.example.demo1.repositories.OrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class OrderedService {
    @Autowired
    private OrderedRepository orderedRepository;
    @Autowired
    private OrderedMapper orderedMapper;
    public List<Ordered> CreateOrdered(OrderedCreationRequest request) {
//
        Ordered ordered = orderedMapper.toOrdered(request);
        return Collections.singletonList(orderedRepository.save(ordered));

    }
    // lấy tất cả danh sách
    public List<Ordered> getAllOrdered() {
        return orderedRepository.findAll();
    }
    // lấy theo id
    public Ordered getOrderedById(Integer id) {
        return orderedRepository.findById(id).orElseThrow(()->new RuntimeException(" not found"));
    }
    /// cập nhập
    public Ordered updateOrdered(Integer id , OrderedUpdateRequest request) {
        Ordered ordered = getOrderedById(id);
        orderedMapper.updateOrdered(ordered,request);
//
        return orderedRepository.save(ordered);
    }
    // xóa admin theo id
    public void deleteOrdered(Integer id) {
        orderedRepository.deleteById(id);
    }
}

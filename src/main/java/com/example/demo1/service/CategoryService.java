package com.example.demo1.service;

import com.example.demo1.dto.request.BoardNewCreationRequest;
import com.example.demo1.dto.request.BoardNewUpdateRequest;
import com.example.demo1.dto.request.CategoryCreationRequest;
import com.example.demo1.dto.request.CategoryUpdateRequest;
import com.example.demo1.mapper.CategoryMapper;
import com.example.demo1.models.Boardnew;
import com.example.demo1.models.Catalog;
import com.example.demo1.repositories.CatalogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CatalogRepository catalogRepository;
    @Autowired
    private CategoryMapper  categoryMapper;
    public List<Catalog> CreateCatalog(CategoryCreationRequest request) {
//
        Catalog catalog = categoryMapper.toCategory(request);
        return Collections.singletonList(catalogRepository.save(catalog));

    }
    // lấy tất cả danh sách
    public List<Catalog> getAllCatalog() {
        return catalogRepository.findAll();
    }
    // lấy theo id
    public Catalog getCatalogById(Integer id) {
        return catalogRepository.findById(id).orElseThrow(()->new RuntimeException(" not found"));
    }
    /// cập nhập
    public Catalog updateCatalog(Integer id , CategoryUpdateRequest request) {
        Catalog catalog = getCatalogById(id);
        categoryMapper.updateCategory(catalog,request);
//
        return catalogRepository.save(catalog);
    }
    // xóa admin theo id
    public void deleteCatalog(Integer id) {
        catalogRepository.deleteById(id);
    }

}


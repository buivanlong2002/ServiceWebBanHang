package com.example.demo1.controller;

import com.example.demo1.dto.request.BoardNewCreationRequest;
import com.example.demo1.dto.request.BoardNewUpdateRequest;
import com.example.demo1.dto.request.CategoryCreationRequest;
import com.example.demo1.dto.request.CategoryUpdateRequest;
import com.example.demo1.dto.response.ApiResponse;
import com.example.demo1.models.Admin;
import com.example.demo1.models.Boardnew;
import com.example.demo1.models.Catalog;
import com.example.demo1.service.BoardNewService;
import com.example.demo1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping
    ApiResponse<List<Catalog>> createCategory(@RequestBody CategoryCreationRequest request) {
        ApiResponse<List<Catalog>> apiResponse = new ApiResponse<>();
        List<Catalog> catalogs = categoryService.CreateCatalog(request);
        if(catalogs != null){
            apiResponse.setData(catalogs);
            apiResponse.setStatus("00");
            apiResponse.setMessage(" thành công");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("thất bại");
        }
        return apiResponse;
    }
    @GetMapping
    ApiResponse<List<Catalog>> getAllCategory() {
        ApiResponse<List<Catalog>> response = new ApiResponse<>();
        List<Catalog> list = categoryService.getAllCatalog();
        if(list != null){
            response.setData(list);
            response.setStatus("00");
            response.setMessage("Tất cả danh sách");
        }else{
            response.setData(null);
            response.setStatus("01");
            response.setMessage("không có ");
        }
        return response;
    }
    @GetMapping("/{id}")
    ApiResponse<Catalog>  getCategoryById(@PathVariable Integer id) {

        ApiResponse<Catalog> apiResponse = new ApiResponse<>();
        Catalog catalog = categoryService.getCatalogById(id);
        if(catalog != null){
            apiResponse.setData(catalog);
            apiResponse.setStatus("00");
            apiResponse.setMessage(" thành công");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("không có người dùng");
        }
        return apiResponse;
    }

    @PutMapping("/{id}")
    ApiResponse<Catalog> UpdateCategory(@PathVariable Integer id, @RequestBody CategoryUpdateRequest request) {
        ApiResponse<Catalog> apiResponse = new ApiResponse<>();
        Catalog catalog = categoryService.updateCatalog(id, request);
        if(catalog != null){
            apiResponse.setData(catalog);
            apiResponse.setStatus("00");
            apiResponse.setMessage("Update thành công");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("Update thất bại");
        }
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    ApiResponse<Catalog> deleteCategory(@PathVariable Integer id ) {
        ApiResponse<Catalog> apiResponse = new ApiResponse<>();
        categoryService.deleteCatalog(id);
        apiResponse.setMessage("Deleted ");
        return apiResponse ;
//        return apiResponse;
    }
}

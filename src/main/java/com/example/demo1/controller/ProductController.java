package com.example.demo1.controller;

import com.example.demo1.dto.request.ProductCreationRequest;
import com.example.demo1.dto.request.ProductUpdateRequest;
import com.example.demo1.dto.response.ApiResponse;
import com.example.demo1.models.Product;
import com.example.demo1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;
    @GetMapping
    ApiResponse<List<Product>> findAll() {
        ApiResponse<List<Product>> apiResponse = new ApiResponse<>();
        List<Product> products = productService.findAll();
        apiResponse.setData(products);
        return apiResponse;
    }
    @PostMapping
    ApiResponse<Product> createProduct(@RequestBody ProductCreationRequest request) {
        ApiResponse<Product> apiResponse = new ApiResponse<>();
        apiResponse.setData(productService.CreateProduct(request));
        return apiResponse;
    }
    @GetMapping("/id/{id}")
    ApiResponse<Product> getProductById(@PathVariable Integer id) {
        ApiResponse<Product> apiResponse = new ApiResponse<>();
        Product product = productService.getProductById(id);
        if(product != null) {
            apiResponse.setData(product);
            apiResponse.setStatus("00");
            apiResponse.setMessage("thành công");
        }else {
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("thất bại");
        }
        return apiResponse;
    }
    @GetMapping("/name")
    ApiResponse<List<Product>> searchProduct(@RequestParam String name ) {
        ApiResponse<List<Product> > apiResponse = new ApiResponse<>();
        List<Product>  products = productService.SearchByName(name);
        if(products != null) {
            apiResponse.setData(products);
            apiResponse.setStatus("00");
            apiResponse.setMessage("thành công");
        }else {
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("thất bại");
        }
        return apiResponse;
    }
    @PutMapping("/{id}")
    ApiResponse<Product> UpdateProduct(@PathVariable Integer id, @RequestBody ProductUpdateRequest request) {
        ApiResponse<Product> apiResponse = new ApiResponse<>();
        Product product = productService.updateProduct(id,request);
        if(product != null) {
            apiResponse.setData(product);
            apiResponse.setStatus("00");
            apiResponse.setMessage("thành công");
        }else {
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("thất bại");
        }
        return apiResponse;
    }
    @DeleteMapping("/{id}")
    ApiResponse<String> deteleProduct(@PathVariable Integer id ) {
        productService.deleteProduct(id);
        return ApiResponse.<String>builder().data("product has been deleted").build();
    }
    @GetMapping("/list/{id}")
    public ApiResponse<List<Product>> getProductsById(@PathVariable Integer id) {

        ApiResponse<List<Product>> apiResponse = new ApiResponse<>();
        Optional<Product> products = productService.getProductsById(id);
        if(products != null) {
            apiResponse.setData(Collections.singletonList(products.get( )));
            apiResponse.setStatus("00");
            apiResponse.setMessage("thành công");
        }else {
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("thất bại");
        }
        return apiResponse;
    }
    @GetMapping("/catalogId")
    public ApiResponse<List<Product> > getProductsByCatalogId(@RequestParam String catalogId) {

        ApiResponse<List<Product> > apiResponse = new ApiResponse<>();
        List<Product>  products = productService.getProductsByCatalogId(catalogId);
        if(products != null) {
            apiResponse.setData(products);
            apiResponse.setStatus("00");
            apiResponse.setMessage("thành công");
        }else {
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("thất bại");
        }
        return apiResponse;
    }
}

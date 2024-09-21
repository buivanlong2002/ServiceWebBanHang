package com.example.demo1.service;

import com.example.demo1.dto.request.ProductCreationRequest;
import com.example.demo1.dto.request.ProductUpdateRequest;
import com.example.demo1.exception.AppException;
import com.example.demo1.exception.ErrorCode;
import com.example.demo1.mapper.ProductMapper1;
import com.example.demo1.models.Product;
import com.example.demo1.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper1 productMapper1;
// lấy tất cả danh sách product
    public List<Product> findAll() {
        return productRepository.findAll();
    }
// tạo product
    public Product CreateProduct(ProductCreationRequest request) {
        if(productRepository.existsByName(request.getName()))
            throw new AppException(ErrorCode.ADMIN_EXISTED);
        Product product = productMapper1.toProduct(request);

        // ma hoa password user
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        admin.setPassword(passwordEncoder.encode(request.getPassword()));



        return productRepository.save(product);


    }
// lấy product theo id
    public Product getProductById(Integer id) {

        return productRepository.findById(id).orElseThrow();
    }
// lấy product theo tên
    public  List<Product> SearchByName(String name) {

        return productRepository.findByNameContaining(name);
    }
//   cập nhập product
    public Product updateProduct(Integer id , ProductUpdateRequest request) {
        Product product = getProductById(id);
        productMapper1.updateProduct(product, request);
//
        return productRepository.save(product);
    }
    /// xóa product theo id
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);

}
// lấy danah sách theo id product
public Optional<Product> getProductsById(Integer id) {
        return productRepository.findById(id);
}

///// lấy danh sách catalogId
    public List<Product> getProductsByCatalogId(String catalogId) {
        return productRepository.findByCatalogId(catalogId);
    }
}



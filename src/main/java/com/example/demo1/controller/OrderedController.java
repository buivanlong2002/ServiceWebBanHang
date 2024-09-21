package com.example.demo1.controller;

import com.example.demo1.dto.request.AdminCreationRequest;
import com.example.demo1.dto.request.AdminUpdateRequest;
import com.example.demo1.dto.request.OrderedCreationRequest;
import com.example.demo1.dto.request.OrderedUpdateRequest;
import com.example.demo1.dto.response.ApiResponse;
import com.example.demo1.models.Admin;
import com.example.demo1.models.Ordered;
import com.example.demo1.service.AdminService;
import com.example.demo1.service.OrderedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/ordered")
public class OrderedController {
    @Autowired
    private OrderedService orderedService;
    @PostMapping
    ApiResponse<List<Ordered>> createOrdered(@RequestBody OrderedCreationRequest request) {
        ApiResponse<List<Ordered>> apiResponse = new ApiResponse<>();
        apiResponse.setData(orderedService.CreateOrdered(request));
        return apiResponse;
    }
    @GetMapping
    ApiResponse<List<Ordered>> getAllOrdered() {
        ApiResponse<List<Ordered>> response = new ApiResponse<>();
        response.setData(orderedService.getAllOrdered());
        return response;
    }
    @GetMapping("/{id}")
    ApiResponse<Ordered>  getOrderedById(@PathVariable Integer id) {
        ApiResponse<Ordered> response = new ApiResponse<>();
        response.setData(orderedService.getOrderedById(id));

        return response;
    }
    @PutMapping("/{id}")
    ApiResponse<Ordered> UpdateOrdered(@PathVariable Integer id, @RequestBody OrderedUpdateRequest request) {
        ApiResponse<Ordered> apiResponse = new ApiResponse<>();
        Ordered ordered = orderedService.updateOrdered(id, request);
        if(ordered != null){
            apiResponse.setData(ordered);
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
    ApiResponse<Ordered> deleteOrdered(@PathVariable Integer id ) {
        ApiResponse<Ordered> apiResponse = new ApiResponse<>();
        orderedService.deleteOrdered(id);
        apiResponse.setMessage("Deleted Admin");
        return apiResponse ;
//        return apiResponse;
    }


}

package com.example.demo1.controller;

import com.example.demo1.dto.response.ApiResponse;
import com.example.demo1.models.Review;
import com.example.demo1.models.Shipper;
import com.example.demo1.service.ShipperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/shipper")
public class ShipperController {
    @Autowired
    private ShipperService shipperService;
    @GetMapping
    ApiResponse<List<Shipper>> getAllReview() {
        ApiResponse<List<Shipper>> response = new ApiResponse<>();
        response.setData(shipperService.findAll());
        return response;
    }
}

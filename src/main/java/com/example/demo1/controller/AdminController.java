package com.example.demo1.controller;

import com.example.demo1.dto.request.AdminCreationRequest;
import com.example.demo1.dto.request.AdminUpdateRequest;
import com.example.demo1.dto.response.ApiResponse;
import com.example.demo1.models.Admin;
import com.example.demo1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @PostMapping
    ApiResponse<List<Admin>> createAdmin(@RequestBody AdminCreationRequest request) {
        ApiResponse<List<Admin>> apiResponse = new ApiResponse<>();
       apiResponse.setData(adminService.CreateAdmin(request));
        return apiResponse;
    }
    @GetMapping
    ApiResponse<List<Admin>> getAllAdmins() {
        ApiResponse<List<Admin>> response = new ApiResponse<>();
        response.setData(adminService.getAllAdmin());
        return response;
    }
    @GetMapping("/{id}")
    ApiResponse<Admin> getAdminById(@PathVariable Integer id) {
        ApiResponse<Admin> apiResponse = new ApiResponse<>();
        Admin admin = adminService.getAdminById(id);
        if(admin != null){
            apiResponse.setData(admin);
            apiResponse.setStatus("00");
            apiResponse.setMessage("nguoi dung co ID :");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage(" thất bại");
        }
        return apiResponse;


    }
    @PutMapping("/{id}")
    ApiResponse<Admin> UpdateAdmin(@PathVariable Integer id, @RequestBody AdminUpdateRequest request) {
        ApiResponse<Admin> apiResponse = new ApiResponse<>();
        Admin admin = adminService.updateAdmin(id, request);
        if(admin != null){
            apiResponse.setData(admin);
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
    ApiResponse<Admin> deleteAdmin(@PathVariable Integer id ) {
        ApiResponse<Admin> apiResponse = new ApiResponse<>();
        adminService.deleteAdmin(id);
        apiResponse.setMessage("Deleted Admin");
        return apiResponse ;
//        return apiResponse;
    }
    @GetMapping("/login")
    ApiResponse<Admin> loginAdmin(@RequestParam String username) {
        ApiResponse<Admin> apiResponse = new ApiResponse<>();
        Admin admin = adminService.getAllUsernamesAndPasswords(username);
        if (admin != null) {
            apiResponse.setData(admin);
            apiResponse.setStatus("00");
            apiResponse.setMessage("dang nhap thanh cong");
        } else {
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("dang nhap that bai");
        }
        return apiResponse;
    }

}

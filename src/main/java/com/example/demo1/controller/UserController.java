package com.example.demo1.controller;

import com.example.demo1.dto.request.ProductUpdateRequest;
import com.example.demo1.dto.response.ApiResponse;
import com.example.demo1.dto.request.UserCreationRequest;
import com.example.demo1.dto.request.UserUpdateRequest;
import com.example.demo1.models.Admin;
import com.example.demo1.models.Product;
import com.example.demo1.models.User;
import com.example.demo1.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    ApiResponse<List<User>> createUser(@RequestBody UserCreationRequest request) {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        List<User> user = userService.CreateUser(request);
        if(user != null){
            apiResponse.setData(user);
            apiResponse.setStatus("00");
            apiResponse.setMessage("Tạo tài khoản thành công");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("Tạo tài khoản thất bại");
        }
        return apiResponse;
    }
    @GetMapping
    ApiResponse<List<User>> getAllUsers() {
        ApiResponse<List<User>> apiResponse = new ApiResponse<>();
        List<User> user = userService.getAllUsers();
        if(user != null){
            apiResponse.setData(user);
            apiResponse.setStatus("00");
            apiResponse.setMessage("Tạo tài khoản thành công");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("Tạo tài khoản thất bại");
        }
        return apiResponse;
    }


    @GetMapping("/{id}")
    ApiResponse<User>  getUserById(@PathVariable Integer id) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        User user = userService.getUserById(id);
        if(user != null){
            apiResponse.setData(user);
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
    ApiResponse<User> UpdateUser(@PathVariable Integer id, @RequestBody UserUpdateRequest request) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
        User user = userService.updateUser(id, request);
        if(user != null){
            apiResponse.setData(user);
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
    ApiResponse<User> deleteUser(@PathVariable Integer id ) {
        ApiResponse<User> apiResponse = new ApiResponse<>();
         userService.deleteUser(id);
        return apiResponse ;
//        return apiResponse;
    }
//    login user
@GetMapping("/login")
ApiResponse<User> loginUser( @RequestParam String username) {
    ApiResponse<User> apiResponse = new ApiResponse<>();
    User user = userService.getAllUsernamesAndPasswords(username);
    if(user != null){
        apiResponse.setData(user);
        apiResponse.setStatus("00");
        apiResponse.setMessage("Đăng nhập thành công");
    }else{
        apiResponse.setData(null);
        apiResponse.setStatus("01");
        apiResponse.setMessage("Đăng nhập thất bại");
    }
    return apiResponse;
}
    @PostMapping("/saveToRedis")
    public ResponseEntity<String> saveUserToRedis(@RequestParam String key, @RequestBody User user) {
        userService.saveUserToRedis(key, user);
        return ResponseEntity.ok("User saved to Redis successfully.");
    }
    @GetMapping("/getFromRedis")
    public ResponseEntity<User> getUserFromRedis(@RequestParam String key) {
        User user = userService.getUserFromRedis(key);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}

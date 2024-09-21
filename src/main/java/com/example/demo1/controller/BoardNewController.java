package com.example.demo1.controller;

import com.example.demo1.dto.request.AdminCreationRequest;
import com.example.demo1.dto.request.AdminUpdateRequest;
import com.example.demo1.dto.request.BoardNewCreationRequest;
import com.example.demo1.dto.request.BoardNewUpdateRequest;
import com.example.demo1.dto.response.ApiResponse;
import com.example.demo1.models.Admin;
import com.example.demo1.models.Boardnew;
import com.example.demo1.models.User;
import com.example.demo1.service.AdminService;
import com.example.demo1.service.BoardNewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/boardNew")
public class BoardNewController {
    @Autowired
    private BoardNewService boardNewService;
    @PostMapping
    ApiResponse<List<Boardnew>> createBoardNew(@RequestBody BoardNewCreationRequest request) {
        ApiResponse<List<Boardnew>> apiResponse = new ApiResponse<>();
        List<Boardnew> boardNew = boardNewService.CreateBoardNew(request);
        if(boardNew != null){
            apiResponse.setData(boardNew);
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
    ApiResponse<List<Boardnew>> getAllBoardNew() {
        ApiResponse<List<Boardnew>> response = new ApiResponse<>();
        List<Boardnew> list = boardNewService.getAllBoardNew();
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
    ApiResponse<Boardnew>  getBoardNewById(@PathVariable Integer id) {

            ApiResponse<Boardnew> apiResponse = new ApiResponse<>();
        Boardnew boardNew = boardNewService.getBoardNewById(id);
            if(boardNew != null){
                apiResponse.setData(boardNew);
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
    ApiResponse<Boardnew> UpdateBoardNew(@PathVariable Integer id, @RequestBody BoardNewUpdateRequest request) {
        ApiResponse<Boardnew> apiResponse = new ApiResponse<>();
        Boardnew boardnew = boardNewService.updateBoardNew(id, request);
        if(boardnew != null){
            apiResponse.setData(boardnew);
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
    ApiResponse<Admin> deleteBoardNew(@PathVariable Integer id ) {
        ApiResponse<Admin> apiResponse = new ApiResponse<>();
        boardNewService.deleteBoardNew(id);
        apiResponse.setMessage("Deleted Admin");
        return apiResponse ;
//        return apiResponse;
    }
}

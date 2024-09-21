package com.example.demo1.controller;

import com.example.demo1.dto.request.VoucherCreationRequest;
import com.example.demo1.dto.response.ApiResponse;
import com.example.demo1.models.Product;
import com.example.demo1.models.Vouchers;
import com.example.demo1.service.VoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/vouchers")
public class VoucherController {
    @Autowired
    private VoucherService voucherService;
    @GetMapping
    ApiResponse<List<Vouchers>> getAllVouchers() {
        ApiResponse<List<Vouchers>>  apiResponse = new ApiResponse();
        List<Vouchers> vouchers = voucherService.getAllVoucher();
        if(vouchers != null){
            apiResponse.setData(vouchers);
            apiResponse.setStatus("00");
            apiResponse.setMessage("Tất cả vouchers");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage(" thất bại");
        }
        return apiResponse;

    }
    @PostMapping
    ApiResponse<Vouchers> createVoucher(@RequestBody VoucherCreationRequest request) {
        ApiResponse<Vouchers> apiResponse = new ApiResponse();
        Vouchers vouchers = voucherService.saveVoucher(request);
        if(vouchers != null){
            apiResponse.setData(vouchers);
            apiResponse.setStatus("00");
            apiResponse.setMessage("Tạo voucher thành công");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("Tạo voucher thất bại");
        }
        return apiResponse;
    }
    @GetMapping("/{id}")
    ApiResponse<Vouchers> getVoucherById(@PathVariable Integer id) {
        ApiResponse<Vouchers> apiResponse = new ApiResponse();
        Vouchers vouchers = voucherService.getVoucherById(id);
        if(vouchers != null){
            apiResponse.setData(vouchers);
            apiResponse.setStatus("00");
            apiResponse.setMessage("thành cong");
        }else {
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("that bai");
        }
        return apiResponse;
    }
    @GetMapping("/code")
    ApiResponse<Vouchers> searchProduct(@RequestParam String code ) {
        try {
            ApiResponse<Vouchers> apiResponse = new ApiResponse<>();
            Vouchers vouchers = voucherService.SearchByCode(code);
            if (vouchers != null) {
                apiResponse.setData(vouchers);
                apiResponse.setStatus("00");
                apiResponse.setMessage("thành công");
            } else {
                apiResponse.setData(null);
                apiResponse.setStatus("01");
                apiResponse.setMessage("thất bại");
            }
            return apiResponse;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
   
}

package com.example.demo1.controller;

import com.example.demo1.dto.response.ApiResponse;
import com.example.demo1.models.Order;
import com.example.demo1.models.OrderStatus;
import com.example.demo1.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @GetMapping("/confirm-order")
    public ResponseEntity<String> confirmOrderAssignment(
            @RequestParam Long orderId,
            @RequestParam Integer shipperId) {

        try {
            // Gọi phương thức confirmOrderAssignment từ OrderService để xử lý việc xác nhận
            orderService.confirmOrderAssignment(orderId, shipperId);

            // Trả về thông báo thành công nếu không có lỗi xảy ra
            return ResponseEntity.ok("Order has been successfully assigned to the shipper.");
        } catch (IllegalArgumentException e) {
            // Nếu có lỗi (ví dụ ID không hợp lệ), trả về thông báo lỗi
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            // Xử lý các lỗi không mong muốn khác
            return ResponseEntity.status(500).body("An unexpected error occurred: " + e.getMessage());
        }
    }
    @GetMapping("/confirm")
    public ApiResponse<Order> confirmDelivery (@RequestParam Long orderId , @RequestParam String response){
        ApiResponse<Order> apiResponse = new ApiResponse<>();
        Order order = orderService.findById(orderId).orElseThrow();
        boolean accepted = "CHAP_NHAN".equalsIgnoreCase(response);
        if (accepted){
            order.setStatus(OrderStatus.DELIVERED);
        } else {
            order.setStatus(OrderStatus.CANCELED);
        }
        if(order != null){
            apiResponse.setData(order);
            apiResponse.setStatus("00");
            apiResponse.setMessage("nguoi dung co ID :");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage(" thất bại");
        }
        return apiResponse;
    }
}

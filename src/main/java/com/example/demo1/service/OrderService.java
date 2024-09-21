package com.example.demo1.service;

import com.example.demo1.models.Order;

import com.example.demo1.models.OrderStatus;
import com.example.demo1.models.Shipper;
import com.example.demo1.repositories.OrderRepository;
import com.example.demo1.repositories.ShipperRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShipperService shipperService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ShipperRepository shipperRepository;

    // lưu order
    public Order save(Order order) {
        return orderRepository.save(order);
    }
    public Optional<Order> findById (long id){
        return orderRepository.findById(id);
    }
    // Tìm shipper cho đơn hàng đang xử lý\
    @Scheduled(fixedRate = 50000)
    public void findShipperForProcessingOrders() throws MessagingException {
        List<Order> processingOrders = orderRepository.findByStatus(OrderStatus.PROCESSING);
        List<Shipper> activeShippers = shipperService.findActiveShipper();

        if (activeShippers.isEmpty()) {
            System.out.println("No shipper found");
        }

        int shipperCount = activeShippers.size();
        // Khởi tạo chỉ số index để chọn shipper theo vòng tròn (round-robin)
        int index = 0;

        // Duyệt qua từng đơn hàng đang xử lý
        for (Order order : processingOrders) {
            // Chọn shipper hiện tại theo chỉ số index
            Shipper selectedShipper = activeShippers.get(index);

            // Gửi yêu cầu xác nhận đến shipper này
            sendRequestToShipper(selectedShipper, order);


            // Tăng chỉ số index và reset nếu đã đến cuối danh sách shipper
            index = (index + 1) % shipperCount;
        }
    }
    public void sendRequestToShipper (Shipper shipper , Order order) throws MessagingException {
        // Tiêu đề email
        String subject = "Order Assignment Request";
        // Nội dung email bao gồm thông tin đơn hàng và đường link để shipper xác nhận
        String content = "Dear " + shipper.getName() + ",\n\n" +
                "You have been selected to deliver the order with ID: " + order.getId() + ".\n" +
                "Please confirm if you are available to take this order.\n\n" +
                "Click here to confirm: " +
                "http://localhost:8081/api/v1/order/confirm-order?orderId=" + order.getId() + "&shipperId=" + shipper.getId() + "\n\n" +
                "Thank you.";
        emailService.sendSimpleMail(shipper.getEmail(), subject , content);
    }
    // Phương thức xác nhận việc gán đơn hàng khi shipper đồng ý
    public void confirmOrderAssignment(Long orderId, Integer shipperId) throws MessagingException {
        // Tìm đơn hàng theo ID, nếu không tìm thấy, ném ra ngoại lệ IllegalArgumentException
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Invalid order ID"));
        // Tìm shipper theo ID, nếu không tìm thấy, ném ra ngoại lệ IllegalArgumentException
        Shipper shipper =  shipperRepository.findById(shipperId).orElseThrow(() -> new IllegalArgumentException("Invalid shipper ID"));

        // Kiểm tra nếu shipper còn khả dụng
        if (shipper != null) {
            // Gán đơn hàng cho shipper này
            shipperService.assignShipper(Math.toIntExact(order.getId()), shipper.getId());
            // Cập nhật trạng thái đơn hàng thành SHIPPED
            order.setStatus(OrderStatus.SHIPPED);
            // Lưu đơn hàng vào cơ sở dữ liệu
            orderRepository.save(order);

            // Cập nhật trạng thái nhận đơn của shipper thành RECEIVED và tăng số lượng đơn hàng
            shipper.setReceivingStatus(Shipper.ReceivingStatus.RECEIVED);
            shipper.setOrderCount(shipper.getOrderCount() + 1);
            // Lưu shipper vào cơ sở dữ liệu
            shipperService.save(shipper);
            // gửi thông báo đến khách hàng
            shipperService.sendDeliveryNotificationToCustomer(order.getId(),shipper);
        } else {
            // Nếu shipper không còn khả dụng, in ra thông báo
            System.out.println("Shipper is not available");
        }
    }
}

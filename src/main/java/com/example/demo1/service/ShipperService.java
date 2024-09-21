package com.example.demo1.service;

import com.example.demo1.models.Shipper;
import com.example.demo1.repositories.ShipperRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ShipperService {
    @Autowired
    private ShipperRepository shipperRepository;
    @Autowired
    private EmailService emailService;
    public List<Shipper> findAll() {
        return shipperRepository.findAll();
    }
    public List<Shipper>  findById (Integer id) {
        return Collections.singletonList(shipperRepository.findById(id).orElse(null));
    }
    public Shipper save(Shipper shipper) {
        return shipperRepository.save(shipper);
    }
    // Tìm shipper có sẵn ( đang active>
    public List<Shipper> findActiveShipper(){
     return shipperRepository.findByStatus(Shipper.Status.ACTIVE);
    }
    // Gán đơn hàng cho shipper
    public void assignShipper(Integer transactionId ,Integer shipperId) {

    }
    // gửi yêu câu đến khách hàng
    public void sendDeliveryNotificationToCustomer(Long orderId , Shipper shipper) throws MessagingException {
        String subject = "Xác nhận đơn hàng #" + orderId;
        String confirmUrl = "http://localhost:8081/api/v1/order/confirm?orderId=" + orderId + "&response=CHAP_NHAN";
        String declineUrl = "http://localhost:8081/api/v1/order/confirm?orderId=" + orderId + "&response=TU_CHOI";

        String text = "Xin chào,\n\nShipper đã giao hàng cho bạn. Vui lòng xác nhận việc nhận hàng bằng cách nhấp vào một trong các liên kết dưới đây:\n\n"
                + "Chấp nhận nhận hàng: " + confirmUrl + "\n"
                + "Từ chối nhận hàng: " + declineUrl + "\n\n"
                + "Cảm ơn bạn.";
        emailService.sendSimpleMail(shipper.getEmail(),subject,text);


    }
}

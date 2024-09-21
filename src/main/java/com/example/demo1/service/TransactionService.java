package com.example.demo1.service;

import com.example.demo1.dto.request.TransactionCreationRequest;
import com.example.demo1.dto.request.TransactionUpdateRequest;
import com.example.demo1.mapper.TransactionMapper;
import com.example.demo1.models.Order;
import com.example.demo1.models.OrderStatus;
import com.example.demo1.models.Transactions;
import com.example.demo1.repositories.TransactionRepository;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private TransactionMapper transactionMapper;
    @Autowired
    private EmailService emailService;
    @Autowired
    private OrderService orderedService   ;
    @Autowired
    private OrderService orderService;


    // Tạo
    public List<Transactions> CreateTransaction(TransactionCreationRequest request) {

        Transactions transaction = transactionMapper.toTransactions(request);

        // ma hoa password admin
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
//        admin.setPassword(passwordEncoder.encode(request.getPassword()));



        return Collections.singletonList(transactionRepository.save(transaction));

    }
    // lấy tất cả danh sách admin
    public List<Transactions> getAllTransaction() {
        return transactionRepository.findAll();
    }
    // lấy theo id
    public Transactions getTransactionsById(Integer id) {
        return transactionRepository.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }
    /// cập nhập admin
    public Transactions updateTransactions(Integer id , TransactionUpdateRequest request) {
        Transactions transactions = getTransactionsById(id);
        transactionMapper.updateTransaction(transactions,request);
//
        return transactionRepository.save(transactions);
    }
    // xóa admin theo id
    public void deletetransactions(Integer id) {
        transactionRepository.deleteById(id);
    }

    public Transactions getTransactionsByClientId(Integer clientId) {
            return transactionRepository.findByClientId(clientId);
    }

    public Integer getClientId() {
        return transactionRepository.getNextSequenceValue();
    }


    /**
     * Gửi email xác nhận cho người dùng về transaction.
     * @param transaction Đối tượng transaction chứa thông tin người dùng.
     */
    private void sendConfirmationEmail(Transactions transaction) throws MessagingException {
        String subject = "Xác nhận đơn hàng #" + transaction.getId();
        String text = String.format(
                "Xin chào %s,\n\n" +
                        "Bạn đã đặt hàng với ID #%d. \n" +
                        "Vui lòng xác nhận đơn hàng của bạn. \n\n" +
                        "Thông tin đơn hàng:\n" +
                        "Số điện thoại: %s\n" +
                        "Địa chỉ: %s\n" +
                        "Số tiền: %s\n" +
                        "Thanh toán: %s\n\n" +
                        "Cảm ơn bạn!",
                transaction.getUserName(),
                transaction.getId(),
                transaction.getUserPhone(),
                transaction.getAddress(),
                transaction.getAmount(),
                transaction.getPayment()
        );
        emailService.sendSimpleMail(transaction.getUserMail(), subject, text);
    }

    // Phương thức xử lý giao dịch và gửi email xác nhận
    public Transactions processTransaction(Transactions transaction) throws MessagingException {
        if ("PENDING_CONFIRMATION".equals(transaction.getStatus())) {
            transactionRepository.save(transaction);
            String clientId = String.valueOf(transaction.getId());
            String confirmationLink = "http://localhost:8081/api/v1/transaction/confirm-transaction/" + clientId;

            String subject = "Xác nhận giao dịch";
            String body = "<h1>Vui lòng xác nhận giao dịch</h1>"
                    + "<p>Giao dịch của bạn: " + transaction.getMessage() + "</p>"
                    + "<a href=\"" + confirmationLink + "\" style=\"background-color: #4CAF50; color: white; padding: 10px 20px; text-align: center; text-decoration: none; display: inline-block;\">Xác nhận giao dịch</a>";
            emailService.sendSimpleMail(transaction.getUserMail(), subject, body);

        }
        return transaction;
    }
    // Phương thức xác nhận giao dịch và gửi email phản hồi và lưu vào đơn hàng
    public String confirmTransaction(Integer id) throws MessagingException {
        Transactions transaction = transactionRepository.findById(id).orElseThrow();
        if (transaction != null && "PENDING_CONFIRMATION".equals(transaction.getStatus())) {
            transaction.setStatus("CONFIRMED");
            transactionRepository.save(transaction);
            // lưu vào đơn hàng
            Order order = new Order();
            order.setTransactionId(transaction.getId());
            order.setStatus(OrderStatus.PROCESSING);
            order.setAddress(transaction.getAddress());
            order.setAmount(Double.parseDouble(transaction.getAmount()));
            order.setPayment(transaction.getPayment());
            order.setUserName(transaction.getUserName());
            order.setUserPhone(transaction.getUserPhone());
            order.setUserMail(transaction.getUserMail());
            order.setMessage(transaction.getMessage());
            orderService.save(order);
            // Gửi email phản hồi xác nhận thành công
            String subject = "Giao dịch đã được xác nhận";
            String body = "<h1>Xác nhận đặt hàng thành công!</h1>"
                    + "<p>Cảm ơn bạn đã xác nhận giao dịch của mình.</p>";
            emailService.sendSimpleMail(transaction.getUserMail(), subject, body);

            // Trả về thông báo thành công
            return "<html><body><h1>Xác nhận giao dịch thành công!</h1><p>Cảm ơn bạn đã xác nhận giao dịch của mình.</p></body></html>";
        } else {
            // Trả về thông báo lỗi nếu giao dịch không hợp lệ
            return "<html><body><h1>Lỗi!</h1><p>Giao dịch không hợp lệ hoặc đã được xác nhận.</p></body></html>";
        }

    }







}

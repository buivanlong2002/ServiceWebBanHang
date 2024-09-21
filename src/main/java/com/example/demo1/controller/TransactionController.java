package com.example.demo1.controller;

import com.example.demo1.dto.request.TransactionCreationRequest;
import com.example.demo1.dto.request.TransactionUpdateRequest;
import com.example.demo1.dto.response.ApiResponse;
import com.example.demo1.models.Transactions;
import com.example.demo1.repositories.TransactionRepository;
import com.example.demo1.service.EmailService;
import com.example.demo1.service.TransactionService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "api/v1/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping
    ApiResponse<List<Transactions>> createTransaction(@RequestBody TransactionCreationRequest request) {
        ApiResponse<List<Transactions>> apiResponse = new ApiResponse<>();
        List<Transactions> transactions = transactionService.CreateTransaction(request);
        if(transactions != null){
            apiResponse.setData(transactions);
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
    ApiResponse<List<Transactions>> getAllTransaction() {
        ApiResponse<List<Transactions>> apiResponse = new ApiResponse<>();
        List<Transactions> transactions =transactionService.getAllTransaction();
        if(transactions != null){
            apiResponse.setData(transactions);
            apiResponse.setStatus("00");
            apiResponse.setMessage(" thành công");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("");
        }

        return apiResponse  ;
    }


    @GetMapping("/{id}")
    ApiResponse<Transactions>  getTransactionById(@PathVariable Integer id) {
        ApiResponse<Transactions> apiResponse = new ApiResponse<>();
        Transactions transactions = transactionService.getTransactionsById(id);
        if(transactions != null){
            apiResponse.setData(transactions);
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
    ApiResponse<Transactions> UpdateTransactions(@PathVariable Integer id, @RequestBody TransactionUpdateRequest request) {
        ApiResponse<Transactions> apiResponse = new ApiResponse<>();
        Transactions transactions = transactionService.updateTransactions(id, request);
        if(transactions != null){
            apiResponse.setData(transactions);
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
    ApiResponse<Transactions> deleteTransactions(@PathVariable Integer id ) {
        ApiResponse<Transactions> apiResponse = new ApiResponse<>();
        transactionService.deletetransactions(id);
        return apiResponse ;
//        return apiResponse;
    }
    @GetMapping("/clientId")
    ApiResponse<Integer> getClientId() {
        ApiResponse<Integer> apiResponse = new ApiResponse<>();
        Integer clientId = transactionService.getClientId();
        if(clientId != null){
            apiResponse.setData(clientId);
            apiResponse.setStatus("00");
            apiResponse.setMessage("Update thành công");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("Update thất bại");
        }
        return apiResponse;
    }
    @GetMapping("/Client/{clientId}")
    ApiResponse<Transactions>  getTransactionByClientId(@PathVariable Integer clientId) {
        ApiResponse<Transactions> apiResponse = new ApiResponse<>();
        Transactions transactions = transactionService.getTransactionsByClientId(clientId);
        if(transactions != null){
            apiResponse.setData(transactions);
            apiResponse.setStatus("00");
            apiResponse.setMessage(" thành công");
        }else{
            apiResponse.setData(null);
            apiResponse.setStatus("01");
            apiResponse.setMessage("không có người dùng");
        }

        return apiResponse;
    }

    @PostMapping("/process")
    public ResponseEntity<Transactions> processTransaction(@RequestBody Transactions transaction) throws MessagingException {
        Transactions processedTransaction = transactionService.processTransaction(transaction);
        return ResponseEntity.ok(processedTransaction);
    }

    @GetMapping("/confirm-transaction/{id}")
    public ResponseEntity<String> confirmTransaction(@PathVariable Integer id) throws MessagingException {
        String responseMessage = transactionService.confirmTransaction(id);
        return ResponseEntity.ok(responseMessage);
    }



    //////////////////////////////





}

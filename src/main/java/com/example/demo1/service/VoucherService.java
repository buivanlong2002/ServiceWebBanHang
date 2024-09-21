package com.example.demo1.service;

import com.example.demo1.dto.request.VoucherCreationRequest;
import com.example.demo1.dto.request.VoucherUpdateRequest;
import com.example.demo1.mapper.VoucherMapper;
import com.example.demo1.models.Product;
import com.example.demo1.models.Vouchers;
import com.example.demo1.repositories.VoucherRepository;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class VoucherService {
    @Autowired
    private VoucherRepository voucherRepository;
    @Autowired
    private VoucherMapper voucherMapper;

    public List<Vouchers> getAllVoucher (){
        return voucherRepository.findAll();
    }
    public Vouchers getVoucherById(int id) {
        return voucherRepository.findById(id).get();
    }
    public Vouchers saveVoucher(VoucherCreationRequest request) {
       Vouchers voucher =  voucherMapper.toVouchers(request);
        return voucherRepository.save(voucher);
    }
    public Vouchers updateVoucher(Integer id , VoucherUpdateRequest request) {
        Vouchers voucher = voucherRepository.findById(id).get();
        voucherMapper.updateVoucher(voucher, request);
        return voucherRepository.save(voucher);
    }
    public void deleteVoucher(int id) {
        voucherRepository.deleteById(id);
    }
    public  Vouchers SearchByCode(String code) throws Exception {

       Vouchers vouchers = voucherRepository.findByCodeContaining(code);
        if (vouchers != null){
            if (vouchers.getUsageLimit() > 0){
                vouchers.setUsageLimit(vouchers.getUsageLimit() - 1);
                voucherRepository.save(vouchers);
            }else{
                throw  new Exception("hết lượt sử dụng");
            }
        }else {
            throw  new Exception("Voucher not found");
        }
        return vouchers;
    }


}

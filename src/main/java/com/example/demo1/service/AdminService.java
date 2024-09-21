package com.example.demo1.service;

import com.example.demo1.dto.request.AdminCreationRequest;
import com.example.demo1.dto.request.AdminUpdateRequest;
import com.example.demo1.exception.AppException;
import com.example.demo1.exception.ErrorCode;
import com.example.demo1.mapper.AdminMapper;
import com.example.demo1.models.Admin;
import com.example.demo1.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {
    @Autowired
   private AdminRepository adminRepository;
    @Autowired
    private AdminMapper adminMapper;
    // Tạo Admin
    public List<Admin>CreateAdmin(AdminCreationRequest request) {
        if(adminRepository.existsByUsername(request.getUsername()))
            throw new AppException(ErrorCode.ADMIN_EXISTED);
        Admin admin = adminMapper.toAdmin(request);

        // ma hoa password admin
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        admin.setPassword(passwordEncoder.encode(request.getPassword()));



        return Collections.singletonList(adminRepository.save(admin));

    }
    // lấy tất cả danh sách admin
    public List<Admin> getAllAdmin() {
        return adminRepository.findAll();
    }
    // lấy theo id
    public Admin getAdminById(Integer id) {
      return  adminRepository.findById(id).orElseThrow();

    }
    /// cập nhập admin
    public Admin updateAdmin(Integer id , AdminUpdateRequest request) {
     Admin admin = adminRepository.findById(id).get();
     adminMapper.updateAdmin(admin,request);
//
        return adminRepository.save(admin);
    }
    // xóa admin theo id
    public void deleteAdmin(Integer id) {
        adminRepository.deleteById(id);
    }
// lấy password và username của admin theo username
    public Admin getAllUsernamesAndPasswords(String username) {
        Admin admin = adminRepository.findByUsername(username);
        if (admin != null) {
            Admin result = new Admin();
            result.setUsername(admin.getUsername());
            result.setPassword(admin.getPassword());
            return result;
        }
        return null;
    }
}

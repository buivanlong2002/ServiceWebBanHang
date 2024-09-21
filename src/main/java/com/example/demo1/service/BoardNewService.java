package com.example.demo1.service;


import com.example.demo1.dto.request.BoardNewCreationRequest;
import com.example.demo1.dto.request.BoardNewUpdateRequest;

import com.example.demo1.mapper.BoardNewMapper;

import com.example.demo1.models.Boardnew;
import com.example.demo1.repositories.BoardNewRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BoardNewService {
    @Autowired
    private BoardNewRepository boardNewRepository;
    @Autowired
    private BoardNewMapper boardNewMapper;
    public List<Boardnew> CreateBoardNew(BoardNewCreationRequest request) {
//
        Boardnew boardnew = boardNewMapper.toBoardNew(request);
        return Collections.singletonList(boardNewRepository.save(boardnew));

    }
    // lấy tất cả danh sách
    public List<Boardnew> getAllBoardNew() {
        return boardNewRepository.findAll();
    }
    // lấy theo id
    public Boardnew getBoardNewById(Integer id) {
        return boardNewRepository.findById(id).orElseThrow(()->new RuntimeException("admin not found"));
    }
    /// cập nhập admin
    public Boardnew updateBoardNew(Integer id , BoardNewUpdateRequest request) {
        Boardnew boardNew = getBoardNewById(id);
        boardNewMapper.updateBoardNew(boardNew,request);
//
        return boardNewRepository.save(boardNew);
    }
    // xóa admin theo id
    public void deleteBoardNew(Integer id) {
        boardNewRepository.deleteById(id);
    }

    }




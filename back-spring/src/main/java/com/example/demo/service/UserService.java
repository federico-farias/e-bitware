package com.example.demo.service;

import com.example.demo.dto.CreateUserDTO;
import com.example.demo.dto.SearchUsersDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    void create(CreateUserDTO request);

    SearchUsersDTO findAll();
}

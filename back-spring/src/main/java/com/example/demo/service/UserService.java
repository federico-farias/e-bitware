package com.example.demo.service;

import com.example.demo.dto.CreateUserDTO;
import com.example.demo.dto.SearchUsersDTO;
import com.example.demo.dto.UserDTO;
import org.springframework.http.ResponseEntity;

public interface UserService {

    String create(CreateUserDTO request);

    SearchUsersDTO findAll();

    UserDTO findById(String id);
}

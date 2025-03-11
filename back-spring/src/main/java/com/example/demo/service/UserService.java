package com.example.demo.service;

import com.example.demo.dto.SearchUsersDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserRequestDTO;

public interface UserService {

    String create(UserRequestDTO request);

    SearchUsersDTO findAll();

    UserDTO findById(String id);

    UserDTO update(String id, UserRequestDTO request);

    void delete(String id);
}

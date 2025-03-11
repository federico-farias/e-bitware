package com.example.demo.service;

import com.example.demo.dto.UserRequestDTO;
import com.example.demo.dto.SearchUsersDTO;
import com.example.demo.dto.UserDTO;

public interface UserService {

    String create(UserRequestDTO request);

    SearchUsersDTO findAll();

    UserDTO findById(String id);

    UserDTO update(String id, UserRequestDTO request);

}

package com.example.demo.controller;

import com.example.demo.dto.CreateUserDTO;
import com.example.demo.dto.SearchUsersDTO;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserDTO request) {
        this.userService.create(request);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<SearchUsersDTO> findAll() {
        var result = this.userService.findAll();
        return ResponseEntity.ok(result);
    }

}


package com.example.demo.controller;

import com.example.demo.dto.CreateUserDTO;
import com.example.demo.dto.SearchUsersDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CreateUserDTO request) {
        var id = this.userService.create(request);
        return ResponseEntity.created(URI.create(String.format("/%s", id))).build();
    }

    @GetMapping
    public ResponseEntity<SearchUsersDTO> findAll() {
        var result = this.userService.findAll();
        return ResponseEntity.ok(result);
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable("id") String id) {
        var result = this.userService.findById(id);
        return ResponseEntity.ok(result);
    }

}


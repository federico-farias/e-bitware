package com.example.demo.service;

import com.example.demo.UserRepository;
import com.example.demo.dto.CreateUserDTO;
import com.example.demo.dto.SearchUsersDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public void create(CreateUserDTO request) {
        Usuario usuario = new Usuario();
        usuario.setId(UUID.randomUUID().toString());
        usuario.setName(request.getName());
        usuario.setEmail(request.getEmail());
        usuario.setCreatedAt(new Date());
        usuario.setUpdatedAt(new Date());
        this.userRepository.save(usuario);
    }

    @Override
    public SearchUsersDTO findAll() {
        var list = this.userRepository.findAll().stream().map(
                u -> new UserDTO(
                        u.getId(),
                        u.getName(),
                        u.getEmail(),
                        u.getCreatedAt(),
                        u.getUpdatedAt()
                )
        ).toList();
        return new SearchUsersDTO(list);
    }

}

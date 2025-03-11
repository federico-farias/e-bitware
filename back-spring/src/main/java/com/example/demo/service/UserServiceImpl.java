package com.example.demo.service;

import com.example.demo.UserRepository;
import com.example.demo.dto.SearchUsersDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.dto.UserRequestDTO;
import com.example.demo.exception.UserNotFound;
import com.example.demo.model.Usuario;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public String create(UserRequestDTO request) {
        var id = UUID.randomUUID().toString();
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setName(request.getName());
        usuario.setEmail(request.getEmail());
        usuario.setCreatedAt(new Date());
        usuario.setUpdatedAt(new Date());
        usuario.setActive(true);
        this.userRepository.save(usuario);
        return id;
    }

    @Override
    public SearchUsersDTO findAll() {
        var list = this.userRepository.findAllActive().stream().map(
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

    @Override
    public UserDTO findById(String id) {
        var user = this.userRepository.findByIdAndActive(id).orElseThrow(() -> new UserNotFound("usuario no encontrado"));
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Override
    public UserDTO update(String id, UserRequestDTO request) {
        var user = this.userRepository.findByIdAndActive(id).orElseThrow(() -> new UserNotFound("usuario no encontrado"));
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setUpdatedAt(new Date());
        this.userRepository.save(user);
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    @Override
    public void delete(String id) {
        var user = this.userRepository.findByIdAndActive(id).orElseThrow(() -> new UserNotFound("usuario no encontrado"));
        user.setActive(false);
        this.userRepository.save(user);
    }


}

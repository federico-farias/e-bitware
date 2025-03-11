package com.example.demo;

import com.example.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Usuario, String> {

    @Query("select u from Usuario u where u.active = true")
    List<Usuario> findAllActive();

    @Query("select u from Usuario u where u.id = :id and u.active = true")
    Optional<Usuario> findByIdAndActive(@Param("id") String id);

}

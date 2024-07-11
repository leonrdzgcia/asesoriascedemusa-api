package com.example.demo.repositories;

import com.example.demo.models.UsuarioMailModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositoryMail extends JpaRepository<UsuarioMailModel, Long> {

    Optional<UsuarioMailModel> findByEmail(String email);
    Optional<UsuarioMailModel> findByResetToken(String resetToken);

}

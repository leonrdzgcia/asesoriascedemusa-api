package com.example.demo.services;

import com.example.demo.models.Usuario;
import com.example.demo.repositories.UsuarioRepositoryMail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

public class RestablecerContrasenaService {

    @Autowired
    private UsuarioRepositoryMail usuarioRepository;

    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreoRestablecimiento(String email) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(email);
        if (!optionalUsuario.isPresent()) {
            throw new RuntimeException("Usuario no encontrado");
        }

        Usuario usuario = optionalUsuario.get();
        usuario.setResetToken(UUID.randomUUID().toString());
        usuarioRepository.save(usuario);

        SimpleMailMessage correo = new SimpleMailMessage();
        correo.setTo(usuario.getEmail());
        correo.setSubject("Solicitud de restablecimiento de contraseña");
        correo.setText("Para restablecer su contraseña, haga clic en el siguiente enlace: "
                + "http://localhost:8080/restablecer?token=" + usuario.getResetToken());

        mailSender.send(correo);
    }

    public Optional<Usuario> obtenerUsuarioPorToken(String token) {
        return usuarioRepository.findByResetToken(token);
    }

    public void actualizarContrasena(Usuario usuario, String nuevaContraseña) {
        usuario.setResetToken(null);
        // Aquí deberías cifrar la contraseña antes de guardarla
        usuario.setPassword(nuevaContraseña);
        usuarioRepository.save(usuario);
    }
}

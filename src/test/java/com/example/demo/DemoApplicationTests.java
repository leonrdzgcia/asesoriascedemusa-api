package com.example.demo;

import com.example.demo.repositories.UsuarioJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

@Autowired
	UsuarioJpaRepository usuarioJpaRepository;


}

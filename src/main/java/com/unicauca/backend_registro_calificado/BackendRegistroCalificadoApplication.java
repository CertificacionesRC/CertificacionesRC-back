package com.unicauca.backend_registro_calificado;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication()
public class BackendRegistroCalificadoApplication {
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	public static void main(String[] args) {
		SpringApplication.run(BackendRegistroCalificadoApplication.class, args);
		System.out.println("Inicio app");
	}

	@PostConstruct
	public void encodePasswords() {
		String password = "usuario";

		for (int i = 0; i < 2; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
	}

}

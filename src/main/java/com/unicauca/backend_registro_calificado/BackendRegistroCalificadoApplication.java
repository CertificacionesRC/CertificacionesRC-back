package com.unicauca.backend_registro_calificado;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BackendRegistroCalificadoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendRegistroCalificadoApplication.class, args);
		System.out.println("Inicio app");
	}

}

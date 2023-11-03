package com.example.EjercicioProducto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication
@EnableJpaRepositories("com.example.EjercicioProducto.*")
@EntityScan("com.example.EjercicioProducto.*")
@ComponentScan(basePackages = { "com.example.EjercicioProducto.*" })
public class EjercicioProductoApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjercicioProductoApplication.class, args);
	}

}

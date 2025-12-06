package com.nailton.spring_render;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;


@SpringBootApplication
public class SpringRenderApplication {

	public static void main(String[] args) {
      

		// --- Passo de Carregamento do .env ---
        Dotenv dotenv = Dotenv.load();
        
        // Carrega todas as variáveis do .env como Propriedades do Sistema
        dotenv.entries().forEach(entry -> {
            System.setProperty(entry.getKey(), entry.getValue());
        });
        
		SpringApplication.run(SpringRenderApplication.class, args);
	}

}

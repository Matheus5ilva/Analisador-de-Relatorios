package br.com.analisador;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AnalisadorApplication {

	private static final Logger logger = LoggerFactory.getLogger(AnalisadorApplication.class);

	public static void main(String[] args) {
		logger.info("Iniciando a aplicação...");
		SpringApplication.run(AnalisadorApplication.class, args);
		logger.info("Aplicação finalizada.");
	}
}

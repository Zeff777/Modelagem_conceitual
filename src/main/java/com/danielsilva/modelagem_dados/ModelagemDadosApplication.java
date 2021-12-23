package com.danielsilva.modelagem_dados;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.danielsilva.modelagem_dados.domain.Categoria;
import com.danielsilva.modelagem_dados.repositories.CategoriaRepository;

@SpringBootApplication
public class ModelagemDadosApplication implements CommandLineRunner {

    @Autowired
	private CategoriaRepository categoriaRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ModelagemDadosApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

	}

}

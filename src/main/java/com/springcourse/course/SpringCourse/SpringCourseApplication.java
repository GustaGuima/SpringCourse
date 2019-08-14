package com.springcourse.course.SpringCourse;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.springcourse.course.SpringCourse.domain.Categoria;
import com.springcourse.course.SpringCourse.repositories.CategoriaRepository;

@SpringBootApplication
public class SpringCourseApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaDAO;

	public static void main(String[] args) {
		SpringApplication.run(SpringCourseApplication.class, args);
	}
	 

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");

		categoriaDAO.saveAll(Arrays.asList(cat1, cat2));
		
	}

}

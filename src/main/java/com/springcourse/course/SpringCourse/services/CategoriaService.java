package com.springcourse.course.SpringCourse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.course.SpringCourse.domain.Categoria;
import com.springcourse.course.SpringCourse.repositories.CategoriaRepository;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaDAO;
	
	public Categoria search(Integer id) {
		Optional<Categoria> categoria1 = categoriaDAO.findById(id);
		return categoria1.orElse(null);
	}

}

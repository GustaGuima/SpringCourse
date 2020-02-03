package com.springcourse.course.SpringCourse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.course.SpringCourse.domain.Categoria;
import com.springcourse.course.SpringCourse.repositories.CategoriaRepository;
import com.springcourse.course.SpringCourse.services.exception.ObjectNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository categoriaDAO;
	
	public Categoria search(Integer id){
		Optional<Categoria> categoria = categoriaDAO.findById(id);
		
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! id : "+ id +" Categoria : "+ Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return categoriaDAO.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		search(categoria.getId());
		return categoriaDAO.save(categoria);
	}

}

package com.springcourse.course.SpringCourse.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.course.SpringCourse.domain.Categoria;
import com.springcourse.course.SpringCourse.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> Find(@PathVariable Integer id) {
		
		Categoria categoria = service.search(id);
		
		return ResponseEntity.ok().body(categoria);
	}

}

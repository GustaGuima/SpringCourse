package com.springcourse.course.SpringCourse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springcourse.course.SpringCourse.domain.Categoria;
import com.springcourse.course.SpringCourse.dto.CategoriaDTO;
import com.springcourse.course.SpringCourse.repositories.CategoriaRepository;
import com.springcourse.course.SpringCourse.services.exception.DataIntegrityException;
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
		Categoria newCategoria = search(categoria.getId());
		updateData(newCategoria, categoria);
		return categoriaDAO.save(newCategoria);
	}

	private void updateData(Categoria newCategoria, Categoria categoria) {
		newCategoria.setName(categoria.getName());
	}

	public void delete(Integer id) {
		search(id);
		try {
			categoriaDAO.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir essa Categoria pois ela possui produtos vinculados a ela");			
		}
	}

	public List<Categoria> findAll() {
		return categoriaDAO.findAll();
	}
	
	public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return categoriaDAO.findAll(pageRequest);	
	}
	
	public Categoria fromDTO(CategoriaDTO catDTO) {
		return new Categoria(catDTO.getId(), catDTO.getName());
	}

}

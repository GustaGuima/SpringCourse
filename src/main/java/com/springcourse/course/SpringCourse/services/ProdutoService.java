package com.springcourse.course.SpringCourse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springcourse.course.SpringCourse.domain.Categoria;
import com.springcourse.course.SpringCourse.domain.Produto;
import com.springcourse.course.SpringCourse.repositories.CategoriaRepository;
import com.springcourse.course.SpringCourse.repositories.ProdutoRepository;
import com.springcourse.course.SpringCourse.services.exception.ObjectNotFoundException;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoDAO;
	
	@Autowired
	private CategoriaRepository categoriaDAO;
	
	public Produto find(Integer id){
		Optional<Produto> produto = produtoDAO.findById(id);
		
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! id : "+ id +" Produto : "+ Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		List<Categoria> categorias = categoriaDAO.findAllById(ids);
		return produtoDAO.search(nome, categorias, pageRequest);
	}

}

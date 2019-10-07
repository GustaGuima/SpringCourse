package com.springcourse.course.SpringCourse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.course.SpringCourse.domain.Cliente;
import com.springcourse.course.SpringCourse.repositories.ClienteRepository;
import com.springcourse.course.SpringCourse.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteDAO;
	
	public Cliente search(Integer id){
		Optional<Cliente> cliente = clienteDAO.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! id : "+ id +" Categoria : "+ Cliente.class.getName()));
	}

}

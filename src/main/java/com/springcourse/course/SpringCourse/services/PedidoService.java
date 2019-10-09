package com.springcourse.course.SpringCourse.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.course.SpringCourse.domain.Pedido;
import com.springcourse.course.SpringCourse.repositories.PedidoRepository;
import com.springcourse.course.SpringCourse.services.exception.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoDAO;
	
	public Pedido search(Integer id){
		Optional<Pedido> pedido = pedidoDAO.findById(id);
		
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! id : "+ id +" Pedido : "+ Pedido.class.getName()));
	}

}

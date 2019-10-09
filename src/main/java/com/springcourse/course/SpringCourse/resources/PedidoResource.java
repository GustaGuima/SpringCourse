package com.springcourse.course.SpringCourse.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.course.SpringCourse.domain.Pedido;
import com.springcourse.course.SpringCourse.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService service;
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> Find(@PathVariable Integer id) {
		Pedido pedido = service.search(id);	
		return ResponseEntity.ok().body(pedido);
	}

}

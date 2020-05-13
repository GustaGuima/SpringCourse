package com.springcourse.course.SpringCourse.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springcourse.course.SpringCourse.domain.Cliente;
import com.springcourse.course.SpringCourse.dto.ClienteDTO;
import com.springcourse.course.SpringCourse.repositories.ClienteRepository;
import com.springcourse.course.SpringCourse.services.exception.DataIntegrityException;
import com.springcourse.course.SpringCourse.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteDAO;
	
	public Cliente search(Integer id){
		Optional<Cliente> cliente = clienteDAO.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! id : "+ id +" Cliente : "+ Cliente.class.getName()));
	}
	
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		return clienteDAO.save(cliente);
	}

	public Cliente update(Cliente cliente) {
		Cliente newClient = search(cliente.getId());
		updateData(newClient, cliente);
		return clienteDAO.save(newClient);
	}

	private void updateData(Cliente newClient, Cliente cliente) {
		newClient.setNome(cliente.getNome());
		newClient.setEmail(cliente.getEmail());
	} 

	public void delete(Integer id) {
		search(id);
		try {
			clienteDAO.deleteById(id);
		} catch(DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possivel excluir esse Cliente pois há entidades relacionadas");			
		}
	}

	public List<Cliente> findAll() {
		return clienteDAO.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteDAO.findAll(pageRequest);	
	}
	
	public Cliente fromDTO(ClienteDTO cliDTO) {
		return new Cliente(cliDTO.getId(), cliDTO.getName(), cliDTO.getEmail(), null, null);
	}


}

package com.springcourse.course.SpringCourse.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.springcourse.course.SpringCourse.domain.Cidade;
import com.springcourse.course.SpringCourse.domain.Cliente;
import com.springcourse.course.SpringCourse.domain.Endereco;
import com.springcourse.course.SpringCourse.domain.enums.TipoCliente;
import com.springcourse.course.SpringCourse.dto.ClienteDTO;
import com.springcourse.course.SpringCourse.dto.ClienteNewDTO;
import com.springcourse.course.SpringCourse.repositories.ClienteRepository;
import com.springcourse.course.SpringCourse.repositories.EnderecoRepository;
import com.springcourse.course.SpringCourse.services.exception.DataIntegrityException;
import com.springcourse.course.SpringCourse.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteDAO;
	
	@Autowired
	private EnderecoRepository enderecoDAO;
	
	public Cliente search(Integer id){
		Optional<Cliente> cliente = clienteDAO.findById(id);
		
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto nao encontrado! id : "+ id +" Cliente : "+ Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteDAO.save(cliente);
		enderecoDAO.saveAll(cliente.getEnderecos());
		return cliente;
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
			throw new DataIntegrityException("Não é possivel excluir ess Cliente pois há entidades relacionadas");			
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
	
	public Cliente fromDTO(ClienteNewDTO cliDTO) {
		Cliente cli = new Cliente(null, cliDTO.getNome(), cliDTO.getEmail(), cliDTO.getCpf_cnpj(), TipoCliente.toEnum(cliDTO.getTipo()));
		Cidade cid = new Cidade(cliDTO.getCidadeId(), null, null);
		Endereco end = new Endereco(null, cliDTO.getLogradouro(), cliDTO.getNumero(), cliDTO.getComplemente(), cliDTO.getBairro(), cliDTO.getCep(), cli, cid);
		cli.getEnderecos().add(end);
		cli.getTelefones().add(cliDTO.getTelefone1());
		if(cliDTO.getTelefone2() != null)
			cli.getTelefones().add(cliDTO.getTelefone2());
		if(cliDTO.getTelefone3() != null)
			cli.getTelefones().add(cliDTO.getTelefone3());
		
		return cli;
		
	}



}

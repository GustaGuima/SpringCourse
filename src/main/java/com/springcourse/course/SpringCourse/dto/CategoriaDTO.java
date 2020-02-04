package com.springcourse.course.SpringCourse.dto;

import java.io.Serializable;

import com.springcourse.course.SpringCourse.domain.Categoria;

public class CategoriaDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	
	public CategoriaDTO() {
	}
	
	public CategoriaDTO(Categoria categoria) {
		id = categoria.getId();
		name = categoria.getName();
	}

	//Getters and Setters
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}

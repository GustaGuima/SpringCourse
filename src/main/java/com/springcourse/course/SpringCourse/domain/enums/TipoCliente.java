package com.springcourse.course.SpringCourse.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Fisíca"),
	PESSOAJURIDICA(2, "Pessoa Juridica");
	
	private int cod;
	private String descricao;
	
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) 
			return null;
		for(TipoCliente i : TipoCliente.values()) {
			if(cod.equals(i.getCod())) {
				return i;
			}
		}
		throw new IllegalArgumentException("Id invalido : "+ cod);
	}
}

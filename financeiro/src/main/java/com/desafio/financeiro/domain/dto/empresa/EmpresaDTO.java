package com.desafio.financeiro.domain.dto.empresa;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "EmpresaDTO", description = "DTO com as informações da Empresa")
public class EmpresaDTO implements Serializable {

	private static final long serialVersionUID = -1687247701970959486L;
	
	private Long id;
	
	private String nome;
	
	private String cnpj;

	public EmpresaDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}

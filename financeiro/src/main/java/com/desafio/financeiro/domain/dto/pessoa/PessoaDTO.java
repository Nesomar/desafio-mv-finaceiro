package com.desafio.financeiro.domain.dto.pessoa;

import java.io.Serializable;
import java.time.LocalDate;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "PessoaDTO", description = "DTO com as informações da Pessoa")
public class PessoaDTO implements Serializable {

	private static final long serialVersionUID = 1522831005368184995L;
	
	private Long id;
	
	private String nome;
	
	private LocalDate dataNascimento;

	public PessoaDTO() {
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

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
}

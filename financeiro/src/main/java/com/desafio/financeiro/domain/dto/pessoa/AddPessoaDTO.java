package com.desafio.financeiro.domain.dto.pessoa;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "AddPessoaDTO", description = "DTO com as informações necessárias para realizar o cadastro.")
public class AddPessoaDTO implements Serializable {

	private static final long serialVersionUID = 582361038349473117L;

	@NotBlank(message = "Campo nome obrigatório.")
	@Size(max = 200)
	private String nome;
	
	@NotNull(message = "Campo data de nascimento obrigatório.")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataNascimento;

	public AddPessoaDTO() {
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

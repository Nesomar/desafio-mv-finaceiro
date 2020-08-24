package com.desafio.financeiro.domain.dto.conta;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "DTO para cadastrar uma conta", description = "DTO com as informações necessárias para cadastrar a Conta")
public class AddContaDTO implements Serializable {

	private static final long serialVersionUID = -4215701438358786081L;
	
	@NotBlank(message = "Campo Agência obrigatório.")
	@Size(max = 10)
	private String agencia;
	
	@NotBlank(message = "Campo Número da Conta obrigatório.")
	@Size(max = 10)
	private String numeroConta;
	
	@NotNull(message = "Campo Cliente obrigatório.")
	private Long idCliente;
	
	private Boolean ativo;

	public AddContaDTO() {
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
}

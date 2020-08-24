package com.desafio.financeiro.domain.dto.conta;

import java.io.Serializable;
import java.math.BigDecimal;

import com.desafio.financeiro.domain.dto.cliente.ClienteResumoDTO;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "DTO que representa a Conta", description = "DTO com as informações da Conta")
public class ContaDTO implements Serializable {

	private static final long serialVersionUID = 304115726616158997L;
	
	private Long id;
	
	private String agencia;
	
	private String numeroConta;
	
	private Boolean ativo;
	
	private BigDecimal saldoInicial;
	
	private ClienteResumoDTO cliente;
	
	public ContaDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public ClienteResumoDTO getCliente() {
		return cliente;
	}

	public void setCliente(ClienteResumoDTO cliente) {
		this.cliente = cliente;
	}
}

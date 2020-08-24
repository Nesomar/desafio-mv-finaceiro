package com.desafio.financeiro.domain.dto.conta;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 
 * @author nfreire
 *
 */
public class AlterarContaDTO implements Serializable {

	private static final long serialVersionUID = -2486943924989835453L;
	
	@NotBlank(message = "Campo Agência obrigatório.")
	@Size(max = 10)
	private String agencia;
	
	@NotBlank(message = "Campo Número da Conta obrigatório.")
	@Size(max = 10)
	private String numeroConta;
	
	private Boolean ativo;
	
	private Long idCliente;

	public AlterarContaDTO() {
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

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}
}

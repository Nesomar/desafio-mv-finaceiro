package com.desafio.financeiro.domain.dto.cliente;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

/**
 * 
 * @author nrfreire
 *
 */
@ApiModel(value = "AlterarClienteDTO", description = "DTO com as informações para alterar um Cliente")
public class AlterarClienteDTO implements Serializable {

	private static final long serialVersionUID = -8427438572244721815L;
	
	@NotBlank(message = "Campo Número Telefone obrigatório.")
	@Size(max = 20)
	private String numeroTelefone;
	
	@Email
	@NotBlank(message = "Campo e-mail obrigatório.")
	@Size(max = 100)
	private String email;

	public AlterarClienteDTO() {
	}

	public String getNumeroTelefone() {
		return numeroTelefone;
	}

	public void setNumeroTelefone(String numeroTelefone) {
		this.numeroTelefone = numeroTelefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}

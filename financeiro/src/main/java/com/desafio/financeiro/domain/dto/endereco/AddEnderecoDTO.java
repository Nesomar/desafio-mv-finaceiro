package com.desafio.financeiro.domain.dto.endereco;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Endereço DTO", description = "DTO com as infomações para cadastrar o Endereço")
public class AddEnderecoDTO implements Serializable {

	private static final long serialVersionUID = -8083683402088098672L;
	
	@NotBlank(message = "Campo CEP Telefone obrigatório.")
	@Size(max = 10)
	private String cep;
	
	@NotBlank(message = "Campo logradouro obrigatório.")
	@Size(max = 150)
	private String logradouro;
	
	@NotBlank(message = "Campo complemento obrigatório.")
	@Size(max = 150)
	private String complemento;
	
	@NotBlank(message = "Campo bairro obrigatório.")
	@Size(max = 150)
	private String bairro;
	
	@NotBlank(message = "Campo localidade obrigatório.")
	@Size(max = 150)
	private String localidade;
	
	@NotBlank(message = "Campo uf obrigatório.")
	@Size(max = 2)
	private String uf;
	
	@NotBlank(message = "Campo Número obrigatório.")
	@Size(max = 10)
	private String numero;

	public AddEnderecoDTO() {
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}
}

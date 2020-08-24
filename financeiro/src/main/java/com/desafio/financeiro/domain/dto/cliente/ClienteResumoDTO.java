package com.desafio.financeiro.domain.dto.cliente;

import java.io.Serializable;

import com.desafio.financeiro.domain.enums.TipoClienteEnum;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "ClienteResumoDTO", description = "DTO com as informações resumidas do Cliente")
public class ClienteResumoDTO implements Serializable {

	private static final long serialVersionUID = 8004277440196174970L;

	private Long id;
	
	private TipoClienteEnum tipoCliente;
	
	private String numeroTelefone;
	
	private String email;
	
	private Boolean ativo;
	
	private String dataCadastro;

	public ClienteResumoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoClienteEnum getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(TipoClienteEnum tipoCliente) {
		this.tipoCliente = tipoCliente;
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

	public Boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
}

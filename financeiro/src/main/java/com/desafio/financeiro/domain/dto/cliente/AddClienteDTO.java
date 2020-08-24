package com.desafio.financeiro.domain.dto.cliente;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.desafio.financeiro.domain.annotation.EnumValues;
import com.desafio.financeiro.domain.dto.conta.AddContaDTO;
import com.desafio.financeiro.domain.dto.movimentacao.AddMovimentacaoDTO;
import com.desafio.financeiro.domain.dto.pessoa.AddPessoaDTO;
import com.desafio.financeiro.domain.enums.TipoClienteEnum;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Cliente DTO", description = "DTO com as informações para Cadastrar um Cliente")
public class AddClienteDTO implements Serializable{

	private static final long serialVersionUID = 8063846933232796987L;
	
	@EnumValues(enumClass = TipoClienteEnum.class, message = "Tipo Cliente deve ser PF/PJ.")
	private String tipoCliente;
	
	@NotBlank(message = "Campo Número Telefone obrigatório.")
	@Size(max = 20)
	private String numeroTelefone;
	
	@Email
	@NotBlank(message = "Campo e-mail obrigatório.")
	@Size(max = 100)
	private String email;
	
	@Valid
	private AddPessoaDTO pessoa;
	
	@NotNull(message = "Campo Empresa obrigatório.")
	private Long idEmpresa;
	
	@NotNull(message = "Campo Endereço obrigatório.")
	private Long idEndereco;
	
	private AddContaDTO conta;
	
	private AddMovimentacaoDTO movimentacao;

	public AddClienteDTO() {
	}

	public String getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(String tipoCliente) {
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

	public AddPessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(AddPessoaDTO pessoa) {
		this.pessoa = pessoa;
	}

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Long getIdEndereco() {
		return idEndereco;
	}

	public void setIdEndereco(Long idEndereco) {
		this.idEndereco = idEndereco;
	}

	public AddContaDTO getConta() {
		return conta;
	}

	public void setConta(AddContaDTO conta) {
		this.conta = conta;
	}

	public AddMovimentacaoDTO getMovimentacao() {
		return movimentacao;
	}

	public void setMovimentacao(AddMovimentacaoDTO movimentacao) {
		this.movimentacao = movimentacao;
	}

}

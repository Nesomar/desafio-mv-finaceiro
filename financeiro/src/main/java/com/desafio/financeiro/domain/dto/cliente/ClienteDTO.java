package com.desafio.financeiro.domain.dto.cliente;

import java.io.Serializable;
import java.time.LocalDate;

import com.desafio.financeiro.domain.dto.empresa.EmpresaDTO;
import com.desafio.financeiro.domain.dto.endereco.EnderecoDTO;
import com.desafio.financeiro.domain.dto.pessoa.PessoaDTO;
import com.desafio.financeiro.domain.enums.TipoClienteEnum;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "Cliente DTO", description = "DTO com as informações do Cliente")
public class ClienteDTO implements Serializable {

	private static final long serialVersionUID = -1908101212937155888L;
	
	private Long id;
	
	private TipoClienteEnum tipoCliente;
	
	private String numeroTelefone;
	
	private String email;
	
	private Boolean ativo;
	
	private LocalDate dataCadastro;
	
	private PessoaDTO pessoa;
	
	private EmpresaDTO empresa;
	
	private EnderecoDTO endereco;

	public ClienteDTO() {
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

	public LocalDate getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public PessoaDTO getPessoa() {
		return pessoa;
	}

	public void setPessoa(PessoaDTO pessoa) {
		this.pessoa = pessoa;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public EnderecoDTO getEndereco() {
		return endereco;
	}

	public void setEndereco(EnderecoDTO endereco) {
		this.endereco = endereco;
	}
}

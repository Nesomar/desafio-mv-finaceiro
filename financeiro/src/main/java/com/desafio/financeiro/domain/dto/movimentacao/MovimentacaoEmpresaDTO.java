package com.desafio.financeiro.domain.dto.movimentacao;

import java.io.Serializable;

/**
 * 
 * @author nrfreire
 *
 */
public class MovimentacaoEmpresaDTO implements Serializable {

	private static final long serialVersionUID = -5221347670215551999L;
	
	private String cnpj;
	
	private String nomeEmpresa;
	
	private String nomeCliente;
	
	private Long totalMovimentacao;

	public MovimentacaoEmpresaDTO() {
	}

	public MovimentacaoEmpresaDTO(String cnpj, String nomeEmpresa, String nomeCliente, Long totalMovimentacao) {
		super();
		this.cnpj = cnpj;
		this.nomeEmpresa = nomeEmpresa;
		this.nomeCliente = nomeCliente;
		this.totalMovimentacao = totalMovimentacao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNomeEmpresa() {
		return nomeEmpresa;
	}

	public void setNomeEmpresa(String nomeEmpresa) {
		this.nomeEmpresa = nomeEmpresa;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}

	public Long getTotalMovimentacao() {
		return totalMovimentacao;
	}

	public void setTotalMovimentacao(Long totalMovimentacao) {
		this.totalMovimentacao = totalMovimentacao;
	}
}

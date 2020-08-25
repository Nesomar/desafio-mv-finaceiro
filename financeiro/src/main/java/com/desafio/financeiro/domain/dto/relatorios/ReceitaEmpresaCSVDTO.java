package com.desafio.financeiro.domain.dto.relatorios;

import java.io.Serializable;
import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * 
 * @author nrfreirre
 *
 */
public class ReceitaEmpresaCSVDTO implements Serializable {

	private static final long serialVersionUID = -8276051284228608660L;
	
	@CsvBindByName(column = "CNPJ")
	@CsvBindByPosition(position = 0)
	private String cnpj;
	
	@CsvBindByName(column = "Empresa")
	@CsvBindByPosition(position = 1)
	private String nomeEmpresa;
	
	@CsvBindByName(column = "Periodo")
	@CsvBindByPosition(position = 2)
	private String periodo;
	
	@CsvBindByName(column = "Cliente")
	@CsvBindByPosition(position = 3)
	private String nome;
	
	@CsvBindByName(column = "Quantidade de movimentacoes")
	@CsvBindByPosition(position = 4)
	private Long totalMovimentacoes;
	
	@CsvBindByName(column = "Valor das Movimentacoes")
	@CsvBindByPosition(position = 5)
	private BigDecimal saldo;

	private ReceitaEmpresaCSVDTO(Builder builder) {
		this.cnpj = builder.cnpj;
		this.nomeEmpresa = builder.nomeEmpresa;
		this.periodo = builder.periodo;
		this.nome = builder.nome;
		this.totalMovimentacoes = builder.totalMovimentacoes;
		this.saldo = builder.saldo;
	}

	public ReceitaEmpresaCSVDTO() {
	}

	public ReceitaEmpresaCSVDTO(String cnpj, String nomeEmpresa, String periodo, String nome, Long totalMovimentacoes,
			BigDecimal saldo) {
		super();
		this.cnpj = cnpj;
		this.nomeEmpresa = nomeEmpresa;
		this.periodo = periodo;
		this.nome = nome;
		this.totalMovimentacoes = totalMovimentacoes;
		this.saldo = saldo;
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

	public String getPeriodo() {
		return periodo;
	}

	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getTotalMovimentacoes() {
		return totalMovimentacoes;
	}

	public void setTotalMovimentacoes(Long totalMovimentacoes) {
		this.totalMovimentacoes = totalMovimentacoes;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}

	/**
	 * Creates builder to build {@link ReceitaEmpresaCSVDTO}.
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link ReceitaEmpresaCSVDTO}.
	 */
	public static final class Builder {
		private String cnpj;
		private String nomeEmpresa;
		private String periodo;
		private String nome;
		private Long totalMovimentacoes;
		private BigDecimal saldo;

		private Builder() {
		}

		public Builder withCnpj(String cnpj) {
			this.cnpj = cnpj;
			return this;
		}

		public Builder withNomeEmpresa(String nomeEmpresa) {
			this.nomeEmpresa = nomeEmpresa;
			return this;
		}

		public Builder withPeriodo(String periodo) {
			this.periodo = periodo;
			return this;
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withTotalMovimentacoes(Long totalMovimentacoes) {
			this.totalMovimentacoes = totalMovimentacoes;
			return this;
		}

		public Builder withSaldo(BigDecimal saldo) {
			this.saldo = saldo;
			return this;
		}

		public ReceitaEmpresaCSVDTO build() {
			return new ReceitaEmpresaCSVDTO(this);
		}
	}
}

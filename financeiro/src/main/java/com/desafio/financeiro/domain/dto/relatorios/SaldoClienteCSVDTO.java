package com.desafio.financeiro.domain.dto.relatorios;

import java.io.Serializable;
import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvBindByPosition;

/**
 * 
 * @author nrfreire
 *
 */
public class SaldoClienteCSVDTO implements Serializable {

	private static final long serialVersionUID = 2326616284745144115L;
	
	@CsvBindByName(column = "Cliente")
	@CsvBindByPosition(position = 0)
	private String nome;
	
	@CsvBindByName(column = "Cliente desde")
	@CsvBindByPosition(position = 1)
	private String dataCadastro;
	
	@CsvBindByName(column = "Endereco")
	@CsvBindByPosition(position = 2)
	private String endereco;
	
	@CsvBindByName(column = "Movimentacoes de credito")
	@CsvBindByPosition(position = 3)
	private Long movimentacaoCredito;
	
	@CsvBindByName(column = "Movimentacoes de debito")
	@CsvBindByPosition(position = 4)
	private Long movimentacaoDebito;
	
	@CsvBindByName(column = "Total de movimentacoes")
	@CsvBindByPosition(position = 5)
	private Long totalMovimentacao;
	
	@CsvBindByName(column = "Valor pago pelas movimentacoes")
	@CsvBindByPosition(position = 6)
	private BigDecimal valorPago;
	
	@CsvBindByName(column = "Saldo inicial")
	@CsvBindByPosition(position = 7)
	private BigDecimal saldoInicial;
	
	@CsvBindByName(column = "Saldo Atual")
	@CsvBindByPosition(position = 8)
	private BigDecimal saldoAtual;

	private SaldoClienteCSVDTO(Builder builder) {
		this.nome = builder.nome;
		this.dataCadastro = builder.dataCadastro;
		this.endereco = builder.endereco;
		this.movimentacaoCredito = builder.movimentacaoCredito;
		this.movimentacaoDebito = builder.movimentacaoDebito;
		this.totalMovimentacao = builder.totalMovimentacao;
		this.valorPago = builder.valorPago;
		this.saldoInicial = builder.saldoInicial;
		this.saldoAtual = builder.saldoAtual;
	}

	public SaldoClienteCSVDTO() {
	}

	public SaldoClienteCSVDTO(String nome, String dataCadastro, String endereco, Long movimentacaoCredito,
			Long movimentacaoDebito, Long totalMovimentacao, BigDecimal valorPago, BigDecimal saldoInicial,
			BigDecimal saldoAtual) {
		super();
		this.nome = nome;
		this.dataCadastro = dataCadastro;
		this.endereco = endereco;
		this.movimentacaoCredito = movimentacaoCredito;
		this.movimentacaoDebito = movimentacaoDebito;
		this.totalMovimentacao = totalMovimentacao;
		this.valorPago = valorPago;
		this.saldoInicial = saldoInicial;
		this.saldoAtual = saldoAtual;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public Long getMovimentacaoCredito() {
		return movimentacaoCredito;
	}

	public void setMovimentacaoCredito(Long movimentacaoCredito) {
		this.movimentacaoCredito = movimentacaoCredito;
	}

	public Long getMovimentacaoDebito() {
		return movimentacaoDebito;
	}

	public void setMovimentacaoDebito(Long movimentacaoDebito) {
		this.movimentacaoDebito = movimentacaoDebito;
	}

	public Long getTotalMovimentacao() {
		return totalMovimentacao;
	}

	public void setTotalMovimentacao(Long totalMovimentacao) {
		this.totalMovimentacao = totalMovimentacao;
	}

	public BigDecimal getValorPago() {
		return valorPago;
	}

	public void setValorPago(BigDecimal valorPago) {
		this.valorPago = valorPago;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	/**
	 * Creates builder to build {@link SaldoClienteCSVDTO}.
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link SaldoClienteCSVDTO}.
	 */
	public static final class Builder {
		private String nome;
		private String dataCadastro;
		private String endereco;
		private Long movimentacaoCredito;
		private Long movimentacaoDebito;
		private Long totalMovimentacao;
		private BigDecimal valorPago;
		private BigDecimal saldoInicial;
		private BigDecimal saldoAtual;

		private Builder() {
		}

		public Builder withNome(String nome) {
			this.nome = nome;
			return this;
		}

		public Builder withDataCadastro(String dataCadastro) {
			this.dataCadastro = dataCadastro;
			return this;
		}

		public Builder withEndereco(String endereco) {
			this.endereco = endereco;
			return this;
		}

		public Builder withMovimentacaoCredito(Long movimentacaoCredito) {
			this.movimentacaoCredito = movimentacaoCredito;
			return this;
		}

		public Builder withMovimentacaoDebito(Long movimentacaoDebito) {
			this.movimentacaoDebito = movimentacaoDebito;
			return this;
		}

		public Builder withTotalMovimentacao(Long totalMovimentacao) {
			this.totalMovimentacao = totalMovimentacao;
			return this;
		}

		public Builder withValorPago(BigDecimal valorPago) {
			this.valorPago = valorPago;
			return this;
		}

		public Builder withSaldoInicial(BigDecimal saldoInicial) {
			this.saldoInicial = saldoInicial;
			return this;
		}

		public Builder withSaldoAtual(BigDecimal saldoAtual) {
			this.saldoAtual = saldoAtual;
			return this;
		}

		public SaldoClienteCSVDTO build() {
			return new SaldoClienteCSVDTO(this);
		}
	}
}

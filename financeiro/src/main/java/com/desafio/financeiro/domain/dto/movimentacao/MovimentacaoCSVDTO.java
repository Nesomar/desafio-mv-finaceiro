package com.desafio.financeiro.domain.dto.movimentacao;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author nrfreire
 *
 */
public class MovimentacaoCSVDTO implements Serializable {

	private static final long serialVersionUID = 4134876628856461295L;
	
	private Long totalMovimentacaoCredito;
	
	private Long totalMovimentacaoDebito;
	
	private Long totalMovimentacao;
	
	private BigDecimal valorPago;
	
	private BigDecimal saldoAtual;
	
	private BigDecimal saldoInicial;

	private MovimentacaoCSVDTO(Builder builder) {
		this.totalMovimentacaoCredito = builder.totalMovimentacaoCredito;
		this.totalMovimentacaoDebito = builder.totalMovimentacaoDebito;
		this.totalMovimentacao = builder.totalMovimentacao;
		this.valorPago = builder.valorPago;
		this.saldoAtual = builder.saldoAtual;
		this.saldoInicial = builder.saldoInicial;
	}

	public MovimentacaoCSVDTO() {
	}

	public MovimentacaoCSVDTO(Long totalMovimentacaoCredito, Long totalMovimentacaoDebito,
			Long totalMovimentacao, BigDecimal valorPago, BigDecimal saldoAtual, BigDecimal saldoInicial) {
		super();
		this.totalMovimentacaoCredito = totalMovimentacaoCredito;
		this.totalMovimentacaoDebito = totalMovimentacaoDebito;
		this.totalMovimentacao = totalMovimentacao;
		this.valorPago = valorPago;
		this.saldoAtual = saldoAtual;
		this.saldoInicial = saldoInicial;
	}

	public Long getTotalMovimentacaoCredito() {
		return totalMovimentacaoCredito;
	}

	public void setTotalMovimentacaoCredito(Long totalMovimentacaoCredito) {
		this.totalMovimentacaoCredito = totalMovimentacaoCredito;
	}

	public Long getTotalMovimentacaoDebito() {
		return totalMovimentacaoDebito;
	}

	public void setTotalMovimentacaoDebito(Long totalMovimentacaoDebito) {
		this.totalMovimentacaoDebito = totalMovimentacaoDebito;
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

	public BigDecimal getSaldoAtual() {
		return saldoAtual;
	}

	public void setSaldoAtual(BigDecimal saldoAtual) {
		this.saldoAtual = saldoAtual;
	}

	public BigDecimal getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(BigDecimal saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	/**
	 * Creates builder to build {@link MovimentacaoCSVDTO}.
	 * @return created builder
	 */
	public static Builder builder() {
		return new Builder();
	}

	/**
	 * Builder to build {@link MovimentacaoCSVDTO}.
	 */
	public static final class Builder {
		private Long totalMovimentacaoCredito;
		private Long totalMovimentacaoDebito;
		private Long totalMovimentacao;
		private BigDecimal valorPago;
		private BigDecimal saldoAtual;
		private BigDecimal saldoInicial;

		private Builder() {
		}

		public Builder withTotalMovimentacaoCredito(Long totalMovimentacaoCredito) {
			this.totalMovimentacaoCredito = totalMovimentacaoCredito;
			return this;
		}

		public Builder withTotalMovimentacaoDebito(Long totalMovimentacaoDebito) {
			this.totalMovimentacaoDebito = totalMovimentacaoDebito;
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

		public Builder withSaldoAtual(BigDecimal saldoAtual) {
			this.saldoAtual = saldoAtual;
			return this;
		}

		public Builder withSaldoInicial(BigDecimal saldoInicial) {
			this.saldoInicial = saldoInicial;
			return this;
		}

		public MovimentacaoCSVDTO build() {
			return new MovimentacaoCSVDTO(this);
		}
	}
}

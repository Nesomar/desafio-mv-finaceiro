package com.desafio.financeiro.domain.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.desafio.financeiro.domain.enums.TipoMovimentacaoEnum;

@Entity
@Table(name = "MOVIMENTACAO")
@SequenceGenerator(name = "SEQ_MOVIMENTACAO", sequenceName = "SEQ_MOVIMENTACAO", allocationSize = 1, initialValue = 1)
public class Movimentacao implements Serializable {

	private static final long serialVersionUID = -8687005769303546584L;
	
	@Id
	@Column(name = "ID_MOVIMENTACAO")
	@GeneratedValue(generator = "SEQ_MOVIMENTACAO", strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotNull
	@Column(name = "TIPO_MOVIMENTACAO")
	private TipoMovimentacaoEnum tipoMovimentacao;
	
	@NotNull
	@Column(name = "DATA_MOVIMENTACAO")
	private LocalDate dataMovimentacao;
	
	@NotNull
	@Column(name = "VALOR_MOVIMENTACAO")
	private BigDecimal valorMovimentacao;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_CONTA", foreignKey = @ForeignKey(name = "FK_CONTA_MOVIMENTACAO"))
	private Conta conta;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "ID_EMPRESA", foreignKey = @ForeignKey(name = "FK_CONTA_EMPRESA"))
	private Empresa empresa;
	
	@Column(name = "MOVIMENTACAO_INICIAL")
	private Boolean movimentacaoInicial;

	public TipoMovimentacaoEnum getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(TipoMovimentacaoEnum tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public LocalDate getDataMovimentacao() {
		return dataMovimentacao;
	}

	public void setDataMovimentacao(LocalDate dataMovimentacao) {
		this.dataMovimentacao = dataMovimentacao;
	}

	public BigDecimal getValorMovimentacao() {
		return valorMovimentacao;
	}

	public void setValorMovimentacao(BigDecimal valorMovimentacao) {
		this.valorMovimentacao = valorMovimentacao;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Long getId() {
		return id;
	}

	public Boolean getMovimentacaoInicial() {
		return movimentacaoInicial;
	}

	public void setMovimentacaoInicial(Boolean movimentacaoInicial) {
		this.movimentacaoInicial = movimentacaoInicial;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((conta == null) ? 0 : conta.hashCode());
		result = prime * result + ((empresa == null) ? 0 : empresa.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movimentacao other = (Movimentacao) obj;
		if (conta == null) {
			if (other.conta != null)
				return false;
		} else if (!conta.equals(other.conta))
			return false;
		if (empresa == null) {
			if (other.empresa != null)
				return false;
		} else if (!empresa.equals(other.empresa))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}

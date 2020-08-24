package com.desafio.financeiro.domain.dto.movimentacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import com.desafio.financeiro.domain.dto.conta.ContaDTO;
import com.desafio.financeiro.domain.dto.empresa.EmpresaDTO;
import com.desafio.financeiro.domain.enums.TipoMovimentacaoEnum;

import io.swagger.annotations.ApiModel;

@ApiModel(value = "MovimentaçãoDTO", description = "DTO com as informações das Movimentações")
public class MovimentacaoDTO implements Serializable {

	private static final long serialVersionUID = -9070464665690086030L;

	private Long id;
	
	private TipoMovimentacaoEnum tipoMovimentacao;
	
	private LocalDate dataMovimentacao;
	
	private BigDecimal valorMovimentacao;
	
	private ContaDTO conta;
	
	private EmpresaDTO empresa;
	
	private Boolean movimentacaoInicial;

	public MovimentacaoDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public ContaDTO getConta() {
		return conta;
	}

	public void setConta(ContaDTO conta) {
		this.conta = conta;
	}

	public EmpresaDTO getEmpresa() {
		return empresa;
	}

	public void setEmpresa(EmpresaDTO empresa) {
		this.empresa = empresa;
	}

	public Boolean getMovimentacaoInicial() {
		return movimentacaoInicial;
	}

	public void setMovimentacaoInicial(Boolean movimentacaoInicial) {
		this.movimentacaoInicial = movimentacaoInicial;
	}
}	

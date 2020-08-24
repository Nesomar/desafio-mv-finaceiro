package com.desafio.financeiro.domain.dto.movimentacao;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.desafio.financeiro.domain.annotation.EnumValues;
import com.desafio.financeiro.domain.enums.TipoMovimentacaoEnum;

/**
 * 
 * @author nesom
 *
 */
public class MovimentacaoInicialDTO implements Serializable {

	private static final long serialVersionUID = 3544883944665045547L;

	@EnumValues(enumClass = TipoMovimentacaoEnum.class, message = "Opção não encontrada no TipoMovimentacaoEnum")
	private String tipoMovimentacao;

	@NotNull(message = "Campo data Movimentação obrigatório.")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataMovimentacao;

	@NotNull(message = "Campo Valor Movimentação obrigatório.")
	private BigDecimal valorMovimentacao;

	@NotNull(message = "Campo Empresa obrigatório.")
	private Long idEmpresa;
	
	@NotNull(message = "Campo Movimentacao Inicial obrigatório.")
	private Boolean movimentacaoInicial;

	public MovimentacaoInicialDTO() {
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
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

	public Long getIdEmpresa() {
		return idEmpresa;
	}

	public void setIdEmpresa(Long idEmpresa) {
		this.idEmpresa = idEmpresa;
	}

	public Boolean getMovimentacaoInicial() {
		return movimentacaoInicial;
	}

	public void setMovimentacaoInicial(Boolean movimentacaoInicial) {
		this.movimentacaoInicial = movimentacaoInicial;
	}
}

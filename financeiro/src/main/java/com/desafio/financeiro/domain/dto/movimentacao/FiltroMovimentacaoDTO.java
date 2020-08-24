package com.desafio.financeiro.domain.dto.movimentacao;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.desafio.financeiro.domain.annotation.EnumValues;
import com.desafio.financeiro.domain.enums.TipoMovimentacaoEnum;

import io.swagger.annotations.ApiModel;

/**
 * 
 * @author nrfreire
 *
 */
@ApiModel(value = "Filtros Movimentação DTO", description = "DTO com os campos possíveis para filtrar as movimentações")
public class FiltroMovimentacaoDTO implements Serializable {

	private static final long serialVersionUID = 4776138031091071237L;
	
	@EnumValues(enumClass = TipoMovimentacaoEnum.class, acceptNull = true)
	private String tipoMovimentacao;

	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataMovimentacao;
	
	private String numeroConta;
	
	private String cnpj;
	
	private Integer pagina;
	
	private Integer tamanhoPagina;

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

	public String getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(String numeroConta) {
		this.numeroConta = numeroConta;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Integer getPagina() {
		return pagina;
	}

	public void setPagina(Integer pagina) {
		this.pagina = pagina;
	}

	public Integer getTamanhoPagina() {
		return tamanhoPagina;
	}

	public void setTamanhoPagina(Integer tamanhoPagina) {
		this.tamanhoPagina = tamanhoPagina;
	}
}

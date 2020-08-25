package com.desafio.financeiro.domain.dto.movimentacao;

import java.io.Serializable;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.desafio.financeiro.domain.annotation.EnumValues;
import com.desafio.financeiro.domain.enums.TipoMovimentacaoEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author nrfreire
 *
 */
@ApiModel(value = "FiltroMovimentacaoDTO", description = "DTO com os campos possíveis para filtrar as movimentações")
public class FiltroMovimentacaoDTO implements Serializable {

	private static final long serialVersionUID = 5778699355652639404L;

	@EnumValues(enumClass = TipoMovimentacaoEnum.class, acceptNull = true)
	private String tipoMovimentacao;

	@ApiModelProperty(example = "2020-08-24")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataInicial;
	
	@ApiModelProperty(example = "2020-08-24")
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate dataFinal;
	
	private String numeroConta;
	
	private String cnpj;
	
	private Integer pagina;
	
	private Integer tamanhoPagina;

	public FiltroMovimentacaoDTO() {
	}

	public String getTipoMovimentacao() {
		return tipoMovimentacao;
	}

	public void setTipoMovimentacao(String tipoMovimentacao) {
		this.tipoMovimentacao = tipoMovimentacao;
	}

	public LocalDate getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(LocalDate dataInicial) {
		this.dataInicial = dataInicial;
	}

	public LocalDate getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(LocalDate dataFinal) {
		this.dataFinal = dataFinal;
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

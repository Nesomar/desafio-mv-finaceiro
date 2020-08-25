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
public class SaltoTodosClientesCSVDTO implements Serializable {

	private static final long serialVersionUID = -8276051284228608660L;

	@CsvBindByName(column = "Cliente")
	@CsvBindByPosition(position = 0)
	private String nome;
	
	@CsvBindByName(column = "Cliente desde")
	@CsvBindByPosition(position = 1)
	private String dataCadastro;
	
	
	@CsvBindByName(column = "Data Consulta")
	@CsvBindByPosition(position = 2)
	private String dataConsulta;
	
	@CsvBindByName(column = "Saldo")
	@CsvBindByPosition(position = 3)
	private BigDecimal saldo;

	public SaltoTodosClientesCSVDTO() {
	}

	public SaltoTodosClientesCSVDTO(String nome, String dataCadastro, String dataConsulta, BigDecimal saldo) {
		super();
		this.nome = nome;
		this.dataCadastro = dataCadastro;
		this.dataConsulta = dataConsulta;
		this.saldo = saldo;
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

	public String getDataConsulta() {
		return dataConsulta;
	}

	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	public BigDecimal getSaldo() {
		return saldo;
	}

	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}
}

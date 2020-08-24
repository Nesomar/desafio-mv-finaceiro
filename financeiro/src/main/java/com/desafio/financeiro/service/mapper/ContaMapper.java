package com.desafio.financeiro.service.mapper;

import java.math.BigDecimal;

import com.desafio.financeiro.domain.dto.conta.AddContaDTO;
import com.desafio.financeiro.domain.dto.conta.AlterarContaDTO;
import com.desafio.financeiro.domain.dto.conta.ContaDTO;
import com.desafio.financeiro.domain.entity.Cliente;
import com.desafio.financeiro.domain.entity.Conta;

/**
 * 
 * @author nrfreire
 *
 */
public class ContaMapper {

	private ContaMapper() {
		
	}
	
	public static ContaDTO mapper(Conta conta) {
		
		ContaDTO contaDTO = new ContaDTO();
		
		contaDTO.setAgencia(conta.getAgencia());
		contaDTO.setAtivo(conta.getAtivo());
		contaDTO.setId(conta.getId());
		contaDTO.setNumeroConta(conta.getNumeroConta());
		contaDTO.setSaldoInicial(conta.getSaldoInicial());
		
		return contaDTO;
	}

	public static Conta mapper(Cliente cliente, AddContaDTO contaDTO) {
		
		Conta conta = new Conta();
		
		conta.setAgencia(contaDTO.getAgencia());
		conta.setAtivo(Boolean.TRUE);
		conta.setCliente(cliente);
		conta.setNumeroConta(contaDTO.getNumeroConta());
		conta.setSaldoInicial(BigDecimal.ZERO);
		
		return conta;
	}

	public static Conta mapper(Cliente cliente, Conta conta, AlterarContaDTO contaDTO) {
		
		conta.setAgencia(contaDTO.getAgencia());
		conta.setAtivo(contaDTO.getAtivo());
		conta.setCliente(cliente);
		conta.setNumeroConta(contaDTO.getNumeroConta());
		conta.setSaldoInicial(BigDecimal.ZERO);
		
		return conta;
	}

}

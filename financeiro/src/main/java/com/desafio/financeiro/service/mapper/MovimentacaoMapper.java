package com.desafio.financeiro.service.mapper;

import java.time.format.DateTimeFormatter;

import com.desafio.financeiro.domain.dto.cliente.ClienteResumoDTO;
import com.desafio.financeiro.domain.dto.conta.ContaDTO;
import com.desafio.financeiro.domain.dto.empresa.EmpresaDTO;
import com.desafio.financeiro.domain.dto.movimentacao.AddMovimentacaoDTO;
import com.desafio.financeiro.domain.dto.movimentacao.MovimentacaoDTO;
import com.desafio.financeiro.domain.dto.movimentacao.MovimentacaoInicialDTO;
import com.desafio.financeiro.domain.entity.Cliente;
import com.desafio.financeiro.domain.entity.Conta;
import com.desafio.financeiro.domain.entity.Empresa;
import com.desafio.financeiro.domain.entity.Movimentacao;
import com.desafio.financeiro.domain.enums.TipoMovimentacaoEnum;

public class MovimentacaoMapper {

	private static final String DD_MM_YYYY = "dd/MM/YYYY";
	
	private MovimentacaoMapper () {
		
	}

	public static MovimentacaoDTO mapper(Movimentacao movimentacao) {
		
		MovimentacaoDTO movimentacaoDTO = new MovimentacaoDTO();
		
		movimentacaoDTO.setDataMovimentacao(movimentacao.getDataMovimentacao().format(DateTimeFormatter.ofPattern(DD_MM_YYYY)));
		movimentacaoDTO.setId(movimentacao.getId());
		movimentacaoDTO.setMovimentacaoInicial(movimentacao.getMovimentacaoInicial());
		movimentacaoDTO.setTipoMovimentacao(movimentacao.getTipoMovimentacao());
		movimentacaoDTO.setValorMovimentacao(movimentacao.getValorMovimentacao());
		movimentacaoDTO.setConta(recuperarContaDTO(movimentacao.getConta()));
		movimentacaoDTO.setEmpresa(recuperarEmpresaDTO(movimentacao.getEmpresa()));
		
		return movimentacaoDTO;
	}

	private static EmpresaDTO recuperarEmpresaDTO(Empresa empresa) {
	
		EmpresaDTO empresaDTO = new EmpresaDTO();
		
		empresaDTO.setCnpj(empresa.getCnpj());
		empresaDTO.setId(empresa.getId());
		empresaDTO.setNome(empresa.getNome());
		
		return empresaDTO;
	}

	private static ContaDTO recuperarContaDTO(Conta conta) {
		
		ContaDTO contaDTO = new ContaDTO();
		
		contaDTO.setAgencia(conta.getAgencia());
		contaDTO.setAtivo(conta.getAtivo());
		contaDTO.setId(conta.getId());
		contaDTO.setNumeroConta(conta.getNumeroConta());
		contaDTO.setSaldoInicial(conta.getSaldoInicial());
		contaDTO.setCliente(recuperarClienteDTO(conta.getCliente()));
		
		return contaDTO;
	}

	private static ClienteResumoDTO recuperarClienteDTO(Cliente cliente) {
		
		ClienteResumoDTO clienteResumoDTO = new ClienteResumoDTO();
		
		clienteResumoDTO.setAtivo(cliente.getAtivo());
		clienteResumoDTO.setDataCadastro(cliente.getDataCadastro().format(DateTimeFormatter.ofPattern(DD_MM_YYYY)));
		clienteResumoDTO.setEmail(cliente.getEmail());
		clienteResumoDTO.setId(cliente.getId());
		clienteResumoDTO.setNumeroTelefone(cliente.getNumeroTelefone());
		
		return clienteResumoDTO;
	}

	public static Movimentacao mapper(MovimentacaoInicialDTO movimentacaoDTO, Empresa empresa, Conta conta) {
	
		Movimentacao movimentacao = new Movimentacao();
		
		movimentacao.setConta(conta);
		movimentacao.setDataMovimentacao(movimentacaoDTO.getDataMovimentacao());
		movimentacao.setEmpresa(empresa);
		movimentacao.setMovimentacaoInicial(movimentacaoDTO.getMovimentacaoInicial());
		movimentacao.setTipoMovimentacao(TipoMovimentacaoEnum.valueOf(movimentacaoDTO.getTipoMovimentacao()));
		movimentacao.setValorMovimentacao(movimentacaoDTO.getValorMovimentacao());
		
		return movimentacao;
	}

	public static Movimentacao mapper(AddMovimentacaoDTO movimentacaoDTO, Empresa empresa, Conta conta) {
		
		Movimentacao movimentacao = new Movimentacao();
		
		movimentacao.setConta(conta);
		movimentacao.setDataMovimentacao(movimentacaoDTO.getDataMovimentacao());
		movimentacao.setEmpresa(empresa);
		movimentacao.setMovimentacaoInicial(movimentacaoDTO.getMovimentacaoInicial());
		movimentacao.setTipoMovimentacao(TipoMovimentacaoEnum.valueOf(movimentacaoDTO.getTipoMovimentacao()));
		movimentacao.setValorMovimentacao(movimentacaoDTO.getValorMovimentacao());
		
		return movimentacao;
	}
}

package com.desafio.financeiro.service;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.financeiro.domain.dto.conta.AddContaDTO;
import com.desafio.financeiro.domain.dto.conta.AlterarContaDTO;
import com.desafio.financeiro.domain.dto.conta.ContaDTO;
import com.desafio.financeiro.domain.entity.Cliente;
import com.desafio.financeiro.domain.entity.Conta;
import com.desafio.financeiro.domain.repository.ContaRepository;
import com.desafio.financeiro.service.exception.NegocioException;
import com.desafio.financeiro.service.mapper.ContaMapper;

@Service
public class ContaService implements Serializable {

	private static final long serialVersionUID = -3537111078535294643L;
	
	private static final String ACAO_NAO_PODE_SER_REALIZADA_EXISTEM_MOVIMENTACOES_NA_CONTA = "Ação não pode ser realizada, existem movimentações na conta.";
	private static final String CONTA_NAO_ENCONTRADA = "Conta não Encontrada";
	private static final String CLIENTE_NAO_ENCONTRADO = "Cliente não Encontrada";
	
	@Autowired
	private ContaRepository repository;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private MovimentacaoService movimentacaoService;
	
	/**
	 * 
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 */
	public Page<ContaDTO> listarTodos(Integer pagina, Integer tamanhoPagina) {
		
		Pageable pageable = PageRequest.of(pagina, tamanhoPagina);

		Page<Conta> page = repository.findAll(pageable);

		return page.map(ContaMapper::mapper);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<ContaDTO> consultarPorId(Long id) {
		
		Optional<Conta> optional = repository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(ContaMapper.mapper(optional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param contaDTO
	 * @return
	 */
	@Transactional
	public ResponseEntity<ContaDTO> alterar(Long id, @Valid AlterarContaDTO contaDTO) {
		
		if (CollectionUtils.isNotEmpty(movimentacaoService.consultarPorIdConta(id))) {
			throw new NegocioException(ACAO_NAO_PODE_SER_REALIZADA_EXISTEM_MOVIMENTACOES_NA_CONTA);
		}
		
		Cliente cliente = clienteService.consultarClientePorId(contaDTO.getIdCliente())
				.orElseThrow(() -> new NegocioException(CLIENTE_NAO_ENCONTRADO));

		Conta conta = repository.findById(id).orElseThrow(() -> new NegocioException(CONTA_NAO_ENCONTRADA));

		return ResponseEntity
				.ok(ContaMapper.mapper(repository.save(ContaMapper.mapper(cliente, conta, contaDTO))));
	}
	
	/**
	 * 
	 * @param id
	 */
	@Transactional
	public void inativar(Long id) {
		
		Conta conta = repository.findById(id).orElseThrow(() -> new NegocioException(CONTA_NAO_ENCONTRADA));
		
		conta.setAtivo(Boolean.FALSE);
		
		repository.save(conta);
	}
	
	/**
	 * 
	 * @param idConta
	 * @return
	 */
	public Optional<Conta> buscarPorId(Long idConta) {
		return repository.findById(idConta);
	}
	
	/**
	 * 
	 * @param cliente
	 * @param contaDTO
	 * @return
	 */
	@Transactional
	public Conta cadastrar(Cliente cliente, AddContaDTO contaDTO) {
		return repository.save(ContaMapper.mapper(cliente, contaDTO));
	}
	
	/**
	 * 
	 * @param conta
	 */
	public void atualizarSaldo(Conta conta) {
		
		try {
			repository.save(conta);
		} catch (IllegalArgumentException e) {
			throw new NegocioException(CONTA_NAO_ENCONTRADA);
		}
	}
}

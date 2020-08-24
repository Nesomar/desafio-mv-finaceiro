package com.desafio.financeiro.service;

import java.io.Serializable;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.financeiro.domain.dto.cliente.AddClienteDTO;
import com.desafio.financeiro.domain.dto.cliente.AlterarClienteDTO;
import com.desafio.financeiro.domain.dto.cliente.ClienteDTO;
import com.desafio.financeiro.domain.dto.cliente.ClienteResumoDTO;
import com.desafio.financeiro.domain.entity.Cliente;
import com.desafio.financeiro.domain.entity.ClientePF;
import com.desafio.financeiro.domain.entity.Conta;
import com.desafio.financeiro.domain.entity.Empresa;
import com.desafio.financeiro.domain.entity.Endereco;
import com.desafio.financeiro.domain.entity.Pessoa;
import com.desafio.financeiro.domain.repository.ClientePFRepository;
import com.desafio.financeiro.service.exception.NegocioException;
import com.desafio.financeiro.service.mapper.ClienteMapper;

@Service
public class ClientePFService implements Serializable {

	private static final long serialVersionUID = -2474427549350809628L;
	
	private static final String CLIENTE_NAO_ECONTRADO = "Cliente não econtrado.";

	private static final String ENDERECO_NAO_ENCONTRADA = "Endereço não encontrada.";

	private static final String EMPRESA_NAO_ENCONTRADA = "Empresa não encontrada.";
	
	@Autowired
	private ClientePFRepository repository;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private EmpresaService empresaService;
	
	@Autowired
	private MovimentacaoService movimentacaoService;
	
	@Autowired
	private EnderecoService enderecoService;
	
	@Autowired
	private ContaService contaService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<ClientePF> consultarClientePorId(Long id) {
		return repository.findById(id);
	}
	
	/**
	 * 
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 */
	public Page<ClienteDTO> listarTodos(Integer pagina, Integer tamanhoPagina) {
		
		Pageable pageable = PageRequest.of(pagina, tamanhoPagina);

		Page<ClientePF> page = repository.findAll(pageable);

		return page.map(ClienteMapper::mapper);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<ClienteDTO> consultarPorId(Long id) {
		
		Optional<ClientePF> optional = repository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(ClienteMapper.mapper(optional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * 
	 * @param addClienteDTO
	 * @return
	 */
	@Transactional
	public ResponseEntity<ClienteDTO> cadastrar(@Valid AddClienteDTO addClienteDTO) {
		
		Empresa empresa = empresaService.buscarPorId(addClienteDTO.getIdEmpresa())
				.orElseThrow(() -> new NegocioException(EMPRESA_NAO_ENCONTRADA));
		
		Endereco endereco = enderecoService.buscarPorId(addClienteDTO.getIdEndereco())
				.orElseThrow(() -> new NegocioException(ENDERECO_NAO_ENCONTRADA));
		
		Pessoa pessoa = pessoaService.cadastrar(addClienteDTO.getPessoa());
		
		Cliente cliente = repository.save(ClienteMapper.mapperPF(empresa, pessoa, endereco, addClienteDTO));
		
		Conta conta = contaService.cadastrar(cliente, addClienteDTO.getConta());
		
		movimentacaoService.cadastrar(conta, empresa, addClienteDTO.getMovimentacao());
		
		return ResponseEntity.ok(ClienteMapper.mapper(cliente));
	}
	
	@Transactional
	public ResponseEntity<ClienteResumoDTO> alterar(Long id, @Valid AlterarClienteDTO alterarClienteDTO) {
		
		ClientePF cliente = repository.findById(id).orElseThrow(() -> new NegocioException(CLIENTE_NAO_ECONTRADO));
		
		cliente.setNumeroTelefone(alterarClienteDTO.getNumeroTelefone());
		cliente.setEmail(alterarClienteDTO.getEmail());
		
		return ResponseEntity.ok(ClienteMapper.mapperResumo(repository.save(cliente)));
	}
	
	@Transactional
	public void inativar(Long id) {
		
		ClientePF cliente = repository.findById(id).orElseThrow(() -> new NegocioException(CLIENTE_NAO_ECONTRADO));
		
		cliente.setAtivo(Boolean.FALSE);
		
		repository.save(cliente);
	}
}

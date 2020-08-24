package com.desafio.financeiro.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.financeiro.domain.dto.cliente.ClienteDTO;
import com.desafio.financeiro.domain.entity.Cliente;
import com.desafio.financeiro.domain.repository.ClienteRepository;
import com.desafio.financeiro.service.mapper.ClienteMapper;

@Service
public class ClienteService implements Serializable {

	private static final long serialVersionUID = -3218712469548742564L;
	
	@Autowired
	private ClienteRepository repository;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Cliente> consultarClientePorId(Long id) {
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

		Page<Cliente> page = repository.findAll(pageable);

		return page.map(ClienteMapper::mapper);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<ClienteDTO> consultarPorId(Long id) {
		
		Optional<Cliente> optional = repository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(ClienteMapper.mapper(optional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
}

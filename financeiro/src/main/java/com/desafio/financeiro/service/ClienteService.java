package com.desafio.financeiro.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.financeiro.domain.entity.Cliente;
import com.desafio.financeiro.domain.repository.ClienteRepository;

@Service
public class ClienteService implements Serializable {

	private static final long serialVersionUID = -3218712469548742564L;
	
	@Autowired
	private ClienteRepository repository;
	
	public Optional<Cliente> consultarClientePorId(Long id) {
		return repository.findById(id);
	}
}

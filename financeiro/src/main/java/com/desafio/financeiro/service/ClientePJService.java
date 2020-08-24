package com.desafio.financeiro.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.financeiro.domain.repository.ClientePJRepository;

@Service
public class ClientePJService implements Serializable {

	private static final long serialVersionUID = 8604987906518308254L;
	
	@Autowired
	private ClientePJRepository repository;
}

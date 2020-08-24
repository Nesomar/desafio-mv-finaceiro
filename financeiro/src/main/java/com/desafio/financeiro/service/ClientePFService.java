package com.desafio.financeiro.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.financeiro.domain.repository.ClientePFRepository;

@Service
public class ClientePFService implements Serializable {

	private static final long serialVersionUID = -2474427549350809628L;
	
	@Autowired
	private ClientePFRepository repository;
}

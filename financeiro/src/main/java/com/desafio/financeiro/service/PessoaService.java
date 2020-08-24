package com.desafio.financeiro.service;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.financeiro.domain.repository.PessoaRepository;

@Service
public class PessoaService implements Serializable {

	private static final long serialVersionUID = 6100936795599190144L;
	
	@Autowired
	private PessoaRepository repository;
}

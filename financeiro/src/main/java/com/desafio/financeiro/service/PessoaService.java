package com.desafio.financeiro.service;

import java.io.Serializable;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.financeiro.domain.dto.pessoa.AddPessoaDTO;
import com.desafio.financeiro.domain.entity.Pessoa;
import com.desafio.financeiro.domain.repository.PessoaRepository;
import com.desafio.financeiro.service.mapper.PessoaMapper;

@Service
public class PessoaService implements Serializable {

	private static final long serialVersionUID = 6100936795599190144L;
	
	@Autowired
	private PessoaRepository repository;
	
	@Transactional
	public Pessoa cadastrar(AddPessoaDTO pessoa) {
		return repository.save(PessoaMapper.mapper(pessoa));
	}
}

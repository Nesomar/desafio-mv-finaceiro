package com.desafio.financeiro.service.mapper;

import com.desafio.financeiro.domain.dto.pessoa.AddPessoaDTO;
import com.desafio.financeiro.domain.entity.Pessoa;

public class PessoaMapper {

	private PessoaMapper() {
	}
	
	public static Pessoa mapper (AddPessoaDTO pessoaDTO) {
		
		Pessoa pessoa = new Pessoa();
		
		pessoa.setDataNascimento(pessoaDTO.getDataNascimento());
		pessoa.setNome(pessoaDTO.getNome());
		
		return pessoa;
	}
}

package com.desafio.financeiro.service.mapper;

import com.desafio.financeiro.domain.dto.endereco.AddEnderecoDTO;
import com.desafio.financeiro.domain.dto.endereco.EnderecoDTO;
import com.desafio.financeiro.domain.entity.Endereco;

/**
 * 
 * @author nrfreire
 *
 */
public class EnderecoMapper {

	private EnderecoMapper() {

	}

	public static EnderecoDTO mapper(Endereco endereco) {

		EnderecoDTO enderecoDTO = new EnderecoDTO();

		enderecoDTO.setCep(endereco.getCep());
		enderecoDTO.setBairro(endereco.getBairro());
		enderecoDTO.setComplemento(endereco.getComplemento());
		enderecoDTO.setId(endereco.getId());
		enderecoDTO.setLocalidade(endereco.getLocalidade());
		enderecoDTO.setNumero(endereco.getNumero());
		enderecoDTO.setUf(endereco.getUf());

		return enderecoDTO;
	}

	public static Endereco mapper(AddEnderecoDTO enderecoDTO) {

		Endereco endereco = new Endereco();

		endereco.setCep(enderecoDTO.getCep());
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setLocalidade(enderecoDTO.getLocalidade());
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setUf(enderecoDTO.getUf());

		return endereco;
	}

	public static Endereco mapper(Endereco endereco, AddEnderecoDTO enderecoDTO) {
		
		endereco.setCep(enderecoDTO.getCep());
		endereco.setBairro(enderecoDTO.getBairro());
		endereco.setComplemento(enderecoDTO.getComplemento());
		endereco.setLocalidade(enderecoDTO.getLocalidade());
		endereco.setNumero(enderecoDTO.getNumero());
		endereco.setUf(enderecoDTO.getUf());

		return endereco;
	}
}

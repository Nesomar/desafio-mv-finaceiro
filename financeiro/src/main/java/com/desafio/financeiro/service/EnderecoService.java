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

import com.desafio.financeiro.domain.dto.endereco.AddEnderecoDTO;
import com.desafio.financeiro.domain.dto.endereco.EnderecoDTO;
import com.desafio.financeiro.domain.entity.Endereco;
import com.desafio.financeiro.domain.repository.EnderecoRepository;
import com.desafio.financeiro.service.exception.NegocioException;
import com.desafio.financeiro.service.mapper.EnderecoMapper;

@Service
public class EnderecoService implements Serializable {

	private static final long serialVersionUID = -6750041759070582688L;
	private static final String ENDERECO_INFORMADO_NAO_ENCONTRADO = "Endereço informado não encontrado";
	private static final String ERRO_AO_CADASTRAR_O_ENDERECO = "Erro ao cadastrar o Endereço";

	@Autowired
	private EnderecoRepository repository;

	/**
	 * 
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 */
	public Page<EnderecoDTO> listarTodos(Integer pagina, Integer tamanhoPagina) {

		Pageable pageable = PageRequest.of(pagina, tamanhoPagina);

		Page<Endereco> page = repository.findAll(pageable);

		return page.map(EnderecoMapper::mapper);
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<EnderecoDTO> consultarPorId(Long id) {

		Optional<Endereco> optional = repository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(EnderecoMapper.mapper(optional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	/**
	 * 
	 * @param enderecoDTO
	 * @return
	 */
	@Transactional
	public ResponseEntity<EnderecoDTO> cadastrar(@Valid AddEnderecoDTO enderecoDTO) {

		try {
			return ResponseEntity.ok(EnderecoMapper.mapper(repository.save(EnderecoMapper.mapper(enderecoDTO))));
		} catch (IllegalArgumentException e) {
			throw new NegocioException(ERRO_AO_CADASTRAR_O_ENDERECO, e);
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param enderecoDTO
	 * @return
	 */
	@Transactional
	public ResponseEntity<EnderecoDTO> alterar(Long id, @Valid AddEnderecoDTO enderecoDTO) {

		Optional<Endereco> optional = repository.findById(id);

		if (!optional.isPresent()) {
			throw new NegocioException(ENDERECO_INFORMADO_NAO_ENCONTRADO);
		}

		return ResponseEntity
				.ok(EnderecoMapper.mapper(repository.save(EnderecoMapper.mapper(optional.get(), enderecoDTO))));
	}
	
	/**
	 * 
	 * @param id
	 */
	@Transactional
	public void inativar(Long id) {
		
		Optional<Endereco> optional = repository.findById(id);

		if (!optional.isPresent()) {
			throw new NegocioException(ENDERECO_INFORMADO_NAO_ENCONTRADO);
		}
		
		optional.get().setAtivo(Boolean.FALSE);
		
		repository.save(optional.get());
	}
}

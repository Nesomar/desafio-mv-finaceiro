package com.desafio.financeiro.service;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.financeiro.domain.dto.empresa.EmpresaDTO;
import com.desafio.financeiro.domain.entity.Empresa;
import com.desafio.financeiro.domain.repository.EmpresaRepository;
import com.desafio.financeiro.service.mapper.EmpresaMapper;

@Service
public class EmpresaService implements Serializable {

	private static final long serialVersionUID = 1351147729577507860L;
	
	@Autowired
	private EmpresaRepository repository;
	
	/**
	 * 
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 */
	public Page<EmpresaDTO> listarTodas(Integer pagina, Integer tamanhoPagina) {
		
		Pageable pageable = PageRequest.of(pagina, tamanhoPagina);
		
		return repository.findAll(pageable).map(EmpresaMapper::mapper);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<EmpresaDTO> consultarPorId(Long id) {
		
		Optional<Empresa> optional = repository.findById(id);
		
		if (optional.isPresent()) {
			return ResponseEntity.ok(EmpresaMapper.mapper(optional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}

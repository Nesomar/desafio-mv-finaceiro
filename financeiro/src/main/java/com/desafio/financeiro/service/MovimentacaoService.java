package com.desafio.financeiro.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.financeiro.domain.dto.conta.ContaDTO;
import com.desafio.financeiro.domain.dto.movimentacao.AddMovimentacaoDTO;
import com.desafio.financeiro.domain.dto.movimentacao.FiltroMovimentacaoDTO;
import com.desafio.financeiro.domain.dto.movimentacao.MovimentacaoDTO;
import com.desafio.financeiro.domain.entity.Movimentacao;
import com.desafio.financeiro.domain.repository.MovimentacaoRepository;
import com.desafio.financeiro.domain.repository.speficication.MovimentacaoSpecification;
import com.desafio.financeiro.service.mapper.MovimentacaoMapper;

@Service
public class MovimentacaoService implements Serializable {

	private static final int TAMANHO_PAGINA_PADRAO = 10;

	private static final int PAGINA_PADRAO = 1;

	private static final long serialVersionUID = -7526724814034358377L;
	
	@Autowired
	private MovimentacaoRepository repository;
	
	/**
	 * 
	 * @param idConta
	 * @return
	 */
	public List<Movimentacao> consultarPorIdConta(Long idConta) {
		return repository.findAll(MovimentacaoSpecification.consultarPorIdConta(idConta));
	}
	
	/**
	 * 
	 * @param filtros
	 * @return
	 */
	public Page<MovimentacaoDTO> consultarPorFiltros(FiltroMovimentacaoDTO filtros) {
		
		Pageable pageable = PageRequest.of(filtros.getPagina() != null ? filtros.getPagina() : PAGINA_PADRAO,
				filtros.getTamanhoPagina() != null ? filtros.getTamanhoPagina() : TAMANHO_PAGINA_PADRAO);

		Page<Movimentacao> page = repository.findAll(MovimentacaoSpecification.consultarPorFiltros(filtros), pageable);
		
		return page.map(MovimentacaoMapper::mapper);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<MovimentacaoDTO> consultarPorId(Long id) {
		
		Optional<Movimentacao> optional = repository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(MovimentacaoMapper.mapper(optional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * 
	 * @param movimentacaoDTO
	 * @return
	 */
	@Transactional
	public ResponseEntity<ContaDTO> cadastrar(@Valid AddMovimentacaoDTO movimentacaoDTO) {
		// TODO Auto-generated method stub
		return null;
	}
}

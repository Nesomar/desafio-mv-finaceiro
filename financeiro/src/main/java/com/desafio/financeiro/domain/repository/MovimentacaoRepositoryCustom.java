package com.desafio.financeiro.domain.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.desafio.financeiro.domain.dto.movimentacao.MovimentacaoEmpresaDTO;

@Repository
public interface MovimentacaoRepositoryCustom {

	/**
	 * 
	 * @param cnpj
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	List<MovimentacaoEmpresaDTO> consultarPorCNPJEPeriodo(String cnpj, LocalDate dataInicial, LocalDate dataFinal);
}

package com.desafio.financeiro.domain.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import com.desafio.financeiro.domain.entity.Movimentacao;

@Repository
public interface MovimentacaoRepository extends JpaRepository<Movimentacao, Long>, JpaSpecificationExecutor<Movimentacao>, MovimentacaoRepositoryCustom {

	@Procedure(procedureName = "GET_VALOR_PAGO_MOVIMENTACOES")
	BigDecimal getValorPagoMovimentacoes (Long quantidadeMovimentacoes);
}

package com.desafio.financeiro.domain.repository.impl;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.stereotype.Repository;

import com.desafio.financeiro.domain.dto.movimentacao.MovimentacaoEmpresaDTO;
import com.desafio.financeiro.domain.entity.Cliente;
import com.desafio.financeiro.domain.entity.Cliente_;
import com.desafio.financeiro.domain.entity.Conta;
import com.desafio.financeiro.domain.entity.Conta_;
import com.desafio.financeiro.domain.entity.Empresa;
import com.desafio.financeiro.domain.entity.Empresa_;
import com.desafio.financeiro.domain.entity.Movimentacao;
import com.desafio.financeiro.domain.entity.Movimentacao_;
import com.desafio.financeiro.domain.entity.Pessoa;
import com.desafio.financeiro.domain.entity.Pessoa_;
import com.desafio.financeiro.domain.repository.MovimentacaoRepositoryCustom;
import com.desafio.financeiro.service.exception.NegocioException;

@Repository
public class MovimentacaoRepositoryCustomImpl implements MovimentacaoRepositoryCustom {
	
	private static final String TRUNC = "TRUNC";
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<MovimentacaoEmpresaDTO> consultarPorCNPJEPeriodo(String cnpj, LocalDate dataInicial,
			LocalDate dataFinal) {
		
		CriteriaBuilder builder = em.getCriteriaBuilder();
		CriteriaQuery<MovimentacaoEmpresaDTO> query = builder.createQuery(MovimentacaoEmpresaDTO.class);
		Root<Movimentacao> root = query.from(Movimentacao.class);
		Join<Movimentacao, Conta> joinConta = root.join(Movimentacao_.conta);
		Join<Conta, Cliente> joinCliente = joinConta.join(Conta_.cliente);
		Join<Cliente, Pessoa> joinPessoa = joinCliente.join(Cliente_.pessoa);
		Join<Movimentacao, Empresa> joinEmpresa = root.join(Movimentacao_.empresa);
		
		Subquery<Long> subQuery = query.subquery(Long.class);
		Root<Movimentacao> rootSub = subQuery.from(Movimentacao.class);
		Join<Movimentacao, Conta> joinSubConta = rootSub.join(Movimentacao_.conta);
		Join<Conta, Cliente> joinSubCliente = joinSubConta.join(Conta_.cliente);
		
		query.select(builder.construct(MovimentacaoEmpresaDTO.class, 
				joinEmpresa.get(Empresa_.cnpj),
				joinEmpresa.get(Empresa_.nome),
				joinPessoa.get(Pessoa_.nome),
				subQuery.select(builder.count(rootSub))
				.where(builder.equal(joinSubCliente.get(Cliente_.id), joinCliente.get(Cliente_.id)))))
		.where(builder.equal(joinEmpresa.get(Empresa_.cnpj), cnpj),
				builder.between(
						builder.function(TRUNC, LocalDate.class, root.get(Movimentacao_.dataMovimentacao)),
						dataInicial, dataFinal))
		.distinct(true);
		
		try {
			return em.createQuery(query).getResultList();
		} catch (PersistenceException e) {
			throw new NegocioException();
		}
	}
}

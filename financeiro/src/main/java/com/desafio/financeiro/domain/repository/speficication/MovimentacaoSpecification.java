package com.desafio.financeiro.domain.repository.speficication;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.desafio.financeiro.domain.dto.movimentacao.FiltroMovimentacaoDTO;
import com.desafio.financeiro.domain.entity.Conta;
import com.desafio.financeiro.domain.entity.Conta_;
import com.desafio.financeiro.domain.entity.Movimentacao;
import com.desafio.financeiro.domain.entity.Movimentacao_;

/**
 * 
 * @author nfreire
 *
 */
public class MovimentacaoSpecification {

	private MovimentacaoSpecification() {

	}

	public static Specification<Movimentacao> consultarPorIdConta(Long idConta) {
		return (root, query, builder) -> {

			Join<Movimentacao, Conta> joinConta = root.join(Movimentacao_.conta);

			List<Predicate> predicates = new ArrayList<>();

			if (idConta != null) {
				predicates.add(builder.equal(joinConta.get(Conta_.id), idConta));
			}

			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

	public static Specification<Movimentacao> consultarPorFiltros(FiltroMovimentacaoDTO filtros) {
		// TODO Auto-generated method stub
		return null;
	}
}

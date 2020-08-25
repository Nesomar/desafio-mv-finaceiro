package com.desafio.financeiro.domain.repository.speficication;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.desafio.financeiro.domain.dto.movimentacao.FiltroMovimentacaoDTO;
import com.desafio.financeiro.domain.entity.Cliente;
import com.desafio.financeiro.domain.entity.Cliente_;
import com.desafio.financeiro.domain.entity.Conta;
import com.desafio.financeiro.domain.entity.Conta_;
import com.desafio.financeiro.domain.entity.Empresa;
import com.desafio.financeiro.domain.entity.Empresa_;
import com.desafio.financeiro.domain.entity.Movimentacao;
import com.desafio.financeiro.domain.entity.Movimentacao_;
import com.desafio.financeiro.domain.enums.TipoMovimentacaoEnum;

/**
 * 
 * @author nfreire
 *
 */
public class MovimentacaoSpecification {
	
	private static final String TRUNC = "TRUNC";

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
	
	public static Specification<Movimentacao> consultarPorPeriodo(String dataInicial, String dataFinal) {
		return (root, query, builder) -> {

			List<Predicate> predicates = new ArrayList<>();

			if (dataInicial != null && dataFinal != null) {
				predicates.add(builder.between(
						builder.function(TRUNC, LocalDate.class, root.get(Movimentacao_.dataMovimentacao)),
						LocalDate.parse(dataInicial), LocalDate.parse(dataFinal)));
			}

			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

	public static Specification<Movimentacao> consultarPorFiltros(FiltroMovimentacaoDTO filtros) {
		return (root, query, builder) -> {
			
			Join<Movimentacao, Empresa> joinEmpresa = root.join(Movimentacao_.empresa);
			Join<Movimentacao, Conta> joinConta = root.join(Movimentacao_.conta);

			List<Predicate> predicates = new ArrayList<>();

			if (filtros.getDataInicial() != null && filtros.getDataFinal() != null) {
				predicates.add(builder.between(
						builder.function(TRUNC, LocalDate.class, root.get(Movimentacao_.dataMovimentacao)),
						filtros.getDataInicial(), filtros.getDataFinal()));
			}
			
			if (filtros.getCnpj() != null) {
				predicates.add(builder.equal(joinEmpresa.get(Empresa_.cnpj), filtros.getCnpj()));
			}
			
			if (filtros.getTipoMovimentacao() != null) {
				predicates.add(builder.equal(root.get(Movimentacao_.tipoMovimentacao),
						TipoMovimentacaoEnum.valueOf(filtros.getTipoMovimentacao())));
			}
			
			if (filtros.getNumeroConta() != null) {
				predicates.add(builder.equal(joinConta.get(Conta_.numeroConta), filtros.getNumeroConta()));
			}

			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

	public static Specification<Movimentacao> consultarPorIdCliente(Long idCliente) {
		return (root, query, builder) -> {

			Join<Movimentacao, Conta> joinConta = root.join(Movimentacao_.conta);
			Join<Conta, Cliente> joinCliente = joinConta.join(Conta_.cliente);

			List<Predicate> predicates = new ArrayList<>();

			if (idCliente != null) {
				predicates.add(builder.equal(joinCliente.get(Cliente_.id), idCliente));
			}

			return builder.and(predicates.toArray(new Predicate[predicates.size()]));
		};
	}

	public static Specification<Movimentacao> consultarPorIdClienteEPeriodo(Long idCliente, String dataInicial, String dataFinal) {
		return Specification.where(consultarPorIdCliente(idCliente)).and(consultarPorPeriodo(dataInicial, dataFinal));
	}
}

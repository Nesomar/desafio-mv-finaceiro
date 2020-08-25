package com.desafio.financeiro.service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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

import com.desafio.financeiro.domain.dto.movimentacao.AddMovimentacaoDTO;
import com.desafio.financeiro.domain.dto.movimentacao.FiltroMovimentacaoDTO;
import com.desafio.financeiro.domain.dto.movimentacao.MovimentacaoCSVDTO;
import com.desafio.financeiro.domain.dto.movimentacao.MovimentacaoDTO;
import com.desafio.financeiro.domain.dto.movimentacao.MovimentacaoEmpresaDTO;
import com.desafio.financeiro.domain.dto.movimentacao.MovimentacaoInicialDTO;
import com.desafio.financeiro.domain.entity.Conta;
import com.desafio.financeiro.domain.entity.Empresa;
import com.desafio.financeiro.domain.entity.Movimentacao;
import com.desafio.financeiro.domain.enums.TipoMovimentacaoEnum;
import com.desafio.financeiro.domain.repository.MovimentacaoRepository;
import com.desafio.financeiro.domain.repository.speficication.MovimentacaoSpecification;
import com.desafio.financeiro.service.exception.NegocioException;
import com.desafio.financeiro.service.mapper.MovimentacaoMapper;

@Service
public class MovimentacaoService implements Serializable {

	private static final String VALOR_ACIMA_VINTE_MOVIMENTACOES = "0.50";

	private static final String VALOR_ATE_VINTE_MOVIMENTACOES = "0.75";

	private static final String VALOR_DEZ_MOVIMENTACOES = "1.00";

	private static final int VINTE_MOVIMENTACOES = 20;

	private static final int DEZ_MOVIMENTACOES = 10;

	private static final String A_MOVIMENTACAO_INICIAL_PRECISA_SER_DO_TIPO_CREDITO_EM_CONTA = "A movimentação inicial precisa ser do tipo Crédito em conta.";

	private static final long serialVersionUID = -7526724814034358377L;
	
	private static final String CONTA_NAO_ENCONTRADA = "Conta não encontrada";

	private static final String EMPRESA_NAO_ENCONTRADA = "Empresa não encontrada";

	private static final int TAMANHO_PAGINA_PADRAO = 10;

	private static final int PAGINA_PADRAO = 0;

	
	@Autowired
	private MovimentacaoRepository repository;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private EmpresaService empresaService;
	
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
	public ResponseEntity<MovimentacaoDTO> cadastrar(@Valid AddMovimentacaoDTO movimentacaoDTO) {
		
		Empresa empresa = empresaService.buscarPorId(movimentacaoDTO.getIdEmpresa())
				.orElseThrow(() -> new NegocioException(EMPRESA_NAO_ENCONTRADA));
		
		Conta conta = contaService.buscarPorId(movimentacaoDTO.getIdConta())
				.orElseThrow(() -> new NegocioException(CONTA_NAO_ENCONTRADA));
		
		
		Movimentacao movimentacao = MovimentacaoMapper.mapper(movimentacaoDTO, empresa, conta);
		
		return ResponseEntity.ok(MovimentacaoMapper.mapper(repository.save(movimentacao)));
	}
	
	/**
	 * 
	 * @param conta
	 * @param empresa
	 * @param movimentacaoDTO
	 */
	@Transactional
	public void cadastrar(Conta conta, Empresa empresa, MovimentacaoInicialDTO movimentacaoDTO) {
		
		if (TipoMovimentacaoEnum.DEBITO.equals(TipoMovimentacaoEnum.valueOf(movimentacaoDTO.getTipoMovimentacao()))) {
			throw new NegocioException(A_MOVIMENTACAO_INICIAL_PRECISA_SER_DO_TIPO_CREDITO_EM_CONTA);
		}
		
		Movimentacao movimentacao = repository.save(MovimentacaoMapper.mapper(movimentacaoDTO, empresa, conta));
		
		conta.setSaldoInicial(movimentacao.getValorMovimentacao());
		
		contaService.atualizarSaldo(conta);
	}
	
	/**
	 * 
	 * @param idCliente
	 * @return
	 */
	public MovimentacaoCSVDTO consultarMovimentacaoPorIdCliente(Long idCliente) {
		
		List<Movimentacao> movimentacoes = repository
				.findAll(MovimentacaoSpecification.consultarPorIdCliente(idCliente));
		
		return MovimentacaoCSVDTO.builder()
				.withSaldoInicial(movimentacoes.stream().filter(Movimentacao::getMovimentacaoInicial)
						.map(Movimentacao::getValorMovimentacao).findAny().orElse(BigDecimal.ZERO))
				.withTotalMovimentacao(movimentacoes.stream().count())
				.withTotalMovimentacaoCredito(movimentacoes.stream().filter(movimentacao -> TipoMovimentacaoEnum.CREDITO.equals(movimentacao.getTipoMovimentacao())).count())
				.withTotalMovimentacaoDebito(movimentacoes.stream().filter(movimentacao -> TipoMovimentacaoEnum.DEBITO.equals(movimentacao.getTipoMovimentacao())).count())
				.withValorPago(calcularValorPagoMovimentacoes(movimentacoes.stream().count()))
				.withSaldoAtual(calcularSaldoAtual(movimentacoes))
				.build();
	}

	/**
	 * 
	 * @param movimentacoes
	 * @return
	 */
	private BigDecimal calcularSaldoAtual(List<Movimentacao> movimentacoes) {
		
		BigDecimal saldoInicial = movimentacoes.stream().filter(Movimentacao::getMovimentacaoInicial)
				.map(Movimentacao::getValorMovimentacao).findAny().orElse(BigDecimal.ZERO);
		
		BigDecimal saldoCredito = movimentacoes.stream()
				.filter(movimentacao -> TipoMovimentacaoEnum.CREDITO.equals(movimentacao.getTipoMovimentacao())
						&& Boolean.FALSE.equals(movimentacao.getMovimentacaoInicial()))
				.map(Movimentacao::getValorMovimentacao).reduce(BigDecimal.ZERO, BigDecimal::add);
				
		BigDecimal saldoDebito = movimentacoes.stream()
				.filter(movimentacao -> TipoMovimentacaoEnum.DEBITO.equals(movimentacao.getTipoMovimentacao()))
				.map(Movimentacao::getValorMovimentacao).reduce(BigDecimal.ZERO, BigDecimal::add);		
		
		return saldoInicial.add(saldoCredito).subtract(saldoDebito);
	}

	/**
	 * 
	 * @param totalMovimentacoes
	 * @return
	 */
	public BigDecimal calcularValorPagoMovimentacoes(Long totalMovimentacoes) {
		
		BigDecimal valorMovimentacao = BigDecimal.ZERO;
		
		if (totalMovimentacoes <= DEZ_MOVIMENTACOES) {
			valorMovimentacao = new BigDecimal(VALOR_DEZ_MOVIMENTACOES);
		}
		
		if (totalMovimentacoes > DEZ_MOVIMENTACOES && totalMovimentacoes <= VINTE_MOVIMENTACOES) {
			valorMovimentacao = new BigDecimal(VALOR_ATE_VINTE_MOVIMENTACOES);
		}
		
		if (totalMovimentacoes > VINTE_MOVIMENTACOES) {
			valorMovimentacao = new BigDecimal(VALOR_ACIMA_VINTE_MOVIMENTACOES);
		}
		
		return valorMovimentacao.multiply(new BigDecimal(totalMovimentacoes));
	}

	/**
	 * 
	 * @param idCliente
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	public MovimentacaoCSVDTO consultarMovimentacaoPorIdClienteEPeriodo(Long idCliente, String dataInicial, String dataFinal) {
		
		List<Movimentacao> movimentacoes = repository
				.findAll(MovimentacaoSpecification.consultarPorIdClienteEPeriodo(idCliente, dataInicial, dataFinal));
		
		return MovimentacaoCSVDTO.builder()
				.withSaldoInicial(movimentacoes.stream().filter(Movimentacao::getMovimentacaoInicial)
						.map(Movimentacao::getValorMovimentacao).findAny().orElse(BigDecimal.ZERO))
				.withTotalMovimentacao(movimentacoes.stream().count())
				.withTotalMovimentacaoCredito(movimentacoes.stream().filter(movimentacao -> TipoMovimentacaoEnum.CREDITO.equals(movimentacao.getTipoMovimentacao())).count())
				.withTotalMovimentacaoDebito(movimentacoes.stream().filter(movimentacao -> TipoMovimentacaoEnum.DEBITO.equals(movimentacao.getTipoMovimentacao())).count())
				.withValorPago(calcularValorPagoMovimentacoes(movimentacoes.stream().count()))
				.withSaldoAtual(calcularSaldoAtual(movimentacoes))
				.build();
	}
	
	/**
	 * 
	 * @param cnpj
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	public List<MovimentacaoEmpresaDTO> consultarMovimentacaoPorCNPJEPeriodo (String cnpj, LocalDate dataInicial,
			LocalDate dataFinal) {
		return repository.consultarPorCNPJEPeriodo(cnpj, dataInicial, dataFinal);
	}
}

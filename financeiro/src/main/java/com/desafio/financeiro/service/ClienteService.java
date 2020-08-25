package com.desafio.financeiro.service;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.financeiro.domain.dto.cliente.ClienteDTO;
import com.desafio.financeiro.domain.dto.movimentacao.MovimentacaoCSVDTO;
import com.desafio.financeiro.domain.dto.relatorios.SaldoClienteCSVDTO;
import com.desafio.financeiro.domain.dto.relatorios.SaldoPeriodoCSVDTO;
import com.desafio.financeiro.domain.entity.Cliente;
import com.desafio.financeiro.domain.repository.ClienteRepository;
import com.desafio.financeiro.service.exception.NegocioException;
import com.desafio.financeiro.service.mapper.ClienteMapper;
import com.desafio.financeiro.service.relatorios.RelatoriosCSV;

@Service
public class ClienteService implements Serializable {

	private static final long serialVersionUID = -3218712469548742564L;
	
	private static final String DESAFIO_MV = "desafioMV";
	private static final String EXTENSAO_ARQUIVO_PADRAO = ".csv";
	private static final String RELATORIO = "relatorio";
	private static final char SEPARATOR = ';';
	private static final String SEPARADOR_ENDERECO = ", ";
	private static final String CLIENTE_NAO_ECONTRADO = "Cliente não econtrado.";
	public static final String DDMMYYYY_HHMMSS = "ddMMyyyy-HHMMSS";
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	
	@Autowired
	private ClienteRepository repository;
	
	@Autowired
	private MovimentacaoService movimentacaoService;
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Optional<Cliente> consultarClientePorId(Long id) {
		return repository.findById(id);
	}
	
	/**
	 * 
	 * @param pagina
	 * @param tamanhoPagina
	 * @return
	 */
	public Page<ClienteDTO> listarTodos(Integer pagina, Integer tamanhoPagina) {
		
		Pageable pageable = PageRequest.of(pagina, tamanhoPagina);

		Page<Cliente> page = repository.findAll(pageable);

		return page.map(ClienteMapper::mapper);
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public ResponseEntity<ClienteDTO> consultarPorId(Long id) {
		
		Optional<Cliente> optional = repository.findById(id);

		if (optional.isPresent()) {
			return ResponseEntity.ok(ClienteMapper.mapper(optional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	/**
	 * 
	 * @param documento
	 * @param tipoCliente
	 * @return
	 */
	public ResponseEntity<byte[]> exportRelatorioClienteCSV(Long id) {
		
		MovimentacaoCSVDTO movimentacaoCSVDTO = movimentacaoService.consultarMovimentacaoPorIdCliente(id);
		
		Cliente cliente = repository.findById(id).orElseThrow(() -> new NegocioException(CLIENTE_NAO_ECONTRADO));
		
		String fileName = new StringBuilder()
				.append(RELATORIO)
				.append(LocalDateTime.now()
						.format(DateTimeFormatter.ofPattern(DDMMYYYY_HHMMSS)))
				.append(EXTENSAO_ARQUIVO_PADRAO)
				.toString();
		
		String directory = new StringBuilder()
				.append(DESAFIO_MV)
				.toString();

		List<SaldoClienteCSVDTO> dataBase = Arrays.asList(SaldoClienteCSVDTO.builder()
				.withDataCadastro(cliente.getDataCadastro().format(DateTimeFormatter.ofPattern(DD_MM_YYYY)))
				.withEndereco(new StringBuilder()
						.append(cliente.getEndereco().getLogradouro())
						.append(SEPARADOR_ENDERECO)
						.append(cliente.getEndereco().getNumero())
						.append(SEPARADOR_ENDERECO)
						.append(cliente.getEndereco().getBairro())
						.append(SEPARADOR_ENDERECO)
						.append(cliente.getEndereco().getLocalidade())
						.append(SEPARADOR_ENDERECO)
						.append(cliente.getEndereco().getUf())
						.append(SEPARADOR_ENDERECO)
						.append(cliente.getEndereco().getCep())
						.toString())
				.withMovimentacaoCredito(movimentacaoCSVDTO.getTotalMovimentacaoCredito())
				.withMovimentacaoDebito(movimentacaoCSVDTO.getTotalMovimentacaoDebito())
				.withTotalMovimentacao(movimentacaoCSVDTO.getTotalMovimentacao())
				.withNome(cliente.getPessoa().getNome())
				.withSaldoAtual(movimentacaoCSVDTO.getSaldoAtual())
				.withSaldoInicial(movimentacaoCSVDTO.getSaldoInicial())
				.withValorPago(movimentacaoCSVDTO.getValorPago())
				.build());
				
		RelatoriosCSV.generateFileWithHeader(directory, fileName, dataBase,
				SEPARATOR, SaldoClienteCSVDTO.class);

		String path = new StringBuilder().append(directory).append(File.separator).append(fileName).toString();
		
		return RelatoriosCSV.exportFile(path, fileName);
	}
	
	/**
	 * 
	 * @param documento
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	public ResponseEntity<byte[]> exportRelatorioPeriodoCSV(Long id, String dataInicial, String dataFinal) {
		
		MovimentacaoCSVDTO movimentacaoCSVDTO = movimentacaoService.consultarMovimentacaoPorIdClienteEPeriodo(id, dataInicial, dataFinal);
		
		Cliente cliente = repository.findById(id).orElseThrow(() -> new NegocioException(CLIENTE_NAO_ECONTRADO));
		
		String fileName = new StringBuilder()
				.append(RELATORIO)
				.append(LocalDateTime.now()
						.format(DateTimeFormatter.ofPattern(DDMMYYYY_HHMMSS)))
				.append(EXTENSAO_ARQUIVO_PADRAO)
				.toString();
		
		String directory = new StringBuilder()
				.append(DESAFIO_MV)
				.toString();

		List<SaldoPeriodoCSVDTO> dataBase = Arrays.asList(SaldoPeriodoCSVDTO.builder()
				.withPeriodo(new StringBuilder()
						.append(LocalDate.parse(dataInicial).format(DateTimeFormatter.ofPattern(DD_MM_YYYY)))
						.append(" a ")
						.append(LocalDate.parse(dataFinal).format(DateTimeFormatter.ofPattern(DD_MM_YYYY))).toString())
				.withDataCadastro(cliente.getDataCadastro().format(DateTimeFormatter.ofPattern(DD_MM_YYYY)))
				.withEndereco(new StringBuilder()
						.append(cliente.getEndereco().getLogradouro())
						.append(SEPARADOR_ENDERECO)
						.append(cliente.getEndereco().getNumero())
						.append(SEPARADOR_ENDERECO)
						.append(cliente.getEndereco().getBairro())
						.append(SEPARADOR_ENDERECO)
						.append(cliente.getEndereco().getLocalidade())
						.append(SEPARADOR_ENDERECO)
						.append(cliente.getEndereco().getUf())
						.append(SEPARADOR_ENDERECO)
						.append(cliente.getEndereco().getCep())
						.toString())
				.withMovimentacaoCredito(movimentacaoCSVDTO.getTotalMovimentacaoCredito())
				.withMovimentacaoDebito(movimentacaoCSVDTO.getTotalMovimentacaoDebito())
				.withTotalMovimentacao(movimentacaoCSVDTO.getTotalMovimentacao())
				.withNome(cliente.getPessoa().getNome())
				.withSaldoAtual(movimentacaoCSVDTO.getSaldoAtual())
				.withSaldoInicial(movimentacaoCSVDTO.getSaldoInicial())
				.withValorPago(movimentacaoCSVDTO.getValorPago())
				.build());
				
		RelatoriosCSV.generateFileWithHeader(directory, fileName, dataBase,
				SEPARATOR, SaldoPeriodoCSVDTO.class);

		String path = new StringBuilder().append(directory).append(File.separator).append(fileName).toString();
		
		return RelatoriosCSV.exportFile(path, fileName);
	}
	
	
}

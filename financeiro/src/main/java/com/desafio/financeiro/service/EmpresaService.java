package com.desafio.financeiro.service;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.desafio.financeiro.domain.dto.empresa.EmpresaDTO;
import com.desafio.financeiro.domain.dto.movimentacao.MovimentacaoEmpresaDTO;
import com.desafio.financeiro.domain.dto.relatorios.ReceitaEmpresaCSVDTO;
import com.desafio.financeiro.domain.entity.Empresa;
import com.desafio.financeiro.domain.repository.EmpresaRepository;
import com.desafio.financeiro.service.mapper.EmpresaMapper;
import com.desafio.financeiro.service.relatorios.RelatoriosCSV;

@Service
public class EmpresaService implements Serializable {

	private static final long serialVersionUID = 1351147729577507860L;
	
	private static final String DESAFIO_MV = "desafioMV";
	private static final String EXTENSAO_ARQUIVO_PADRAO = ".csv";
	private static final String RELATORIO = "relatorio";
	private static final char SEPARATOR = ';';
	public static final String DDMMYYYY_HHMMSS = "ddMMyyyy-HHMMSS";
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	
	@Autowired
	private EmpresaRepository repository;
	
	@Autowired
	private MovimentacaoService movimentacaoService;
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
	
	/**
	 * 
	 * @param idEmpresa
	 * @return
	 */
	public Optional<Empresa> buscarPorId(Long idEmpresa) {
		return repository.findById(idEmpresa);
	}
	
	/**
	 * 
	 * @param cnpj
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	public ResponseEntity<byte[]> exportRelatorioReceitaPeriodoCSV(String cnpj, String dataInicial, String dataFinal) {
		
		List<MovimentacaoEmpresaDTO> movimentacaoEmpresaDTOs = movimentacaoService
				.consultarMovimentacaoPorCNPJEPeriodo(cnpj, LocalDate.parse(dataInicial), LocalDate.parse(dataFinal));
		
		List<ReceitaEmpresaCSVDTO> dataBase = recuperarDadosCSV(movimentacaoEmpresaDTOs, dataInicial, dataFinal);
		
		String fileName = new StringBuilder()
				.append(RELATORIO)
				.append(LocalDateTime.now()
						.format(DateTimeFormatter.ofPattern(DDMMYYYY_HHMMSS)))
				.append(EXTENSAO_ARQUIVO_PADRAO)
				.toString();
		
		String directory = new StringBuilder()
				.append(DESAFIO_MV)
				.toString();
				
		RelatoriosCSV.generateFileWithHeader(directory, fileName, dataBase,
				SEPARATOR, ReceitaEmpresaCSVDTO.class);

		String path = new StringBuilder().append(directory).append(File.separator).append(fileName).toString();
		
		return RelatoriosCSV.exportFile(path, fileName);
	}
	
	/**
	 * 
	 * @param movimentacaoEmpresaDTOs
	 * @param dataInicial
	 * @param dataFinal
	 * @return
	 */
	private List<ReceitaEmpresaCSVDTO> recuperarDadosCSV(List<MovimentacaoEmpresaDTO> movimentacaoEmpresaDTOs, String dataInicial, String dataFinal) {
		
		List<ReceitaEmpresaCSVDTO> dataBase = new ArrayList<>();
		
		for (MovimentacaoEmpresaDTO movimentacaoEmpresaDTO : movimentacaoEmpresaDTOs) {
			
			dataBase.add(ReceitaEmpresaCSVDTO.builder()
					.withCnpj(movimentacaoEmpresaDTO.getCnpj())
					.withNomeEmpresa(movimentacaoEmpresaDTO.getNomeEmpresa())
					.withNome(movimentacaoEmpresaDTO.getNomeCliente())
					.withPeriodo(new StringBuilder()
							.append(LocalDate.parse(dataInicial).format(DateTimeFormatter.ofPattern(DD_MM_YYYY)))
							.append(" a ")
							.append(LocalDate.parse(dataFinal).format(DateTimeFormatter.ofPattern(DD_MM_YYYY)))
							.toString())
					.withTotalMovimentacoes(movimentacaoEmpresaDTO.getTotalMovimentacao())
					.withSaldo(movimentacaoService
							.calcularValorPagoMovimentacoes(movimentacaoEmpresaDTO.getTotalMovimentacao()))
					.build());

		}
		return dataBase;
	}

}

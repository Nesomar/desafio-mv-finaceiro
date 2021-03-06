package com.desafio.financeiro.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.financeiro.domain.dto.cliente.ClienteDTO;
import com.desafio.financeiro.service.ClienteService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/clientes", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
@Api(value = "Recurso responsável por disponibilizar acesso aos métodos para manter o Cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;

	@GetMapping
	@ApiOperation(value = "Listar todas os Clientes cadastradas.", tags = { "Clientes" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro") })
	public Page<ClienteDTO> listarTodos(
			@ApiParam("Número da página") @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@ApiParam("Número máximo de itens por página") @RequestParam(value = "tamanhoPagina", defaultValue = "10") Integer tamanhoPagina) {
		return service.listarTodos(pagina, tamanhoPagina);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Consultar uma Cliente por id", tags = { "Clientes" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 404, message = "Não Encontrado") })
	public ResponseEntity<ClienteDTO> consultarPorId(@ApiParam("Id da Conta") @PathVariable(value = "id") Long id) {
		return service.consultarPorId(id);
	}

	@ApiOperation(value = "Resonsável por realizar a exportação do relatório de Saldo", tags = { "Clientes" })
	@GetMapping("{id}/relatorios")
	public ResponseEntity<byte[]> exportRelatorioClienteCSV(
			@ApiParam("Id do Cliente") @RequestParam(value = "id", required = false) Long id) {
		return service.exportRelatorioClienteCSV(id);
	}

	@ApiOperation(value = "Resonsável por realizar a exportação do relatório Saldo por periodo", tags = { "Clientes" })
	@GetMapping("{id}/relatorios/periodos")
	public ResponseEntity<byte[]> exportRelatorioPeriodoCSV(
			@ApiParam("Id do Cliente") @RequestParam(value = "id", required = false) Long id,
			@ApiParam("Data inicial") @RequestParam(value = "dataInicial", required = false) @DateTimeFormat(iso = ISO.DATE) String dataInicial,
			@ApiParam("Data Final") @RequestParam(value = "dataFinal", required = false) @DateTimeFormat(iso = ISO.DATE) String dataFinal) {
		return service.exportRelatorioPeriodoCSV(id, dataInicial, dataFinal);
	}
}

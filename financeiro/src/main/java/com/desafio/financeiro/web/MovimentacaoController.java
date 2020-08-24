package com.desafio.financeiro.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.financeiro.domain.dto.conta.ContaDTO;
import com.desafio.financeiro.domain.dto.movimentacao.AddMovimentacaoDTO;
import com.desafio.financeiro.domain.dto.movimentacao.FiltroMovimentacaoDTO;
import com.desafio.financeiro.domain.dto.movimentacao.MovimentacaoDTO;
import com.desafio.financeiro.service.MovimentacaoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/movimentacoes", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
@Api(value = "Recurso responsável por disponibilizar acesso aos métodos para responsáveis por manter as movimentações Financeiraas")
public class MovimentacaoController {

	@Autowired
	private MovimentacaoService service;
	
	@GetMapping
	@ApiOperation(value = "Consultar as Movimentações por Filtros.", tags = { "movimentacoes" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro") })
	public Page<MovimentacaoDTO> consultarPorFiltros(@RequestParam FiltroMovimentacaoDTO filtros) {
		return service.consultarPorFiltros(filtros);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Consultar uma Movimentação por id", tags = { "movimentacoes" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 404, message = "Não Encontrado") })
	public ResponseEntity<MovimentacaoDTO> consultarPorId(@ApiParam("Id da Movimentação") @PathVariable(value = "id") Long id) {
		return service.consultarPorId(id);
	}

	@PostMapping
	@ApiOperation(value = "Cadastrar uma Movimentação", tags = { "movimentacoes" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro") })
	public ResponseEntity<ContaDTO> cadastrar(
			@ApiParam("Informações para cadastrar uma Movimentação") @Valid @RequestBody AddMovimentacaoDTO movimentacaoDTO) {
		return service.cadastrar(movimentacaoDTO);
	}
}

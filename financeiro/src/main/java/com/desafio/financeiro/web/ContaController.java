package com.desafio.financeiro.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.financeiro.domain.dto.conta.AddContaDTO;
import com.desafio.financeiro.domain.dto.conta.ContaDTO;
import com.desafio.financeiro.service.ContaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/contas", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
@Api(value = "Recurso responsável por disponibilizar acesso aos métodos para manter as Contas")
public class ContaController {

	@Autowired
	private ContaService service;

	@GetMapping
	@ApiOperation(value = "Listar todas as Contas cadastradas.", tags = { "contas" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro") })
	public Page<ContaDTO> listarTodos(
			@ApiParam("Número da página") @RequestParam(value = "pagina", defaultValue = "1") Integer pagina,
			@ApiParam("Número máximo de itens por página") @RequestParam(value = "tamanhoPagina", defaultValue = "10") Integer tamanhoPagina) {
		return service.listarTodos(pagina, tamanhoPagina);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Consultar uma Conta por id", tags = { "contas" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 404, message = "Não Encontrado") })
	public ResponseEntity<ContaDTO> consultarPorId(@ApiParam("Id da Conta") @PathVariable(value = "id") Long id) {
		return service.consultarPorId(id);
	}

	@PostMapping
	@ApiOperation(value = "Cadastrar uma Conta", tags = { "contas" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro") })
	public ResponseEntity<ContaDTO> cadastrar(
			@ApiParam("Informações para cadastrar uma Conta") @Valid @RequestBody AddContaDTO contaDTO) {
		return service.cadastrar(contaDTO);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Alterar as informações da Conta", tags = { "contas" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 404, message = "Não Encontrado"), @ApiResponse(code = 400, message = "Erro") })
	public ResponseEntity<ContaDTO> alterar(@ApiParam("Id da Conta") @PathVariable(value = "id") Long id,
			@ApiParam("Informações para alterar a conta") @Valid @RequestBody AddContaDTO contaDTO) {
		return service.alterar(id, contaDTO);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Inativar uma Conta por id", tags = { "contas" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro"),
			@ApiResponse(code = 404, message = "Não Encontrado") })
	public ResponseEntity<?> inativar(@ApiParam("Id da Conta") @PathVariable(value = "id") Long id) {
		service.inativar(id);
		return ResponseEntity.ok().build();
	}
}

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

import com.desafio.financeiro.domain.dto.endereco.AddEnderecoDTO;
import com.desafio.financeiro.domain.dto.endereco.EnderecoDTO;
import com.desafio.financeiro.service.EnderecoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/enderecos", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
@Api(value = "Recurso responsável por disponibilizar acesso aos métodos para manter o Endereço")
public class EnderecoController {

	@Autowired
	private EnderecoService service;
	
	@GetMapping
	@ApiOperation(value = "Listar todos os endereços cadastradas.", tags = { "Endereços" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro") })
	public Page<EnderecoDTO> listarTodos(
			@ApiParam("Número da página") @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@ApiParam("Número máximo de itens por página") @RequestParam(value = "tamanhoPagina", defaultValue = "10") Integer tamanhoPagina) {
		return service.listarTodos(pagina, tamanhoPagina);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Consultar uma Endereço por id", tags = { "Endereços" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 404, message = "Não Encontrado") })
	public ResponseEntity<EnderecoDTO> consultarPorId(@ApiParam("Id do Endereço") @PathVariable(value = "id") Long id) {
		return service.consultarPorId(id);
	}
	
	@PostMapping
	@ApiOperation(value = "Cadastrar um Endereço", tags = { "Endereços" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro") })
	public ResponseEntity<EnderecoDTO> cadastrar(
			@ApiParam("Informações para cadastrar o Endereço") @Valid @RequestBody AddEnderecoDTO enderecoDTO) {
		return service.cadastrar(enderecoDTO);
	}
	
	@PutMapping("/{id}")
	@ApiOperation(value = "Alterar as informações do Endereço", tags = { "Endereços" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 404, message = "Não Encontrado"), @ApiResponse(code = 400, message = "Erro") })
	public ResponseEntity<EnderecoDTO> alterar(@ApiParam("Id do Endereço") @PathVariable(value = "id") Long id,
			@ApiParam("Informações para alterar o Endereço") @Valid @RequestBody AddEnderecoDTO enderecoDTO) {
		return service.alterar(id, enderecoDTO);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Inativar uma Empresa por id", tags = { "Endereços" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro"),
			@ApiResponse(code = 404, message = "Não Encontrado") })
	public ResponseEntity<?> inativar(@ApiParam("Id do Endereço") @PathVariable(value = "id") Long id) {
		service.inativar(id);
		return ResponseEntity.ok().build();
	}
}

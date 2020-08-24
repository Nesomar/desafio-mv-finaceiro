package com.desafio.financeiro.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.financeiro.domain.dto.empresa.EmpresaDTO;
import com.desafio.financeiro.service.EmpresaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/empresas", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
@Api(value = "Recurso responsável por disponibilizar acesso aos métodos para manter as empresas")
public class EmpresaController {

	@Autowired
	private EmpresaService service;
	
	@GetMapping
	@ApiOperation(value = "Listar todas as empresas cadastradas.", tags = { "Empresas" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro") })
	public Page<EmpresaDTO> listarTodas(
			@ApiParam("Número da página") @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@ApiParam("Número máximo de itens por página") @RequestParam(value = "tamanhoPagina", defaultValue = "10") Integer tamanhoPagina) {
		return service.listarTodas(pagina, tamanhoPagina);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Consultar uma Empresa por id", tags = { "Empresas" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 404, message = "Não Encontrado") })
	public ResponseEntity<EmpresaDTO> consultarPorId(@ApiParam("Id da Empresa") @PathVariable(value = "id") Long id) {
		return service.consultarPorId(id);
	}
}

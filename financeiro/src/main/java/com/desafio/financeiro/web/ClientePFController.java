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

import com.desafio.financeiro.domain.dto.cliente.AddClienteDTO;
import com.desafio.financeiro.domain.dto.cliente.AlterarClienteDTO;
import com.desafio.financeiro.domain.dto.cliente.ClienteDTO;
import com.desafio.financeiro.domain.dto.cliente.ClienteResumoDTO;
import com.desafio.financeiro.service.ClientePFService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping(path = "/clientes-pf", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
@Api(value = "Recurso responsável por disponibilizar acesso aos métodos para manter o Cliente")
public class ClientePFController {

	@Autowired
	private ClientePFService service;
	
	@GetMapping
	@ApiOperation(value = "Listar todas os Clientes PF cadastradas.", tags = { "Clientes PF" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro") })
	public Page<ClienteDTO> listarTodos(
			@ApiParam("Número da página") @RequestParam(value = "pagina", defaultValue = "0") Integer pagina,
			@ApiParam("Número máximo de itens por página") @RequestParam(value = "tamanhoPagina", defaultValue = "10") Integer tamanhoPagina) {
		return service.listarTodos(pagina, tamanhoPagina);
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "Consultar uma Cliente PF por id", tags = { "Clientes PF" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 404, message = "Não Encontrado") })
	public ResponseEntity<ClienteDTO> consultarPorId(@ApiParam("Id da Conta") @PathVariable(value = "id") Long id) {
		return service.consultarPorId(id);
	}

	@PostMapping
	@ApiOperation(value = "Cadastrar um Cliente PF", tags = { "Clientes PF" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro") })
	public ResponseEntity<ClienteDTO> cadastrar(
			@ApiParam("Informações para cadastrar um Cliente") @Valid @RequestBody AddClienteDTO addClienteDTO) {
		return service.cadastrar(addClienteDTO);
	}

	@PutMapping("/{id}")
	@ApiOperation(value = "Alterar as informações de Cliente PF", tags = { "Clientes PF" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"),
			@ApiResponse(code = 404, message = "Não Encontrado"), @ApiResponse(code = 400, message = "Erro") })
	public ResponseEntity<ClienteResumoDTO> alterar(@ApiParam("Id da Conta") @PathVariable(value = "id") Long id,
			@ApiParam("Informações para alterar a conta") @Valid @RequestBody AlterarClienteDTO alterarClienteDTO) {
		return service.alterar(id, alterarClienteDTO);
	}

	@DeleteMapping("/{id}")
	@ApiOperation(value = "Inativar um Cliente PF por id", tags = { "Clientes PF" })
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Sucesso"), @ApiResponse(code = 400, message = "Erro"),
			@ApiResponse(code = 404, message = "Não Encontrado") })
	public ResponseEntity<?> inativar(@ApiParam("Id da Conta") @PathVariable(value = "id") Long id) {
		service.inativar(id);
		return ResponseEntity.ok().build();
	}
}

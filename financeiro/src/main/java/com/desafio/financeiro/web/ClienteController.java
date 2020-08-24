package com.desafio.financeiro.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafio.financeiro.service.ClienteService;

import io.swagger.annotations.Api;


@RestController
@RequestMapping(path = "/clientes", produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
@Api(value = "Recurso responsável por disponibilizar acesso aos métodos para manter o Cliente")
public class ClienteController {

	@Autowired
	private ClienteService service;
}

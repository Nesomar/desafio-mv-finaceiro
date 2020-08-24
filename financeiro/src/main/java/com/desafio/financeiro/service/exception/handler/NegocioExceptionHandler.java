package com.desafio.financeiro.service.exception.handler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.desafio.financeiro.domain.dto.MensagemErroDTO;
import com.desafio.financeiro.service.exception.NegocioException;

/**
 * 
 * @author nrfreire
 *
 */
@ControllerAdvice
public class NegocioExceptionHandler {

	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<MensagemErroDTO> handleApiRepositoryException (NegocioException ex) {
		new MensagemErroDTO();
		return new ResponseEntity<>(MensagemErroDTO
				.builder()
				.addCodigo(HttpStatus.BAD_REQUEST.value())
				.addTimestamp(LocalDate.now())
				.addMensagem(ex.getMessage())
				.addDetalhe(ex.getLocalizedMessage())
				.build(), HttpStatus.BAD_REQUEST);
	}
}

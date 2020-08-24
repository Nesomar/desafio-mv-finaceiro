package com.desafio.financeiro.service.exception;

/**
 * 
 * @author nrfreire
 *
 */
public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -4974400954021758930L;

	public NegocioException() {
	}

	public NegocioException(String message, Throwable cause) {
		super(message, cause);
	}

	public NegocioException(String message) {
		super(message);
	}
}

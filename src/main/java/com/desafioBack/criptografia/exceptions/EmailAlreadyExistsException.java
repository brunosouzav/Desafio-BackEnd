package com.desafioBack.criptografia.exceptions;

public class EmailAlreadyExistsException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	public EmailAlreadyExistsException(String mensage) {
		super(mensage);
	}
}

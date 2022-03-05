package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmailNotExistException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8437462749709206813L;

	/**
	 * EXCEPCIÓN PARA NOTIFICAR QUE EL EMAIL INTRODUCIDO NO EXISTE
	 */
	public EmailNotExistException(String email) {
		super("The email " + email + " not exist");
	}

}

package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2518408353976859288L;

	/**
	 * EXCEPCIÓN PARA NOTIFICAR QUE EL USUARIO INTRODUCIDO NO EXISTE
	 */
	public UserNotFoundException(Long id) {
		super("The user with the id " + id + " does not exit");
	}	
}

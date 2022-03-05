package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class CitaNotFoundException extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6381931935881878085L;

	/**
	 * EXCEPCIÓN PARA NOTIFICAR QUE LA CITA CON LA ID INTRODUCIDA NO EXISTE
	 */
	public CitaNotFoundException(Long id) {
		super("The appointment with the id " + id + " does not exist");
	}	
}

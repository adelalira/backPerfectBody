package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class CitaExistException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6770330861010505348L;

	/**
	 * EXCEPCIÓN PARA NOTIFICAR QUE LA CITA SOLICITADA YA ESTA CONCEDIDA A ESE USUARIO ANTERIORMENTE
	 */
	public CitaExistException() {
		super("You already have an appointment on the requested date and time");
	}	

}

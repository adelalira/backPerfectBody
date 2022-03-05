package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class PasswordException  extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3069145615406976560L;

	/**
	 * EXCEPCIÓN PARA NOTIFICAR QUE LA CONTRASEÑA INTRODUCIDA ES INCORRECTA
	 */
	public PasswordException() {
		super("Password is incorrect");
	}	

}

package com.example.demo.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class LineaCitaServicioNotExistException  extends RuntimeException{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5888021333390351607L;

	/**
	 * EXCEPCIÓN PARA NOTIFICAR QUE LA CITA CON LA LINEACITASERVICIO INDICADA NO EXISTE
	 */
	public LineaCitaServicioNotExistException(Long idLP) {
		super("The appointment with the service id " + idLP + " does not exit");
	}	

}

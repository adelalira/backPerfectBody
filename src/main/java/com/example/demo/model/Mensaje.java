package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * CLASE DEL MENSAJE
 * @author adela
 *
 */
public class Mensaje {

	/**
	 * ATRIBUTOS
	 */
	private String to;
	private String subject;
	private String text;
	
	/**
	 * CONSTRUCTOR VACIO
	 */
	public Mensaje() {
		super();
	}


	/**
	 * CONSTRUCTOR CON TODOS LOS ATRIBUTOS DEL MENSAJE
	 * @param id
	 * @param to
	 * @param subject
	 * @param text
	 */
	public Mensaje( String to, String subject, String text) {
		super();

		this.to = to;
		this.subject = subject;
		this.text = text;
	}


	/**
	 * GETTER Y SETTERS DE LOS ATRIBUTOS
	 * @return
	 */
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	/**
	 * TOSTRING DE TODOS LOS ATRIBUTOS DEL MENSAJE
	 */
	@Override
	public String toString() {
		return "Mensaje [to=" + to + ", subject=" + subject + ", text=" + text + "]";
	}
	
	
	
	
	
	
}

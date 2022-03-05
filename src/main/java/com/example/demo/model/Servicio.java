package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * ENTIDAD DEL SERVICIO
 * @author adela
 *
 */
@Entity
@Table(name="servicio")
public class Servicio {
	
	/**
	 * ID QUE SE GENERA DE FORMA AUTOMATICA
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nombre;
	
	private double precio;
	
	private String tipo;

	/**
	 * CONSTRUCTOR VACIO
	 */
	public Servicio() {
	}

	/**
	 * CONTRUCTOR CON LA ID DEL SERVICIO
	 * @param id
	 */
	public Servicio(Long id) {
		super();
		this.id = id;
	}

	/**
	 * CONSTRUCTOR CON EL NOMBRE DEL SERVICIO
	 * @param nombre
	 */
	public Servicio(String nombre) {
		super();
		this.nombre = nombre;
	}



	/**
	 * CONSTRUCTOR CON TODOS LOS ATRIBUTOS DEL SERVICIO
	 * @param nombre
	 * @param precio
	 */
	public Servicio( long id, String nombre, double precio, String tipo) {
		this.id=id;
		this.nombre = nombre;
		this.precio = precio;
		this.tipo = tipo;
	}

	
	/**
	 * GETTER Y SETTERS DE LOS ATRIBUTOS
	 * @return
	 */
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * HASHCODE Y EQUALS DE LA ID
	 */
	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Servicio other = (Servicio) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * TOSTRING DE TODOS LOS ATRIBUTOS DEL SERVICIO
	 */
	@Override
	public String toString() {
		return "Servicio [id=" + id + ", nombre=" + nombre + ", precio=" + precio + "]";
	}
	
	
	

}

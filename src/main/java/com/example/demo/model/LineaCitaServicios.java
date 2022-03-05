package com.example.demo.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * ENTIDAD DE LINEACITASERVICIOS
 * @author adela
 *
 */
@Entity
@Table(name="lineaCitaServicios")
public class LineaCitaServicios {

	/**
	 * ID QUE SE GENERA DE FORMA AUTOMATICA
	 */
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@ManyToOne
	private Servicio servicio;
	
	private double descuento;

	/**
	 * CONSTRUCTOR VACIO
	 */
	public LineaCitaServicios() {
		super();
	}
	
	

	/**
	 * CONTRUCTOR CON LA ID DE LA CITA
	 * @param id
	 */
	public LineaCitaServicios(long id) {
		super();
		this.id = id;
	}

	/**
	 * CONTRUCTOR CON LA ID, SERVICIO Y DESCUENTO
	 * @param id
	 */
	public LineaCitaServicios(long id, Servicio servicio, double descuento) {
		super();
		this.id = id;
		this.servicio = servicio;
		this.descuento = descuento;
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

	public Servicio getServicio() {
		return servicio;
	}

	public void setServicio(Servicio servicio) {
		this.servicio = servicio;
	}

	public double getDescuento() {
		return descuento;
	}

	public void setDescuento(double descuento) {
		this.descuento = descuento;
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
		LineaCitaServicios other = (LineaCitaServicios) obj;
		return id == other.id;
	}

	/**
	 * TOSTRING DE TODOS LOS ATRIBUTOS DE LA LINEACITASERVICIO
	 */
	@Override
	public String toString() {
		return "LineaCitaServicios [id=" + id + ", servicio=" + servicio + ", descuento=" + descuento + "]";
	}
	
	
}

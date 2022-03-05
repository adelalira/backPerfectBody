package com.example.demo.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * ENTIDAD DE LA CITA
 * @author adela
 *
 */
@Entity
@Table(name="cita")
public class Cita {
	
	/**
	 * ID QUE SE GENERA DE FORMA AUTOMATICA
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	/**
	 * VARIABLE QUE CONTIENE LA FECHA Y LA HORA
	 */
	private LocalDateTime dia;
	
	@OneToMany (cascade = CascadeType.ALL)
	private List<LineaCitaServicios> listaCitaServicios = new ArrayList<>();

	
	@JsonBackReference
	@ManyToOne
	private Usuario usuario;

	/**
	 * CONSTRUCTOR VACIO
	 */
	public Cita() {
		super();
	}
	
	
	/**
	 * CONTRUCTOR CON LA ID DE LA CITA
	 * @param id
	 */
	public Cita(long id) {
		super();
		this.id = id;
	}


	/**
	 * CONSTRUCTOR CON EL USUARIO PARA PODER GUARDARLE LA CITA
	 * @param usuario
	 */
	public Cita(Usuario usuario) {
		super();
		this.usuario = usuario;
	}

	/**
	 * CONSTRUCTOR CON TODOS LOS ATRIBUTOS MENOS LA LISTA DE SERVICIOS
	 * @param id
	 * @param dia
	 * @param hora
	 */
	public Cita(long id, LocalDateTime dia) {
		super();
		this.id = id;
		this.dia = dia;
	}
	
	/**
	 * CONSTRUCTOR CON LA ID, DIA Y USUARIO
	 * @param id
	 * @param dia
	 * @param usuario
	 */
	public Cita(long id, LocalDateTime dia, Usuario usuario) {
		super();
		this.id = id;
		this.dia = dia;
		this.usuario = usuario;
	}
	
	/**
	 * CONSTRUCTOR CON TODOS LOS ATRIBUTOS DE LA CITA
	 * @param id
	 * @param dia
	 * @param hora
	 * @param listaServicios
	 */
	public Cita(long id, LocalDateTime dia, List<LineaCitaServicios> listaCitaServicios) {
		super();
		this.id = id;
		this.dia = dia;
		this.listaCitaServicios = listaCitaServicios;
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


	public LocalDateTime getDia() {
		return dia;
	}

	public void setDia(LocalDateTime dia) {
		this.dia = dia;
	}

	public List<LineaCitaServicios> getListaServicios() {
		return listaCitaServicios;
	}

	public void setListaServicios(List<LineaCitaServicios> listaCitaServicios) {
		this.listaCitaServicios = listaCitaServicios;
	}


	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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
		Cita other = (Cita) obj;
		return id == other.id;
	}


	/**
	 * TOSTRING DE TODOS LOS ATRIBUTOS DE LA CITA
	 */
	@Override
	public String toString() {
		return "Cita [id=" + id + ", dia=" + dia + ", listaCitaServicios=" + listaCitaServicios + "]";
	}

	
	
	
}

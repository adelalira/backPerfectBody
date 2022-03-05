package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * ENTIDAD DEL USUARIO
 * @author adela
 *
 */
@Entity
@Table(name="user")
public class Usuario {

	/**
	 * ID QUE SE GENERA DE FORMA AUTOMATICA
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String name;
	
	private String lastname;
	
	private String dni;
	
	private String telephone;
	
	private String email;

	//Evita que el campo password se incluya en el JSON de respuesta
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	
	
	@OneToMany (fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	private List<Cita> listaCitas = new ArrayList();

	/**
	 * CONSTRUCTOR VACIO
	 */
	public Usuario() {
		super();
	}

	/**
	 * CONSTRUCTOR CON TODOS LOS ATRIBUTOS MENOS LA LISTA DE CITAS
	 * @param id
	 * @param name
	 * @param lastname
	 * @param dni
	 * @param edad
	 * @param telephone
	 * @param address
	 * @param email
	 * @param password
	 */
	public Usuario(Long id, String name, String lastname, String dni, String telephone,
			String email, String password) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.dni = dni;
		this.telephone = telephone;
		this.email = email;
		this.password = password;
	}
	
	
	/**
	 * CONSTRUCTOR CON TODOS LOS ATRIBUTOS DEL USUARIO
	 * @param id
	 * @param name
	 * @param lastname
	 * @param dni
	 * @param edad
	 * @param telephone
	 * @param address
	 * @param email
	 * @param password
	 * @param listaCitas
	 */
	public Usuario(Long id, String name, String lastname, String dni, String telephone,
			String email, String password, List<Cita> listaCitas) {
		super();
		this.id = id;
		this.name = name;
		this.lastname = lastname;
		this.dni = dni;
		this.telephone = telephone;
		this.email = email;
		this.password = password;
		this.listaCitas = listaCitas;
	}

	/**
	 * GETTER Y SETTERS DE LOS ATRIBUTOS
	 * @return
	 */
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getname() {
		return name;
	}

	public void setname(String name) {
		this.name = name;
	}

	public String getlastname() {
		return lastname;
	}

	public void setlastname(String lastname) {
		this.lastname = lastname;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}



	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Cita> getListaCitas() {
		return listaCitas;
	}

	public void setListaCitas(List<Cita> listaCitas) {
		this.listaCitas = listaCitas;
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
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * TOSTRING DE TODOS LOS ATRIBUTOS DEL USUARIO
	 */
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", name=" + name + ", lastname=" + lastname + ", dni=" + dni + ", telephone=" + telephone + ", email=" + email + ", password=" + password
				+ ", listaCitas=" + listaCitas + "]";
	}

	

	
	
}

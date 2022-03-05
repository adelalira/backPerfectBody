package com.example.demo.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Usuario;

/**
 * INTERFAZ QUE NOS PERMITE COMUNICARNOS CON EL REPOSITORIO DEL USUARIO
 * @author adela
 *
 */
public interface UserRepo extends JpaRepository<Usuario, Long> {
  
	/**
	 * METODO QUE NOS ENCUENTRA EL EMAIL DEL USUARIO
	 * @param email
	 * @return
	 */
	public Usuario findByEmail(String email);

	/**
	 * CONSULTA QUE NOS DEVUELVE SI EL EMAIL INTRODUCIDO ESTA REGISTRADO YA EN LA BASE DE DATOS
	 * @param email
	 * @return
	 */
	@Query(value="select email from user where email = ?1", nativeQuery = true) 
	public Object getEmail(String email);
	

	
}

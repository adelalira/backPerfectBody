package com.example.demo.repository;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Cita;

/**
 * INTERFAZ QUE NOS PERMITE COMUNICARNOS CON EL REPOSITORIO DE CITA
 * @author adela
 *
 */
public interface CitaRepo extends JpaRepository<Cita, Long>{

	/**
	 * CONSULTA QUE NOS INDICA SI LA CITA QUE INTRODUCIMOS YA LA TENEMOS ASIGNADA
	 * @param dia
	 * @return
	 */
	@Query(value="select dia from cita where dia = ?1", nativeQuery = true) 
	public Object getDia(LocalDateTime dia);

}

package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Servicio;

/**
 * INTERFAZ QUE NOS PERMITE COMUNICARNOS CON EL REPOSITORIO DE SERVICIO
 * @author adela
 *
 */
public interface ServicioRepo extends JpaRepository<Servicio, Long>{

}

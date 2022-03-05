package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.LineaCitaServicios;

/**
 * INTERFAZ QUE NOS PERMITE COMUNICARNOS CON EL REPOSITORIO DE LA LINEACITASERVICIOS
 * @author adela
 *
 */
public interface LineaCitaServicioRepo extends JpaRepository<LineaCitaServicios, Long> {


}


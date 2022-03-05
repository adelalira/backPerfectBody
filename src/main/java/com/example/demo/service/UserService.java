package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.example.demo.model.Usuario;
import com.example.demo.repository.UserRepo;

/**
 * SERVICIO DEL USUARIO
 * @author adela
 *
 */
@Service
public class UserService {

	/**
	 * INYECTAMOS EL REPOSITORIO DEL USUARIO
	 */
	@Autowired
	private UserRepo repositorio;
	
	/**
	 * BUSCAMOS EL USUARIO POR EMAIL
	 * @param email
	 * @return
	 */
	public Usuario findByEmail(String email) {
		return this.repositorio.findByEmail(email);
	}


}
